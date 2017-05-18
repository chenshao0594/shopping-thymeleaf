package com.smartshop.core.order.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.smartshop.core.enumeration.OrderSummaryType;
import com.smartshop.core.shipping.model.ShippingSummary;
import com.smartshop.shop.model.ShoppingCartItem;

public class OrderSummary implements Serializable {

	/**
	 *
	 */
	private OrderSummaryType orderSummaryType = OrderSummaryType.ORDERTOTAL;
	private static final long serialVersionUID = 1L;
	private ShippingSummary shippingSummary;
	private List<ShoppingCartItem> products = new ArrayList<ShoppingCartItem>();

	public void setProducts(List<ShoppingCartItem> products) {
		this.products = products;
	}

	public List<ShoppingCartItem> getProducts() {
		return products;
	}

	public void setShippingSummary(ShippingSummary shippingSummary) {
		this.shippingSummary = shippingSummary;
	}

	public ShippingSummary getShippingSummary() {
		return shippingSummary;
	}

	public OrderSummaryType getOrderSummaryType() {
		return orderSummaryType;
	}

	public void setOrderSummaryType(OrderSummaryType orderSummaryType) {
		this.orderSummaryType = orderSummaryType;
	}

}
