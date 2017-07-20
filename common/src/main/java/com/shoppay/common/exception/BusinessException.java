package com.shoppay.common.exception;

import java.util.Locale;

import com.shoppay.common.service.MessageService;

public final class BusinessException extends Exception {

	private static final long serialVersionUID = -6854945379036729034L;
	private int exceptionType = 0;// regular error
	private Object[] args = new Object[] { };

	public final static int EXCEPTION_VALIDATION = 99;
	public final static int EXCEPTION_PAYMENT_DECLINED = 100;
	public final static int EXCEPTION_TRANSACTION_DECLINED = 101;

	private String messageCode = null;

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public BusinessException() {
		super();
	}

	public BusinessException(String messageCode) {
		super();
		this.messageCode = messageCode;
	}

	public BusinessException(String messageCode, Object... args) {
		this.messageCode = messageCode;
		this.args = args;
	}
	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(int exceptionType) {
		super();
		this.exceptionType = exceptionType;
	}

	public BusinessException(int exceptionType, String message) {
		super(message);
		this.exceptionType = exceptionType;
	}

	public BusinessException(int exceptionType, String message, String messageCode) {
		super(message);
		this.messageCode = messageCode;
		this.exceptionType = exceptionType;
	}

	public int getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(int exceptionType) {
		this.exceptionType = exceptionType;
	}

	public String getMessageCode() {
		return messageCode;
	}


	@Override
	public String getMessage() {
		return MessageService.getInstance().getMessage(this.messageCode, this.args, Locale.ENGLISH);

	}
}
