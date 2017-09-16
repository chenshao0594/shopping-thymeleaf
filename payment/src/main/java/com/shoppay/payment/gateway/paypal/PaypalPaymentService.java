package com.shoppay.payment.gateway.paypal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.shoppay.core.payment.model.PaymentContext;
import com.shoppay.core.payment.provider.PaymentGetewayService;

@Service("PaypalProviderService")
public class PaypalPaymentService  implements PaymentGetewayService{

	@Override
	public void payment() {
	}
	
	@Autowired
	private APIContext apiContext;
	
	public Payment createPayment(PaymentContext paymentContext) throws PayPalRESTException{
		Amount amount = new Amount();
		amount.setCurrency(paymentContext.getCurrency());
		amount.setTotal(String.format("%.2f", paymentContext.getTotal()));
		Transaction transaction = new Transaction();
		transaction.setDescription(paymentContext.getDescription());
		transaction.setAmount(amount);

		List<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction);

		Payer payer = new Payer();
		payer.setPaymentMethod(paymentContext.getMethod().toString());
		Payment payment = new Payment();
		payment.setIntent(paymentContext.getIntent().toString());
		payment.setPayer(payer);
		payment.setTransactions(transactions);
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl(paymentContext.getCancelUrl());
		redirectUrls.setReturnUrl(paymentContext.getSuccessUrl());
		payment.setRedirectUrls(redirectUrls);
		return payment.create(apiContext);
	}
	
	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{
		Payment payment = new Payment();
		payment.setId(paymentId);
		PaymentExecution paymentExecute = new PaymentExecution();
		paymentExecute.setPayerId(payerId);
		return payment.execute(apiContext, paymentExecute);
	}
	public void refund(){
		
	}
}
