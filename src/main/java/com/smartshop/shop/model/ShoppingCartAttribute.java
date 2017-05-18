package com.smartshop.shop.model;

import java.io.Serializable;

public class ShoppingCartAttribute implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -7019917391768826889L;
	private long optionId;
	private long optionValueId;
	private long attributeId;
	private String optionName;
	private String optionValue;

	public long getOptionId() {
		return optionId;
	}

	public void setOptionId(long optionId) {
		this.optionId = optionId;
	}

	public long getOptionValueId() {
		return optionValueId;
	}

	public void setOptionValueId(long optionValueId) {
		this.optionValueId = optionValueId;
	}

	public long getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(long attributeId) {
		this.attributeId = attributeId;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public String getOptionValue() {
		return optionValue;
	}

	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}

}
