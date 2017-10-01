package com.shoppay.core.model;

public class CategoryCountInfo {
	private String code;
	private int count;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "CategoryCountInfo [code=" + code + ", count=" + count + "]";
	}

}
