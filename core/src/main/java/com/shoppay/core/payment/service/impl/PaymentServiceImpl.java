package com.shoppay.core.payment.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.core.customer.Customer;
import com.shoppay.core.model.ShoppingCartItem;
import com.shoppay.core.order.SalesOrder;
import com.shoppay.core.payment.PaymentConfiguration;
import com.shoppay.core.payment.model.Payment;
import com.shoppay.core.payment.service.PaymentService;
import com.shoppay.core.transaction.Transaction;

public class PaymentServiceImpl implements PaymentService {

	@Override
	public void validatePaymentConfiguration(PaymentConfiguration integrationConfiguration, MerchantStore store)
			throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public Transaction initTransaction(MerchantStore store, Customer customer, BigDecimal amount, Payment payment,
			PaymentConfiguration configuration) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction authorize(MerchantStore store, Customer customer, List<ShoppingCartItem> items,
			BigDecimal amount, Payment payment, PaymentConfiguration configuration) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction capture(MerchantStore store, Customer customer, SalesOrder order,
			Transaction capturableTransaction, PaymentConfiguration configuration) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction authorizeAndCapture(MerchantStore store, Customer customer, List<ShoppingCartItem> items,
			BigDecimal amount, Payment payment, PaymentConfiguration configuration) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction refund(boolean partial, MerchantStore store, Transaction transaction, SalesOrder order,
			BigDecimal amount, PaymentConfiguration configuration) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
}
