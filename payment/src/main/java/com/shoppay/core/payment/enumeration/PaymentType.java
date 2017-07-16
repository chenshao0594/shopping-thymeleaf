package com.shoppay.core.payment.enumeration;

public enum PaymentType {
	CREDITCARD("creditcard"), FREE("free"), COD("cod"), MONEYORDER("moneyorder"), PAYPAL("paypal"), STRIPE(
			"strip"), WEPAY("wepay");

	private String paymentType;

	PaymentType(String type) {
		paymentType = type;
	}

	public static PaymentType fromString(String text) {
		if (text != null) {
			for (PaymentType b : PaymentType.values()) {
				String payemntType = text.toUpperCase();
				if (payemntType.equalsIgnoreCase(b.name())) {
					return b;
				}
			}
		}
		return null;
	}
}
