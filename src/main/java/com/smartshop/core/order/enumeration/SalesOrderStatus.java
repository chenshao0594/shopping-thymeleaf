package com.smartshop.core.order.enumeration;

public enum SalesOrderStatus {
	ORDERED("ordered"), PROCESSED("processed"), DELIVERED("delivered"), REFUNDED("refunded"), CANCELED("canceled"),;

	private String value;

	private SalesOrderStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
