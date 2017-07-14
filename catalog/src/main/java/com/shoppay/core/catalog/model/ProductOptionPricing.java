package com.shoppay.core.catalog.model;

import java.util.Set;

public class ProductOptionPricing {
	private Long skuId;
	private String standardPrice;
	private String retailPrice;
	private Set<Long> selectedOptions;

	public String getStandardPrice() {
		return standardPrice;
	}

	public void setStandardPrice(String standardPrice) {
		this.standardPrice = standardPrice;
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

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	@Override
	public String toString() {
		return "ProductOptionPricing [skuId=" + skuId + ", standardPrice=" + standardPrice + ", retailPrice="
				+ retailPrice + ", selectedOptions=" + selectedOptions + "]";
	}

}
