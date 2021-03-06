package com.shoppay.core.order.enumeration;

public enum SalesOrderStatus {
	INITED("inited"), ORDERED("ordered"), PROCESSED("processed"), DELIVERED("delivered"), REFUNDED(
			"refunded"), CANCELED("canceled"),DELETED("deleted");

	private String value;

	private SalesOrderStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
