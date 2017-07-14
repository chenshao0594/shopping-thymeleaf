package com.shoppay.core.facade.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.common.exception.ConversionException;
import com.shoppay.core.cart.Cart;
import com.shoppay.core.cart.CartItem;
import com.shoppay.core.cart.service.CartItemService;
import com.shoppay.core.cart.service.CartService;
import com.shoppay.core.cart.service.ShoppingCartCalculationService;
import com.shoppay.core.catalog.Product;
import com.shoppay.core.catalog.SKU;
import com.shoppay.core.catalog.service.PricingService;
import com.shoppay.core.catalog.service.ProductService;
import com.shoppay.core.catalog.service.SKUService;
import com.shoppay.core.customer.Customer;
import com.shoppay.facade.ShoppingCartFacade;
import com.shoppay.populator.ShoppingCartDataPopulator;
import com.shoppay.shop.model.ShoppingCartData;
import com.shoppay.shop.model.ShoppingCartItem;

@Service("shoppingCartFacadeImpl")
public class ShoppingCartFacadeImpl implements ShoppingCartFacade {

	private final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartFacadeImpl.class);

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
		Cart cart = null;
		try {
			if (customer != null) {
				LOGGER.info("Reteriving customer shopping cart...");
				cart = cartService.getShoppingCart(customer);
			} else if (StringUtils.isNotBlank(shoppingCartId) && cart == null) {
				cart = cartService.getByCode(shoppingCartId, store);
			}
		} catch (ServiceException ex) {
			LOGGER.error("Error while retriving cart from customer", ex);
		} catch (NoResultException nre) {
			// nothing
		}

		if (cart == null) {
			return null;
		}
		LOGGER.info("Cart  found .....");
		ShoppingCartDataPopulator shoppingCartDataPopulator = new ShoppingCartDataPopulator();
		shoppingCartDataPopulator.setShoppingCartCalculationService(shoppingCartCalculationService);
		shoppingCartDataPopulator.setPricingService(pricingService);
		shoppingCartDataPopulator.setProductService(this.productService);
		shoppingCartDataPopulator.setSkuService(this.skuService);
		ShoppingCartData shoppingCartData = null;
		try {
			shoppingCartData = shoppingCartDataPopulator.populate(cart, store);
		} catch (ConversionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shoppingCartData;
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
