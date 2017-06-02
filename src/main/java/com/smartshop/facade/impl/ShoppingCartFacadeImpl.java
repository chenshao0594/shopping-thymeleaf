package com.smartshop.facade.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.smartshop.core.cart.Cart;
import com.smartshop.core.cart.CartItem;
import com.smartshop.core.cart.service.CartItemService;
import com.smartshop.core.cart.service.CartService;
import com.smartshop.core.cart.service.ShoppingCartCalculationService;
import com.smartshop.core.catalog.Product;
import com.smartshop.core.catalog.SKU;
import com.smartshop.core.catalog.service.PricingService;
import com.smartshop.domain.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.exception.BusinessException;
import com.smartshop.exception.ConversionException;
import com.smartshop.facade.ShoppingCartFacade;
import com.smartshop.populator.ShoppingCartDataPopulator;
import com.smartshop.service.ProductService;
import com.smartshop.service.SKUService;
import com.smartshop.shop.model.ShoppingCartData;
import com.smartshop.shop.model.ShoppingCartItem;

@Service("shoppingCartFacadeImpl")
public class ShoppingCartFacadeImpl implements ShoppingCartFacade {

	@Inject
	private ProductService productService;

	@Inject
	private SKUService skuService;

	@Inject
	private CartService cartService;

	@Inject
	private CartItemService cartItemService;

	@Inject
	private PricingService pricingService;

	@Inject
	private ShoppingCartCalculationService shoppingCartCalculationService;

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
			for (SKU each : product.getSkus()) {
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
		CartItem cartItem = this.cartItemService.findCartItemByItemInfo(product.getId(), item.getSkuId(),
				shoppingCart.getId());
		if (cartItem != null) {
			int oldQuantity = cartItem.getQuantity();
			cartItem.setQuantity(oldQuantity + item.getQuantity());
			this.cartItemService.save(cartItem);

		} else {
			cartItem = new CartItem();
			cartItem.setQuantity(item.getQuantity());
			cartItem.setProductId(product.getId());
			cartItem.setShoppingCart(shoppingCart);
			cartItem.setSkuId(item.getSkuId());
			cartItem.setItemPrice(price);
			cartItem.setSubTotal(price.multiply(new BigDecimal(item.getQuantity())));
			cartItem.setProduct(product);
			this.cartItemService.save(cartItem);
			shoppingCart.getLineItems().add(cartItem);
			this.cartService.save(shoppingCart);
		}
		shoppingCart = this.cartService.findOne(shoppingCart.getId());
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
	public ShoppingCartData getShoppingCartData(Cart shoppingCart, MerchantStore merchantStore)
			throws BusinessException, ConversionException {
		ShoppingCartDataPopulator shoppingCartDataPopulator = new ShoppingCartDataPopulator();
		shoppingCartDataPopulator.setShoppingCartCalculationService(shoppingCartCalculationService);
		shoppingCartDataPopulator.setPricingService(pricingService);
		shoppingCartDataPopulator.setProductService(productService);
		shoppingCartDataPopulator.setSkuService(skuService);
		return shoppingCartDataPopulator.populate(shoppingCart, merchantStore);
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
