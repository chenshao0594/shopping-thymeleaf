package com.shoppay.core.payment.model;

import java.math.BigDecimal;

import com.shoppay.core.payment.gateway.paypal.PaypalPaymentIntent;
import com.shoppay.core.payment.gateway.paypal.PaypalPaymentMethod;

public class PaymentContext {

	private BigDecimal total;
	private String currency; 
	private PaypalPaymentMethod method;
	private PaypalPaymentIntent intent; 
	private String description;
	private String cancelUrl; 
	private String successUrl;
	public PaymentContext(BigDecimal total, String currency) {
		this.total =total;
		this.currency = currency;
	} 

	

	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public PaypalPaymentMethod getMethod() {
		return method;
	}
	public void setMethod(PaypalPaymentMethod method) {
		this.method = method;
	}
	public PaypalPaymentIntent getIntent() {
		return intent;
	}
	public void setIntent(PaypalPaymentIntent intent) {
		this.intent = intent;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCancelUrl() {
		return cancelUrl;
	}
	public void setCancelUrl(String cancelUrl) {
		this.cancelUrl = cancelUrl;
	}
	public String getSuccessUrl() {
		return successUrl;
	}
	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}



	public static class Builder {
		private BigDecimal total;
		private String currency; 
		private PaypalPaymentMethod method;
		private PaypalPaymentIntent intent; 
		private String description;
		private String cancelUrl; 
		private String successUrl;
		public Builder(BigDecimal total, String currency) {
			this.total =total;
			this.currency = currency;
		}

		public Builder method(PaypalPaymentMethod method) {
			this.method = method;
			return this;
		}
		public Builder intent(PaypalPaymentIntent intent) {
			this.intent = intent;
			return this;
		}
		public Builder description(String description) {
			this.description = description;
			return this;
		}
		public Builder cancelUrl(String cancelUrl) {
			this.cancelUrl = cancelUrl;
			return this;
		}
		public Builder successUrl(String successUrl) {
			this.successUrl = successUrl;
			return this;
		}

		public PaymentContext build() {
			return new PaymentContext(this);

		}

	}
	private PaymentContext(Builder b) {
		total =b.total;
		currency =b.currency; 
		method =b.method;
		intent = b.intent; 
		description =b.description;
		cancelUrl = b.cancelUrl; 
		successUrl = b.successUrl;

	}

}
