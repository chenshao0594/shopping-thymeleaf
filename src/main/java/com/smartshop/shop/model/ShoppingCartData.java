package com.smartshop.shop.model;

import java.io.Serializable;
import java.util.List;

import com.smartshop.core.cart.ShoppingCartItem;

public class ShoppingCartData implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -3563109569242923101L;

	private String message;
	private String code;
	private int quantity;
	private String total;
	private String subTotal;

	// private List<OrderTotal> totals;// calculated from OrderTotalSummary
	private List<ShoppingCartItem> shoppingCartItems;
	private List<ShoppingCartItem> unavailables;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

	public List<ShoppingCartItem> getShoppingCartItems() {
		return shoppingCartItems;
	}

	public void setShoppingCartItems(List<ShoppingCartItem> shoppingCartItems) {
		this.shoppingCartItems = shoppingCartItems;
	}

	public List<ShoppingCartItem> getUnavailables() {
		return unavailables;
	}

	public void setUnavailables(List<ShoppingCartItem> unavailables) {
		this.unavailables = unavailables;
	}

}
