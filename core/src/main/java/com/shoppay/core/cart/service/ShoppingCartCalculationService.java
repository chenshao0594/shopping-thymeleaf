package com.shoppay.core.cart.service;

import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.core.cart.Cart;
import com.shoppay.core.customer.Customer;
import com.shoppay.core.order.model.SalesOrderTotalSummary;

public interface ShoppingCartCalculationService {
	/**
	 * Method which will be used to calculate price for each line items as well
	 * Total and Sub-total for {@link Cart}.
	 *
	 * @param cartModel
	 *            ShoopingCart mode representing underlying DB object
	 * @param customer
	 * @param store
	 * @param language
	 * @throws BusinessException
	 */
	public SalesOrderTotalSummary calculate(final Cart cartModel, final Customer customer, final MerchantStore store)
			throws BusinessException;

	/**
	 * Method which will be used to calculate price for each line items as well
	 * Total and Sub-total for {@link Cart}.
	 *
	 * @param cartModel
	 *            ShoopingCart mode representing underlying DB object
	 * @param store
	 * @param language
	 * @throws BusinessException
	 */
	public SalesOrderTotalSummary calculate(final Cart cartModel, final MerchantStore store) throws BusinessException;
}
