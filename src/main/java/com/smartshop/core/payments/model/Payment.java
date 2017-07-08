package com.smartshop.core.payments.model;

import java.util.Map;

import com.smartshop.core.enumeration.TransactionType;
import com.smartshop.core.payment.enumeration.PaymentType;
import com.smartshop.domain.Currency;

public class Payment {
	private PaymentType paymentType;
	private TransactionType transactionType = TransactionType.AUTHORIZECAPTURE;
	private String moduleName;
	private Map<String, String> paymentMetaData = null;
	private Currency currency;

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public Map<String, String> getPaymentMetaData() {
		return paymentMetaData;
	}

	public void setPaymentMetaData(Map<String, String> paymentMetaData) {
		this.paymentMetaData = paymentMetaData;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

}
