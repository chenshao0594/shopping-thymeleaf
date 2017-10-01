package com.shoppay.core.model;

import com.shoppay.core.payment.enumeration.PaymentType;

public class PaymentInfo {
	private long orderId;
	private PaymentType type;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

}
