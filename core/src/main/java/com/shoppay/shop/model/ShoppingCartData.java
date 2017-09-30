package com.shoppay.shop.model;

import java.io.Serializable;
import java.util.List;

import com.shoppay.order.model.OrderTotal;

public class ShoppingCartData implements Serializable {

	private static final long serialVersionUID = -3563109569242923101L;

	private Long id;
	private String message;
	private String code;
	private int quantity;
	private String total;
	private String subTotal;
	private List<OrderTotal> totals;// calculated from OrderTotalSummary
	private List<ShoppingCartItem> shoppingCartItems;

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

	/*
	 * public List<ShoppingCartItem> getUnavailables() { return unavailables; }
	 * 
	 * public void setUnavailables(List<ShoppingCartItem> unavailables) {
	 * this.unavailables = unavailables; }
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<OrderTotal> getTotals() {
		return totals;
	}

	public void setTotals(List<OrderTotal> totals) {
		this.totals = totals;
	}

}
