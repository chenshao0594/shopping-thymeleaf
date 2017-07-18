package com.shoppay.core.order.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.shoppay.core.cart.CartItem;
import com.shoppay.core.enumeration.OrderSummaryEnum;
import com.shoppay.core.shipping.model.ShippingSummary;

public class SalesOrderSummary implements Serializable {

	/**
	 *
	 */
	private OrderSummaryEnum orderSummaryType = OrderSummaryEnum.ORDERTOTAL;
	private static final long serialVersionUID = 1L;
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
