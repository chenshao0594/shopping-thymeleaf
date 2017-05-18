package com.smartshop.core.cart.service;

import com.smartshop.core.cart.Cart;
import com.smartshop.core.order.model.OrderTotalSummary;
import com.smartshop.domain.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.exception.BusinessException;

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
	public OrderTotalSummary calculate(final Cart cartModel, final Customer customer, final MerchantStore store)
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
	public OrderTotalSummary calculate(final Cart cartModel, final MerchantStore store) throws BusinessException;
}
