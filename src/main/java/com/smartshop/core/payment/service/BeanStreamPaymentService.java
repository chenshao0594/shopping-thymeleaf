package com.smartshop.core.payment.service;

import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartshop.core.enumeration.PaymentEnvEnum;
import com.smartshop.core.enumeration.TransactionType;
import com.smartshop.core.order.SalesOrder;
import com.smartshop.core.payments.model.Payment;
import com.smartshop.core.product.ProductPriceUtils;
import com.smartshop.domain.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.domain.PaymentConfiguration;
import com.smartshop.domain.Transaction;
import com.smartshop.exception.BusinessException;
import com.smartshop.shop.model.ShoppingCartItem;

public class BeanStreamPaymentService implements PaymentService {
	private final Logger LOGGER = LoggerFactory.getLogger(BeanStreamPaymentService.class);

	@Override
	public void validatePaymentConfiguration(PaymentConfiguration integrationConfiguration, MerchantStore store)
			throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public Transaction initTransaction(MerchantStore store, Customer customer, BigDecimal amount, Payment payment,
			PaymentConfiguration configuration) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction authorize(MerchantStore store, Customer customer, List<ShoppingCartItem> items,
			BigDecimal amount, Payment payment, PaymentConfiguration configuration) throws BusinessException {
		return processTransaction(store, customer, TransactionType.AUTHORIZE, amount, payment, configuration);
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

	private Transaction processTransaction(MerchantStore store, Customer customer, TransactionType type,
			BigDecimal amount, Payment payment, PaymentConfiguration configuration) throws BusinessException {
		boolean bSandbox = false;
		PaymentEnvEnum env = configuration.getEnvType();
		if (env == PaymentEnvEnum.SANDBOX) {// sandbox
			bSandbox = true;
		}
		StringBuilder serverBuilder = new StringBuilder().append(configuration.getScheme());
		serverBuilder.append("://");
		serverBuilder.append(configuration.getHost()).append(":");
		serverBuilder.append(configuration.getPort()).append(configuration.getUri());
		String server = serverBuilder.toString();

		HttpURLConnection conn = null;

		try {
			String uniqueId = UUID.randomUUID().toString();// TODO
			String orderNumber = uniqueId;

			String amnt = ProductPriceUtils.getAdminFormatedAmount(store, amount);
			StringBuilder messageString = new StringBuilder();
			String transactionType = "P";
			if (type == TransactionType.AUTHORIZE) {
				transactionType = "PA";
			} else if (type == TransactionType.AUTHORIZECAPTURE) {
				transactionType = "P";
			}
			CreditCardPayment creditCardPayment = (CreditCardPayment) payment;

			messageString.append("requestType=BACKEND&");
			messageString.append("merchant_id=").append(configuration.getIntegrationKeys().get("merchantid"))
					.append("&");
			messageString.append("trnType=").append(transactionType).append("&");
			messageString.append("username=").append(configuration.getIntegrationKeys().get("username")).append("&");
			messageString.append("password=").append(configuration.getIntegrationKeys().get("password")).append("&");
			messageString.append("orderNumber=").append(orderNumber).append("&");
			messageString.append("trnCardOwner=").append(creditCardPayment.getCardOwner()).append("&");
			messageString.append("trnCardNumber=").append(creditCardPayment.getCreditCardNumber()).append("&");
			messageString.append("trnExpMonth=").append(creditCardPayment.getExpirationMonth()).append("&");
			messageString.append("trnExpYear=").append(creditCardPayment.getExpirationYear().substring(2)).append("&");
			messageString.append("trnCardCvd=").append(creditCardPayment.getCredidCardValidationNumber()).append("&");
			messageString.append("trnAmount=").append(amnt).append("&");

			StringBuilder nm = new StringBuilder();
			nm.append(customer.getBilling().getFirstName()).append(" ").append(customer.getBilling().getLastName());

			messageString.append("ordName=").append(nm.toString()).append("&");
			messageString.append("ordAddress1=").append(customer.getBilling().getAddress()).append("&");
			messageString.append("ordCity=").append(customer.getBilling().getCity()).append("&");

			String stateProvince = customer.getBilling().getState();
			if (customer.getBilling().getZone() != null) {
				stateProvince = customer.getBilling().getZone().getCode();
			}

			String countryName = customer.getBilling().getCountry().getIsoCode();

			messageString.append("ordProvince=").append(stateProvince).append("&");
			messageString.append("ordPostalCode=").append(customer.getBilling().getPostalCode().replaceAll("\\s", ""))
					.append("&");
			messageString.append("ordCountry=").append(countryName).append("&");
			messageString.append("ordPhoneNumber=").append(customer.getBilling().getTelephone()).append("&");
			messageString.append("ordEmailAddress=").append(customer.getEmailAddress());

			StringBuffer messageLogString = new StringBuffer();

			messageLogString.append("requestType=BACKEND&");
			messageLogString.append("merchant_id=").append(configuration.getIntegrationKeys().get("merchantid"))
					.append("&");
			messageLogString.append("trnType=").append(type).append("&");
			messageLogString.append("orderNumber=").append(orderNumber).append("&");
			messageLogString.append("trnCardOwner=").append(creditCardPayment.getCardOwner()).append("&");
			messageLogString.append("trnCardNumber=")
					.append(CreditCardUtils.maskCardNumber(creditCardPayment.getCreditCardNumber())).append("&");
			messageLogString.append("trnExpMonth=").append(creditCardPayment.getExpirationMonth()).append("&");
			messageLogString.append("trnExpYear=").append(creditCardPayment.getExpirationYear()).append("&");
			messageLogString.append("trnCardCvd=").append(creditCardPayment.getCredidCardValidationNumber())
					.append("&");
			messageLogString.append("trnAmount=").append(amnt).append("&");

			messageLogString.append("ordName=").append(nm.toString()).append("&");
			messageLogString.append("ordAddress1=").append(customer.getBilling().getAddress()).append("&");
			messageLogString.append("ordCity=").append(customer.getBilling().getCity()).append("&");

			messageLogString.append("ordProvince=").append(stateProvince).append("&");
			messageLogString.append("ordPostalCode=").append(customer.getBilling().getPostalCode()).append("&");
			messageLogString.append("ordCountry=").append(customer.getBilling().getCountry().getName()).append("&");
			messageLogString.append("ordPhoneNumber=").append(customer.getBilling().getTelephone()).append("&");
			messageLogString.append("ordEmailAddress=").append(customer.getEmailAddress());

			LOGGER.debug("REQUEST SENT TO BEANSTREAM -> " + messageLogString.toString());

			URL postURL = new URL(server.toString());
			conn = (HttpURLConnection) postURL.openConnection();

			Transaction response = this.sendTransaction(orderNumber, store, messageString.toString(), transactionType,
					type, payment.getPaymentType(), amount, configuration, module);

			return response;

		} catch (Exception e) {

			if (e instanceof BusinessException)
				throw (BusinessException) e;
			throw new BusinessException("Error while processing BeanStream transaction", e);

		} finally {

			if (conn != null) {
				try {
					conn.disconnect();
				} catch (Exception ignore) {
				}
			}
		}

	}

}
