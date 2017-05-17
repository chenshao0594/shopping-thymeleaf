package com.smart.shopping.core.cart.service;

import java.util.List;

import com.smart.shopping.core.cart.ShoppingCart;
import com.smart.shopping.core.cart.ShoppingCartItem;
import com.smart.shopping.core.catalog.Product;
import com.smart.shopping.domain.Customer;
import com.smart.shopping.domain.MerchantStore;
import com.smart.shopping.exception.BusinessException;
import com.smart.shopping.service.AbstractDomainService;

public interface ShoppingCartService extends AbstractDomainService<ShoppingCart, Long> {

	ShoppingCart getShoppingCart(Customer customer) throws BusinessException;

	void saveOrUpdate(ShoppingCart shoppingCart) throws BusinessException;

	ShoppingCart getById(Long id, MerchantStore store) throws BusinessException;

	ShoppingCart getByCode(String code, MerchantStore store) throws BusinessException;

	ShoppingCart getByCustomer(Customer customer) throws BusinessException;

	/**
	 * Creates a list of ShippingProduct based on the ShoppingCart if items are
	 * virtual return list will be null
	 *
	 * @param cart
	 * @return
	 * @throws BusinessException
	 */
	// List<ShippingProduct> createShippingProduct(ShoppingCart cart) throws
	// BusinessException;

	/**
	 * Looks if the items in the ShoppingCart are free of charges
	 *
	 * @param cart
	 * @return
	 * @throws BusinessException
	 */
	boolean isFreeShoppingCart(ShoppingCart cart) throws BusinessException;

	boolean isFreeShoppingCart(List<ShoppingCartItem> items) throws BusinessException;

	/**
	 * Populates a ShoppingCartItem from a Product and attributes if any
	 *
	 * @param product
	 * @return
	 * @throws BusinessException
	 */
	ShoppingCartItem populateShoppingCartItem(Product product) throws BusinessException;

	void deleteCart(ShoppingCart cart) throws BusinessException;

	void removeShoppingCart(ShoppingCart cart) throws BusinessException;

	/**
	 *
	 * @param userShoppingModel
	 * @param sessionCart
	 * @param store
	 * @return {@link ShoppingCart} merged Shopping Cart
	 * @throws Exception
	 */
	public ShoppingCart mergeShoppingCarts(final ShoppingCart userShoppingCart, final ShoppingCart sessionCart,
			final MerchantStore store) throws Exception;

	/**
	 * Determines if the shopping cart requires shipping
	 *
	 * @param cart
	 * @return
	 * @throws BusinessException
	 */
	boolean requiresShipping(ShoppingCart cart) throws BusinessException;

	/**
	 * Removes a shopping cart item
	 *
	 * @param item
	 */
	void deleteShoppingCartItem(Long id);

}