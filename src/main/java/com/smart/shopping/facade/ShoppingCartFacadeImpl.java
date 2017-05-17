package com.smart.shopping.facade;

import java.util.List;

import com.smart.shop.model.ShoppingCartData;
import com.smart.shopping.core.cart.ShoppingCart;
import com.smart.shopping.domain.Customer;
import com.smart.shopping.domain.MerchantStore;

public class ShoppingCartFacadeImpl implements ShoppingCartFacade {

	@Override
	public ShoppingCartData addItemsToShoppingCart(ShoppingCartData shoppingCart,
			com.smart.shop.model.ShoppingCartItem item, MerchantStore store, Customer customer) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShoppingCart createCartModel(String shoppingCartCode, MerchantStore store, Customer customer)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShoppingCartData getShoppingCartData(Customer customer, MerchantStore store, String shoppingCartId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShoppingCartData getShoppingCartData(ShoppingCart shoppingCart) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShoppingCartData getShoppingCartData(String code, MerchantStore store) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShoppingCartData removeCartItem(Long itemID, String cartId, MerchantStore store) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShoppingCartData updateCartItem(Long itemID, String cartId, long quantity, MerchantStore store)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteShoppingCart(Long id, MerchantStore store) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public ShoppingCartData updateCartItems(List<com.smart.shop.model.ShoppingCartItem> shoppingCartItems,
			MerchantStore store) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShoppingCart getShoppingCartModel(String shoppingCartCode, MerchantStore store) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShoppingCart getShoppingCartModel(Customer customer, MerchantStore store) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteShoppingCart(String code, MerchantStore store) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdateShoppingCart(ShoppingCart cart) throws Exception {
		// TODO Auto-generated method stub

	}

}
