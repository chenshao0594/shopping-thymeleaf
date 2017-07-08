package com.smartshop.core.order.service;

import java.util.List;

import com.smartshop.core.cart.Cart;
import com.smartshop.core.order.SalesOrder;
import com.smartshop.core.order.model.SalesOrderTotalSummary;
import com.smartshop.domain.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.exception.BusinessException;
import com.smartshop.service.AbstractDomainService;

/**
 * Service Interface for managing Product.
 */
public interface SalesOrderService extends AbstractDomainService<SalesOrder, Long> {

	List<SalesOrder> findByCustomer(Customer customer);

	SalesOrderTotalSummary calculateShoppingCartTotal(Cart shoppingCart, MerchantStore store) throws BusinessException;

	SalesOrderTotalSummary calculateShoppingCartTotal(Cart cartModel, Customer customer, MerchantStore store);

}