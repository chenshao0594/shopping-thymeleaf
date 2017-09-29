package com.shoppay.shop.controller;

import javax.servlet.http.HttpServletRequest;

import com.shoppay.shop.utils.SessionUtil;

public abstract class AbstractShoppingController {
	@SuppressWarnings("unchecked")
	protected <T> T getSessionAttribute(final String key, HttpServletRequest request) {
		return (T) SessionUtil.getSessionAttribute(key, request);

	}

	protected void setSessionAttribute(final String key, final Object value, HttpServletRequest request) {
		SessionUtil.setSessionAttribute(key, value, request);
	}

	protected void removeAttribute(final String key, HttpServletRequest request) {
		SessionUtil.removeSessionAttribute(key, request);
	}
}