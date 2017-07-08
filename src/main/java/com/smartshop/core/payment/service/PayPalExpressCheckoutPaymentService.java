package com.smartshop.core.payment.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartshop.core.catalog.service.PricingService;
import com.smartshop.core.enumeration.PaymentEnvEnum;
import com.smartshop.core.enumeration.TransactionType;
import com.smartshop.core.order.SalesOrder;
import com.smartshop.core.payment.enumeration.PaymentType;
import com.smartshop.core.payments.model.Payment;
import com.smartshop.core.payments.model.PaypalPayment;
import com.smartshop.domain.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.domain.PaymentConfiguration;
import com.smartshop.domain.Transaction;
import com.smartshop.exception.BusinessException;
import com.smartshop.exception.PaymentErrorCode;
import com.smartshop.shop.model.ShoppingCartItem;

import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentReq;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentRequestType;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentResponseType;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsReq;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsRequestType;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsResponseType;
import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.DoExpressCheckoutPaymentRequestDetailsType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsType;
import urn.ebay.apis.eBLBaseComponents.PaymentInfoType;

public final class PayPalExpressCheckoutPaymentService implements PaymentService {
	private final Logger LOGGER = LoggerFactory.getLogger(PayPalExpressCheckoutPaymentService.class);

	@Inject
	private PricingService pricingService;

	@Override
	public void validatePaymentConfiguration(PaymentConfiguration paymentConfiguration, MerchantStore store)
			throws BusinessException {
		List<String> errorFields = new ArrayList<String>();
		String api = paymentConfiguration.getApi();
		String username = paymentConfiguration.getUsername();
		String signature = paymentConfiguration.getSignature();
		if (StringUtils.isBlank(api)) {
			errorFields.add("api");
		}
		if (StringUtils.isBlank(username)) {
			errorFields.add("username");
		}
		if (StringUtils.isBlank(signature)) {
			errorFields.add("signature");
		}
		if (errorFields.isEmpty()) {
			BusinessException ex = new BusinessException(PaymentErrorCode.ERROR_VALIDATION_SAVE);
			throw ex;
		}

	}

	@Override
	public Transaction initTransaction(MerchantStore store, Customer customer, BigDecimal amount, Payment payment,
			PaymentConfiguration configuration) throws BusinessException {
		throw new BusinessException("Not imlemented");

	}

	@Override
	public Transaction authorize(MerchantStore store, Customer customer, List<ShoppingCartItem> items,
			BigDecimal amount, Payment payment, PaymentConfiguration configuration) throws BusinessException {
		PaypalPayment paypalPayment = (PaypalPayment) payment;
		Validate.notNull(paypalPayment.getPaymentToken(),
				"A paypal payment token is required to process this transaction");
		return processTransaction(store, customer, items, amount, paypalPayment, configuration);

	}

