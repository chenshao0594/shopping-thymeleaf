package com.smartshop.facade;

import java.util.List;

import com.smartshop.core.cart.Cart;
import com.smartshop.domain.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.shop.model.ShoppingCartData;
import com.smartshop.shop.model.ShoppingCartItem;

public interface ShoppingCartFacade {
	public ShoppingCartData addItemsToShoppingCart(ShoppingCartData shoppingCart, final ShoppingCartItem item,
			final MerchantStore store, final Customer customer) throws Exception;

	public Cart createCartModel(final String shoppingCartCode, final MerchantStore store,
			final Customer customer) throws Exception;

	/**
	 * Method responsible for getting shopping cart from either session or from
	 * underlying DB.
	 */
	public ShoppingCartData getShoppingCartData(final Customer customer, final MerchantStore store,
			final String shoppingCartId) throws Exception;

	public ShoppingCartData getShoppingCartData(final Cart shoppingCart) throws Exception;

	public ShoppingCartData getShoppingCartData(String code, MerchantStore store) throws Exception;

	public ShoppingCartData removeCartItem(final Long itemID, final String cartId, final MerchantStore store)
			throws Exception;

	public ShoppingCartData updateCartItem(final Long itemID, final String cartId, final long quantity,
			final MerchantStore store) throws Exception;

	public void deleteShoppingCart(final Long id, final MerchantStore store) throws Exception;

	ShoppingCartData updateCartItems(List<ShoppingCartItem> shoppingCartItems, MerchantStore store) throws Exception;

	public Cart getShoppingCartModel(final String shoppingCartCode, MerchantStore store) throws Exception;

	public Cart getShoppingCartModel(final Customer customer, MerchantStore store) throws Exception;

	void deleteShoppingCart(String code, MerchantStore store) throws Exception;

	void saveOrUpdateShoppingCart(Cart cart) throws Exception;

}
