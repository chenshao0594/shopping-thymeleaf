package com.smartshop.shop.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.smartshop.core.common.Billing;

public class ShoppingOrderContext implements Serializable {

	private String uuid;

	private long userId;

	private Set<Long> cartItemIds = new HashSet<Long>();
	private Billing billing;
	private String paymentType;

	public Set<Long> getCartItemIds() {
		return cartItemIds;
	}

	public void setCartItemIds(Set<Long> cartItemIds) {
		this.cartItemIds = cartItemIds;
	}

	public Billing getBilling() {
		return billing;
	}

	public void setBilling(Billing billing) {
		this.billing = billing;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ShoppingOrderContext [uuid=" + uuid + ", cartItemIds=" + cartItemIds + ", billing=" + billing
				+ ", paymentType=" + paymentType + "]";
	}

}
