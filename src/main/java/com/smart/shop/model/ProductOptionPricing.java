package com.smart.shop.model;

import java.util.Set;

public class ProductOptionPricing {
	private Long skuId;
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

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	@Override
	public String toString() {
		return "ProductOptionPricing [skuId=" + skuId + ", salePrice=" + salePrice + ", retailPrice=" + retailPrice
				+ ", selectedOptions=" + selectedOptions + "]";
	}

}
