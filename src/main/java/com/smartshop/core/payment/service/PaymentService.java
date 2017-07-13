package com.smartshop.core.payment.service;

import java.math.BigDecimal;
import java.util.List;

import com.smartshop.core.customer.Customer;
import com.smartshop.core.order.SalesOrder;
import com.smartshop.core.payment.PaymentConfiguration;
import com.smartshop.core.payment.Transaction;
import com.smartshop.core.payments.model.Payment;
import com.smartshop.domain.MerchantStore;
import com.smartshop.exception.BusinessException;
import com.smartshop.shop.model.ShoppingCartItem;

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