	@Override
	public Transaction capture(MerchantStore store, Customer customer, SalesOrder order,
			Transaction capturableTransaction, PaymentConfiguration configuration) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction authorizeAndCapture(MerchantStore store, Customer customer, List<ShoppingCartItem> items,
			BigDecimal amount, Payment payment, PaymentConfiguration configuration) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction refund(boolean partial, MerchantStore store, Transaction transaction, SalesOrder order,
			BigDecimal amount, PaymentConfiguration configuration) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	private Transaction processTransaction(MerchantStore store, Customer customer, List<ShoppingCartItem> items,
			BigDecimal amount, PaypalPayment paypalPayment, PaymentConfiguration configuration)
			throws BusinessException {
		try {
			String mode = "sandbox";
			PaymentEnvEnum env = configuration.getEnvType();
			if (env == PaymentEnvEnum.PRODUCTION) {
				mode = "production";
			}
			GetExpressCheckoutDetailsRequestType getExpressCheckoutDetailsRequest = new GetExpressCheckoutDetailsRequestType(
					paypalPayment.getPaymentToken());
			getExpressCheckoutDetailsRequest.setVersion("104.0");

			GetExpressCheckoutDetailsReq getExpressCheckoutDetailsReq = new GetExpressCheckoutDetailsReq();
			getExpressCheckoutDetailsReq.setGetExpressCheckoutDetailsRequest(getExpressCheckoutDetailsRequest);

			Map<String, String> configurationMap = new HashMap<String, String>();
			configurationMap.put("mode", mode);
			configurationMap.put("acct1.UserName", configuration.getUsername());
			configurationMap.put("acct1.Password", configuration.getApi());
			configurationMap.put("acct1.Signature", configuration.getSignature());
			PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap);
			GetExpressCheckoutDetailsResponseType getExpressCheckoutDetailsResponse = service
					.getExpressCheckoutDetails(getExpressCheckoutDetailsReq);

			String token = getExpressCheckoutDetailsResponse.getGetExpressCheckoutDetailsResponseDetails().getToken();
			String correlationID = getExpressCheckoutDetailsResponse.getCorrelationID();
			String ack = getExpressCheckoutDetailsResponse.getAck().getValue();
			String payerId = getExpressCheckoutDetailsResponse.getGetExpressCheckoutDetailsResponseDetails()
					.getPayerInfo().getPayerID();
			if (!"Success".equals(ack)) {
				LOGGER.error("Wrong value from anthorize and capture transaction " + ack);
				throw new BusinessException("Wrong paypal ack from init transaction " + ack);
			}

			PaymentDetailsType paymentDetail = new PaymentDetailsType();
			/** IPN **/
			// paymentDetail.setNotifyURL("http://replaceIpnUrl.com");
			BasicAmountType orderTotal = new BasicAmountType();
			orderTotal.setValue(pricingService.getStringAmount(amount, store));
			orderTotal.setCurrencyID(
					urn.ebay.apis.eBLBaseComponents.CurrencyCodeType.fromValue(paypalPayment.getCurrency().getCode()));
			paymentDetail.setOrderTotal(orderTotal);
			paymentDetail.setButtonSource("Shopizer_Cart_AP");
			/** sale or pre-auth **/
			if (paypalPayment.getTransactionType().name().equals(TransactionType.AUTHORIZE.name())) {
				paymentDetail.setPaymentAction(urn.ebay.apis.eBLBaseComponents.PaymentActionCodeType.AUTHORIZATION);
			} else {
				paymentDetail.setPaymentAction(urn.ebay.apis.eBLBaseComponents.PaymentActionCodeType.SALE);
			}

			List<PaymentDetailsType> paymentDetails = new ArrayList<PaymentDetailsType>();
			paymentDetails.add(paymentDetail);

			DoExpressCheckoutPaymentRequestDetailsType doExpressCheckoutPaymentRequestDetails = new DoExpressCheckoutPaymentRequestDetailsType();
			doExpressCheckoutPaymentRequestDetails.setToken(token);
			doExpressCheckoutPaymentRequestDetails.setPayerID(payerId);
			doExpressCheckoutPaymentRequestDetails.setPaymentDetails(paymentDetails);

			DoExpressCheckoutPaymentRequestType doExpressCheckoutPaymentRequest = new DoExpressCheckoutPaymentRequestType(
					doExpressCheckoutPaymentRequestDetails);
			doExpressCheckoutPaymentRequest.setVersion("104.0");

			DoExpressCheckoutPaymentReq doExpressCheckoutPaymentReq = new DoExpressCheckoutPaymentReq();
			doExpressCheckoutPaymentReq.setDoExpressCheckoutPaymentRequest(doExpressCheckoutPaymentRequest);

			DoExpressCheckoutPaymentResponseType doExpressCheckoutPaymentResponse = service
					.doExpressCheckoutPayment(doExpressCheckoutPaymentReq);
			String commitAck = doExpressCheckoutPaymentResponse.getAck().getValue();

			if (!"Success".equals(commitAck)) {
				LOGGER.error("Wrong value from transaction commit " + ack);
				throw new BusinessException("Wrong paypal ack from init transaction " + ack);
			}

			List<PaymentInfoType> paymentInfoList = doExpressCheckoutPaymentResponse
					.getDoExpressCheckoutPaymentResponseDetails().getPaymentInfo();
			String transactionId = null;

			for (PaymentInfoType paymentInfo : paymentInfoList) {
				transactionId = paymentInfo.getTransactionID();
			}
			Transaction transaction = new Transaction();
			transaction.setAmount(amount);
			transaction.setTransactionDate(new Date());
			transaction.setTransactionType(paypalPayment.getTransactionType());
			transaction.setPaymentType(PaymentType.PAYPAL);
			transaction.getTransactionDetails().put("TOKEN", token);
			transaction.getTransactionDetails().put("PAYERID", payerId);
			transaction.getTransactionDetails().put("TRANSACTIONID", transactionId);
			transaction.getTransactionDetails().put("CORRELATION", correlationID);
			return transaction;

		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}
}
