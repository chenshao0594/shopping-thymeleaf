package com.smartshop.order.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.smartshop.core.cart.CartItem;
import com.smartshop.core.shipping.model.ShippingSummary;

public class SalesOrderSummary implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8683318345130020683L;
	private OrderSummaryEnum orderSummaryType = OrderSummaryEnum.ORDERTOTAL;
	private ShippingSummary shippingSummary;
	private Set<CartItem> cartItems = new HashSet<CartItem>();

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
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
