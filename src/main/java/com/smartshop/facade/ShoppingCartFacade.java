package com.smartshop.facade;

import java.util.List;

import com.smartshop.core.cart.Cart;
import com.smartshop.domain.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.exception.BusinessException;
import com.smartshop.shop.model.ShoppingCartData;
import com.smartshop.shop.model.ShoppingCartItem;

public interface ShoppingCartFacade {
	public Cart addItemsToShoppingCart(Cart shoppingCart, final ShoppingCartItem item, final MerchantStore store,
			final Customer customer) throws BusinessException;

	public Cart createCartModel(final String shoppingCartCode, final MerchantStore store, final Customer customer)
			throws BusinessException;

	/**
	 * Method responsible for getting shopping cart from either session or from
	 * underlying DB.
	 */
	public ShoppingCartData getShoppingCartData(final Customer customer, final MerchantStore store,
			final String shoppingCartId) throws BusinessException;

	public ShoppingCartData getShoppingCartData(final Cart shoppingCart) throws BusinessException;

	public ShoppingCartData getShoppingCartData(String code, MerchantStore store) throws BusinessException;

	public ShoppingCartData removeCartItem(final Long itemID, final String cartId, final MerchantStore store)
			throws BusinessException;

	public ShoppingCartData updateCartItem(final Long itemID, final String cartId, final long quantity,
			final MerchantStore store) throws BusinessException;

	public void deleteShoppingCart(final Long id, final MerchantStore store) throws BusinessException;

	ShoppingCartData updateCartItems(List<ShoppingCartItem> shoppingCartItems, MerchantStore store)
			throws BusinessException;

	public Cart getShoppingCartModel(final String shoppingCartCode, MerchantStore store) throws BusinessException;

	public Cart getShoppingCartModel(final Customer customer, MerchantStore store) throws BusinessException;

	void deleteShoppingCart(String code, MerchantStore store) throws BusinessException;

	void saveOrUpdateShoppingCart(Cart cart) throws BusinessException;

}
