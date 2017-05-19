package com.smartshop.facade.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.smartshop.core.cart.Cart;
import com.smartshop.core.cart.CartItem;
import com.smartshop.core.cart.service.CartService;
import com.smartshop.core.catalog.Product;
import com.smartshop.core.catalog.SKU;
import com.smartshop.domain.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.exception.BusinessException;
import com.smartshop.facade.ShoppingCartFacade;
import com.smartshop.service.ProductService;
import com.smartshop.shop.model.ShoppingCartData;
import com.smartshop.shop.model.ShoppingCartItem;

@Service("shoppingCartFacadeImpl")
public class ShoppingCartFacadeImpl implements ShoppingCartFacade {

	@Inject
	private ProductService productService;
	@Inject
	private CartService cartService;

	@Override
	public Cart addItemsToShoppingCart(Cart shoppingCart, ShoppingCartItem item, MerchantStore store, Customer customer)
			throws BusinessException {
		Product product = this.productService.findOne(item.getProductId());
		if (product == null) {
			throw new BusinessException("Product {} not exist " + item.getProductId());
		}

		BigDecimal price = product.getRetailPrice();
		if (item.getSkuId() != null) {
			boolean flag = false;
			for (SKU each : product.getAdditionalSKUs()) {
				if (each.getId() == item.getSkuId()) {
					price = each.getRetailPrice();
					flag = true;
					break;
				}
			}
			if (!flag) {
				throw new BusinessException("product id can't match skuid ");
			}

		}
		CartItem cartItem = new CartItem();
		cartItem.setItemPrice(price);
		cartItem.setQuantity(item.getQuantity());
		cartItem.setProductId(product.getId());
		cartItem.setShoppingCart(shoppingCart);
		if (item.getSkuId() != null) {
			cartItem.setSkuId(item.getSkuId());
		}
		shoppingCart.getLineItems().add(cartItem);
		this.cartService.save(shoppingCart);
		return shoppingCart;
	}

	@Override
	public Cart createCartModel(String shoppingCartCode, MerchantStore store, Customer customer)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShoppingCartData getShoppingCartData(Customer customer, MerchantStore store, String shoppingCartId)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShoppingCartData getShoppingCartData(Cart shoppingCart) throws BusinessException {
		ShoppingCartData shoppingCartData = new ShoppingCartData();
		shoppingCartData.setCode(shoppingCart.getCode());
		shoppingCartData.setId(shoppingCart.getId());
		BigDecimal total = new BigDecimal(0);
		shoppingCartData.setTotal(shoppingCart);
		return shoppingCartData;
	}

	@Override
	public ShoppingCartData getShoppingCartData(String code, MerchantStore store) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShoppingCartData updateCartItem(Long itemID, String cartId, long quantity, MerchantStore store)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteShoppingCart(Long id, MerchantStore store) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public ShoppingCartData updateCartItems(List<ShoppingCartItem> shoppingCartItems, MerchantStore store)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart getShoppingCartModel(String shoppingCartCode, MerchantStore store) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart getShoppingCartModel(Customer customer, MerchantStore store) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteShoppingCart(String code, MerchantStore store) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdateShoppingCart(Cart cart) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public ShoppingCartData removeCartItem(Long itemID, String cartId, MerchantStore store) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
