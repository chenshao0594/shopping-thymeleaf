package com.smartshop.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartshop.core.cart.Cart;
import com.smartshop.core.cart.CartItem;
import com.smartshop.core.order.OrderStatusHistory;
import com.smartshop.core.order.SalesOrder;
import com.smartshop.core.order.model.OrderSummary;
import com.smartshop.core.order.model.OrderTotalSummary;
import com.smartshop.core.payments.model.Payment;
import com.smartshop.domain.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.domain.Transaction;
import com.smartshop.exception.BusinessException;
import com.smartshop.repository.SalesOrderRepository;
import com.smartshop.repository.search.SalesOrderSearchRepository;
import com.smartshop.service.SalesOrderService;

/**
 * Service Implementation for managing Product.
 */
@Service
@Transactional
public class SalesOrderServiceImpl extends AbstractDomainServiceImpl<SalesOrder, Long> implements SalesOrderService {

	private final Logger LOGGER = LoggerFactory.getLogger(SalesOrderServiceImpl.class);
	private final SalesOrderRepository salesOrderRepository;
	private final SalesOrderSearchRepository salesOrderSearchRepository;

	public SalesOrderServiceImpl(SalesOrderRepository salesOrderRepository,
			SalesOrderSearchRepository salesOrderSearchRepository) {
		super(salesOrderRepository, salesOrderSearchRepository);
		this.salesOrderRepository = salesOrderRepository;
		this.salesOrderSearchRepository = salesOrderSearchRepository;
	}

	@Override
	public void addOrderStatusHistory(SalesOrder order, OrderStatusHistory history) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public OrderTotalSummary caculateOrderTotal(OrderSummary orderSummary, Customer customer, MerchantStore store)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderTotalSummary caculateOrderTotal(OrderSummary orderSummary, MerchantStore store)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderTotalSummary calculateShoppingCartTotal(Cart shoppingCart, Customer customer, MerchantStore store)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderTotalSummary calculateShoppingCartTotal(Cart shoppingCart, MerchantStore store)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ByteArrayOutputStream generateInvoice(MerchantStore store, SalesOrder order) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SalesOrder getOrder(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(SalesOrder order) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public SalesOrder processOrder(SalesOrder order, Customer customer, List<CartItem> items, OrderTotalSummary summary,
			Payment payment, MerchantStore store) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SalesOrder processOrder(SalesOrder order, Customer customer, List<CartItem> items, OrderTotalSummary summary,
			Payment payment, Transaction transaction, MerchantStore store) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasDownloadFiles(SalesOrder order) throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

}