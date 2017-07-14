package com.shoppay.core.payment.service;

import java.math.BigDecimal;
import java.util.List;

import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.core.customer.Customer;
import com.shoppay.core.order.SalesOrder;
import com.shoppay.core.payment.PaymentConfiguration;
import com.shoppay.core.payment.Transaction;
import com.shoppay.core.payments.model.Payment;
import com.shoppay.shop.model.ShoppingCartItem;

public interface PaymentService {
	
	public void validatePaymentConfiguration(PaymentConfiguration integrationConfiguration, MerchantStore store)
			throws BusinessException;

	public Transaction initTransaction(MerchantStore store, Customer customer, BigDecimal amount, Payment payment,
			PaymentConfiguration configuration) throws BusinessException;

	public Transaction authorize(MerchantStore store, Customer customer, List<ShoppingCartItem> items,
			BigDecimal amount, Payment payment, PaymentConfiguration configuration) throws BusinessException;

	public Transaction capture(MerchantStore store, Customer customer, SalesOrder order,
			Transaction capturableTransaction, PaymentConfiguration configuration) throws BusinessException;

	public Transaction authorizeAndCapture(MerchantStore store, Customer customer, List<ShoppingCartItem> items,
			BigDecimal amount, Payment payment, PaymentConfiguration configuration) throws BusinessException;

	public Transaction refund(boolean partial, MerchantStore store, Transaction transaction, SalesOrder order,
			BigDecimal amount, PaymentConfiguration configuration) throws BusinessException;

}