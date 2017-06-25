package com.smartshop.shop.controller;

import javax.servlet.http.HttpServletRequest;

import com.smartshop.shop.utils.SessionUtil;

public abstract class AbstractShoppingController {
	/**
	 * Method which will help to retrieving values from Session based on the key
	 * being passed to the method.
	 *
	 * @param key
	 * @return value stored in session corresponding to the key
	 */
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