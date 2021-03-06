package com.shoppay.core.cart.service;

import java.util.List;

import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.common.service.AbstractDomainService;
import com.shoppay.core.cart.Cart;
import com.shoppay.core.cart.CartItem;
import com.shoppay.core.catalog.Product;
import com.shoppay.core.customer.Customer;

public interface CartService extends AbstractDomainService<Cart, Long> {

	Cart getShoppingCart(Customer customer) throws BusinessException;

	Cart getById(Long id, MerchantStore store) throws BusinessException;

	Cart getByCode(String code, MerchantStore store) throws BusinessException;

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
	boolean isFreeShoppingCart(Cart cart) throws BusinessException;

	boolean isFreeShoppingCart(List<CartItem> items) throws BusinessException;

	/**
	 * Populates a ShoppingCartItem from a Product and attributes if any
	 *
	 * @param product
	 * @return
	 * @throws BusinessException
	 */
	CartItem populateShoppingCartItem(Product product) throws BusinessException;

	/**
	 *
	 * @param userShoppingModel
	 * @param sessionCart
	 * @param store
	 * @return {@link Cart} merged Shopping Cart
	 * @throws Exception
	 */
	public Cart mergeShoppingCarts(final Cart userShoppingCart, final Cart sessionCart, final MerchantStore store)
			throws BusinessException;

	/**
	 * Determines if the shopping cart requires shipping
	 *
	 * @param cart
	 * @return
	 * @throws BusinessException
	 */
	boolean requiresShipping(Cart cart) throws BusinessException;

	/**
	 * Removes a shopping cart item
	 *
	 * @param item
	 */
	void deleteShoppingCartItem(Long id);

	Cart createEmptyCart(Customer customer);

	Cart getShoppingCartByCode(String cartCode);

	Cart getShoppingCartByCustomer(Customer customer);

}