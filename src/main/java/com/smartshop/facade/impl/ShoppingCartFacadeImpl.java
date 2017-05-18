package com.smartshop.facade.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartshop.core.cart.Cart;
import com.smartshop.domain.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.facade.ShoppingCartFacade;
import com.smartshop.shop.model.ShoppingCartData;
import com.smartshop.shop.model.ShoppingCartItem;

@Service("shoppingCartFacadeImpl")
public class ShoppingCartFacadeImpl implements ShoppingCartFacade {

	@Override
	public ShoppingCartData addItemsToShoppingCart(ShoppingCartData shoppingCart, ShoppingCartItem item,
			MerchantStore store, Customer customer) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart createCartModel(String shoppingCartCode, MerchantStore store, Customer customer) throws Exception {
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
	public ShoppingCartData getShoppingCartData(Cart shoppingCart) throws Exception {
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
	public ShoppingCartData updateCartItems(List<ShoppingCartItem> shoppingCartItems, MerchantStore store)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart getShoppingCartModel(String shoppingCartCode, MerchantStore store) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart getShoppingCartModel(Customer customer, MerchantStore store) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteShoppingCart(String code, MerchantStore store) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdateShoppingCart(Cart cart) throws Exception {
		// TODO Auto-generated method stub

	}

}
