package com.smartshop.core.cart.service.impl;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import com.smartshop.core.cart.Cart;
import com.smartshop.core.cart.CartItem;
import com.smartshop.core.cart.service.CartService;
import com.smartshop.core.cart.service.ShoppingCartCalculationService;
import com.smartshop.core.order.model.SalesOrderTotalSummary;
import com.smartshop.core.order.service.SalesOrderService;
import com.smartshop.domain.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.exception.BusinessException;

@Service
public class ShoppingCartCalculationServiceImpl implements ShoppingCartCalculationService {
	@Inject
	private CartService shoppingCartService;

	@Inject
	private SalesOrderService orderService;

	/**
	 * <p>
	 * Method used to recalculate state of shopping cart every time any change
	 * has been made to underlying {@link Cart} object in DB.
	 * </p>
	 * Following operations will be performed by this method.
	 *
	 * <li>Calculate price for each {@link CartItem} and update it.</li>
	 * <p>
	 * This method is backbone method for all price calculation related to
	 * shopping cart.
	 * </p>
	 *
	 * @see OrderServiceImpl
	 *
	 * @param cartModel
	 * @param customer
	 * @param store
	 * @param language
	 * @throws BusinessException
	 */
	@Override
	public SalesOrderTotalSummary calculate(final Cart cartModel, final Customer customer, final MerchantStore store)
			throws BusinessException {

		Validate.notNull(cartModel, "cart cannot be null");
		Validate.notNull(cartModel.getLineItems(), "Cart should have line items.");
		Validate.notNull(store, "MerchantStore cannot be null");
		Validate.notNull(customer, "Customer cannot be null");
		SalesOrderTotalSummary orderTotalSummary = orderService.calculateShoppingCartTotal(cartModel, customer, store);
		// updateCart(cartModel);
		return orderTotalSummary;

	}

	/**
	 * <p>
	 * Method used to recalculate state of shopping cart every time any change
	 * has been made to underlying {@link Cart} object in DB.
	 * </p>
	 * Following operations will be performed by this method.
	 *
	 * <li>Calculate price for each {@link CartItem} and update it.</li>
	 * <p>
	 * This method is backbone method for all price calculation related to
	 * shopping cart.
	 * </p>
	 *
	 * @see OrderServiceImpl
	 *
	 * @param cartModel
	 * @param store
	 * @param language
	 * @throws BusinessException
	 */
	@Override
	public SalesOrderTotalSummary calculate(final Cart cart, final MerchantStore store) throws BusinessException {

		Validate.notNull(cart, "cart cannot be null");
		Validate.notNull(cart.getLineItems(), "Cart should have line items.");
		Validate.notNull(store, "MerchantStore cannot be null");
		SalesOrderTotalSummary orderTotalSummary = orderService.calculateShoppingCartTotal(cart, store);
		// updateCart(cart);
		return orderTotalSummary;

	}

	public CartService getShoppingCartService() {
		return shoppingCartService;
	}

	private void updateCart(final Cart cart) throws BusinessException {
		shoppingCartService.save(cart);
	}

}
