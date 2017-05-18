package com.smartshop.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

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

/**
 * Service Interface for managing Product.
 */
public interface SalesOrderService extends AbstractDomainService<SalesOrder, Long> {

	void addOrderStatusHistory(SalesOrder order, OrderStatusHistory history) throws BusinessException;

	/**
	 * Can be used to calculates the final prices of all items contained in
	 * checkout page
	 *
	 * @param orderSummary
	 * @param customer
	 * @param store
	 * @param language
	 * @return
	 * @throws BusinessException
	 */
	OrderTotalSummary caculateOrderTotal(OrderSummary orderSummary, Customer customer, MerchantStore store)
			throws BusinessException;

	/**
	 * Can be used to calculates the final prices of all items contained in a
	 * ShoppingCart
	 *
	 * @param orderSummary
	 * @param store
	 * @param language
	 * @return
	 * @throws BusinessException
	 */
	OrderTotalSummary caculateOrderTotal(OrderSummary orderSummary, MerchantStore store) throws BusinessException;

	/**
	 * Can be used to calculates the final prices of all items contained in
	 * checkout page
	 *
	 * @param shoppingCart
	 * @param customer
	 * @param store
	 * @param language
	 * @return @return {@link OrderTotalSummary}
	 * @throws BusinessException
	 */
	OrderTotalSummary calculateShoppingCartTotal(final Cart shoppingCart, final Customer customer,
			final MerchantStore store) throws BusinessException;

	/**
	 * Can be used to calculates the final prices of all items contained in a
	 * ShoppingCart
	 *
	 * @param shoppingCart
	 * @param store
	 * @param language
	 * @return {@link OrderTotalSummary}
	 * @throws BusinessException
	 */
	OrderTotalSummary calculateShoppingCartTotal(final Cart shoppingCart, final MerchantStore store)
			throws BusinessException;

	ByteArrayOutputStream generateInvoice(MerchantStore store, SalesOrder order) throws BusinessException;

	SalesOrder getOrder(Long id);

	// List<Order> listByStore(MerchantStore merchantStore);

	/**
	 * For finding orders. Mainly used in the administration tool
	 *
	 * @param store
	 * @param criteria
	 * @return
	 */
	// OrderList listByStore(MerchantStore store, OrderCriteria criteria);

	void saveOrUpdate(SalesOrder order) throws BusinessException;

	SalesOrder processOrder(SalesOrder order, Customer customer, List<CartItem> items, OrderTotalSummary summary,
			Payment payment, MerchantStore store) throws BusinessException;

	SalesOrder processOrder(SalesOrder order, Customer customer, List<CartItem> items, OrderTotalSummary summary,
			Payment payment, Transaction transaction, MerchantStore store) throws BusinessException;

	/**
	 * Determines if an Order has download files
	 *
	 * @param order
	 * @return
	 * @throws BusinessException
	 */
	boolean hasDownloadFiles(SalesOrder order) throws BusinessException;

}