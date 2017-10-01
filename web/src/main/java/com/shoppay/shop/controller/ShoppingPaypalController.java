package com.shoppay.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.shoppay.common.utils.URLUtils;
import com.shoppay.core.order.SalesOrder;
import com.shoppay.core.order.enumeration.SalesOrderStatus;
import com.shoppay.core.order.service.SalesOrderService;
import com.shoppay.core.payment.enumeration.PaymentType;
import com.shoppay.core.payment.gateway.paypal.PaypalPaymentIntent;
import com.shoppay.core.payment.gateway.paypal.PaypalPaymentMethod;
import com.shoppay.core.payment.gateway.paypal.PaypalPaymentService;
import com.shoppay.core.payment.model.PaymentContext;
import com.shoppay.shop.model.PaymentInfo;
import com.shoppay.web.constants.ShoppingControllerConstants;



@Controller("ShoppingPaypalController")
@RequestMapping("/paypal")
public class ShoppingPaypalController {
	
	public static final String PAYPAL_SUCCESS_URL = "paypal/success";
	public static final String PAYPAL_CANCEL_URL = "paypal/cancel";
	
	private Logger LOGGER = LoggerFactory.getLogger(ShoppingPaypalController.class);
	
	@Autowired
	private PaypalPaymentService paypalService;
	
	
	@Autowired
	private SalesOrderService salesOrderService;
	
	@GetMapping("/pay/{orderId}")
	public String pay(@PathVariable long orderId,  PaymentInfo paymentInfo, HttpServletRequest request){
		String cancelUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_CANCEL_URL+"/"+orderId;
		String successUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_SUCCESS_URL+"/"+orderId;
		SalesOrder order = salesOrderService.findOne(orderId);
		PaymentContext paymentContext = new PaymentContext.Builder(order.getTotal(), order.getCurrency().getCurrencyCode())
				.method(PaypalPaymentMethod.paypal)
				.intent(PaypalPaymentIntent.sale)
				.description("order descripton")
				.cancelUrl(cancelUrl)
				.successUrl(successUrl)
				.build();
		
		try {
			Payment payment = paypalService.createPayment(paymentContext);
			for(Links links : payment.getLinks()){
				if(links.getRel().equals("approval_url")){
					return "redirect:" + links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			LOGGER.error(e.getMessage());
		}
		return "redirect:/";
	}

	@GetMapping(value = "/cancel/{orderId}")
	public String cancelPay(@PathVariable long orderId){
		System.out.println("path order id is " + orderId);
		
		
		return ShoppingControllerConstants.Payment.cancel;
	}

	@GetMapping(value = "/success/{orderId}")
	public String successPay(@PathVariable long orderId, @RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
		LOGGER.info("pay success path order id is {} ", orderId);
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			LOGGER.info("paypal  info is {}" , payment);
			if(payment.getState().equals("approved")){
				SalesOrder order = this.salesOrderService.findOne(orderId);
				order.setPaymentCode(payment.getId());
				order.setPaymentType(PaymentType.PAYPAL);
				order.setStatus(SalesOrderStatus.PROCESSED);
				this.salesOrderService.update(order);
				return ShoppingControllerConstants.Payment.success;
			}
		} catch (PayPalRESTException e) {
			LOGGER.error(e.getMessage());
		}
		return "redirect:/";
	}

}
