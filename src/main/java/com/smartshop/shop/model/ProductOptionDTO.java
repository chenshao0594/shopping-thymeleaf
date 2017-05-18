package com.smartshop.shop.model;

import java.util.Map;

public class ProductOptionDTO {
	private Long id;
	private String type;
	private Map<Long, String> values;
	private String selectedValue;

	@SuppressWarnings("unused")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@SuppressWarnings("unused")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@SuppressWarnings("unused")
	public Map<Long, String> getValues() {
		return values;
	}

	public void setValues(Map<Long, String> values) {
		this.values = values;
	}

	@SuppressWarnings("unused")
	public String getSelectedValue() {
		return selectedValue;
	}

	@SuppressWarnings("unused")
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (!getClass().isAssignableFrom(o.getClass())) {
			return false;
		}

		ProductOptionDTO that = (ProductOptionDTO) o;

		if (id != null ? !id.equals(that.id) : that.id != null) {
			return false;
		}
		if (selectedValue != null ? !selectedValue.equals(that.selectedValue) : that.selectedValue != null) {
			return false;
		}
		if (type != null ? !type.equals(that.type) : that.type != null) {
			return false;
		}
		if (values != null ? !values.equals(that.values) : that.values != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + (values != null ? values.hashCode() : 0);
		result = 31 * result + (selectedValue != null ? selectedValue.hashCode() : 0);
		return result;
	}
}
