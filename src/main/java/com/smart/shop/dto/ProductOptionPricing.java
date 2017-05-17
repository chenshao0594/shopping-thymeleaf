package com.smart.shop.dto;

import java.util.Set;

public class ProductOptionPricing {
	private String salePrice;
	private String retailPrice;
	private Set<Long> selectedOptions;

	public String getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}

	public String getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(String retailPrice) {
		this.retailPrice = retailPrice;
	}

	public Set<Long> getSelectedOptions() {
		return selectedOptions;
	}

	public void setSelectedOptions(Set<Long> selectedOptions) {
		this.selectedOptions = selectedOptions;
	}

}
