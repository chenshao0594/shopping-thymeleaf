package com.shoppay.core.order.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.common.service.AbstractDomainService;
import com.shoppay.core.cart.Cart;
import com.shoppay.core.customer.Customer;
import com.shoppay.core.order.SalesOrder;
import com.shoppay.core.order.model.SalesOrderTotalSummary;

/**
 * Service Interface for managing Product.
 */
public interface SalesOrderService extends AbstractDomainService<SalesOrder, Long> {


	SalesOrderTotalSummary calculateShoppingCartTotal(Cart shoppingCart, MerchantStore store) throws BusinessException;

	SalesOrderTotalSummary calculateShoppingCartTotal(Cart cartModel, Customer customer, MerchantStore store);

	Page<SalesOrder> findByCustomerAndStore(Customer customer, MerchantStore store, Pageable pageable);

}