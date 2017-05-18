package com.smartshop.order.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderTotal implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5367687627551343926L;
	private String title;
	private String text;
	private String code;
	private int order;
	private String module;
	private BigDecimal value;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}
