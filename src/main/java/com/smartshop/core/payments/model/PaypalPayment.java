package com.smartshop.core.payments.model;

import com.smartshop.core.payment.enumeration.PaymentType;

public class PaypalPayment extends Payment {

	// express checkout
	private String payerId;
	private String paymentToken;

	public PaypalPayment() {
		super.setPaymentType(PaymentType.PAYPAL);
	}

	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}

	public String getPayerId() {
		return payerId;
	}

	public void setPaymentToken(String paymentToken) {
		this.paymentToken = paymentToken;
	}

	public String getPaymentToken() {
		return paymentToken;
	}

}