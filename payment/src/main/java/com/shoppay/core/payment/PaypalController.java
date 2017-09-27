package com.shoppay.core.payment;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.shoppay.common.utils.URLUtils;
import com.shoppay.core.payment.gateway.paypal.PaypalPaymentIntent;
import com.shoppay.core.payment.gateway.paypal.PaypalPaymentMethod;
import com.shoppay.core.payment.gateway.paypal.PaypalPaymentService;
import com.shoppay.core.payment.model.PaymentContext;

public class PaypalController {
	
	public static final String PAYPAL_SUCCESS_URL = "paypal/success";
	public static final String PAYPAL_CANCEL_URL = "paypal/cancel";
	
	private Logger LOGGER = LoggerFactory.getLogger(PaypalController.class);
	
	@Autowired
	private PaypalPaymentService paypalService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(){
		return "index";
	}
	
	@PostMapping("/pay")
	public String pay(HttpServletRequest request){
		String cancelUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_CANCEL_URL;
		String successUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_SUCCESS_URL;
		PaymentContext paymentContext = new PaymentContext.Builder(4.00, "USD").method(PaypalPaymentMethod.paypal)
				.intent(PaypalPaymentIntent.sale).description("paymentDescription")
				.cancelUrl(cancelUrl).successUrl(successUrl).build();
		
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

	@GetMapping(value = PAYPAL_CANCEL_URL)
	public String cancelPay(){
		return "cancel";
	}

	@GetMapping(value = PAYPAL_SUCCESS_URL)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if(payment.getState().equals("approved")){
				return "success";
			}
		} catch (PayPalRESTException e) {
			LOGGER.error(e.getMessage());
		}
		return "redirect:/";
	}

}
