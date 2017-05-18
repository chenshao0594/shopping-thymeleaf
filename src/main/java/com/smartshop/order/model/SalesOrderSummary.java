package com.smartshop.order.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.smartshop.core.cart.CartItem;
import com.smartshop.core.shipping.model.ShippingSummary;

public class SalesOrderSummary implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8683318345130020683L;
	private OrderSummaryEnum orderSummaryType = OrderSummaryEnum.ORDERTOTAL;
	private ShippingSummary shippingSummary;
	private List<CartItem> products = new ArrayList<CartItem>();

	public void setProducts(List<CartItem> products) {
		this.products = products;
	}

	public List<CartItem> getProducts() {
		return products;
	}

	public void setShippingSummary(ShippingSummary shippingSummary) {
		this.shippingSummary = shippingSummary;
	}

	public ShippingSummary getShippingSummary() {
		return shippingSummary;
	}

	public OrderSummaryEnum getOrderSummaryType() {
		return orderSummaryType;
	}

	public void setOrderSummaryType(OrderSummaryEnum orderSummaryType) {
		this.orderSummaryType = orderSummaryType;
	}

}
