package com.shoppay.order.model;

import java.util.List;

public abstract class OrderTotalVariation {
	List<OrderTotal> variations = null;

	public List<OrderTotal> getVariations() {
		return variations;
	}

	public void setVariations(List<OrderTotal> variations) {
		this.variations = variations;
	}

}
