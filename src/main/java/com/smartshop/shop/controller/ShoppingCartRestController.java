package com.smartshop.shop.controller;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.smartshop.constants.AppConstants;
import com.smartshop.core.cart.Cart;
import com.smartshop.core.cart.CartItem;
import com.smartshop.core.cart.service.CartItemService;
import com.smartshop.core.cart.service.CartService;
import com.smartshop.domain.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.exception.BusinessException;
import com.smartshop.exception.ConversionException;
import com.smartshop.facade.ShoppingCartFacade;
import com.smartshop.shop.model.ShoppingCartData;
import com.smartshop.shop.model.ShoppingCartItem;
import com.smartshop.web.rest.util.HeaderUtil;

@RestController("ShopCartRestController")
@RequestMapping("/cart")
public class ShoppingCartRestController extends AbstractShopController {
	private final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartRestController.class);

	@Inject
	private CartService shoppingCartService;

	@Inject
	private CartItemService cartItemService;

	@Inject
	private ShoppingCartFacade shoppingCartFacade;

	@Timed
	@PostMapping()
	public ResponseEntity<ShoppingCartData> addShoppingCartItem(final ShoppingCartItem item,
			final HttpServletRequest request, final HttpServletResponse response, final Locale locale)
			throws BusinessException, ConversionException {
		LOGGER.info("shopping cart item  {}", item);
		ShoppingCartData shoppingCartData = null;
		Cart shoppingCart = null;
		MerchantStore store = getSessionAttribute(AppConstants.MERCHANT_STORE, request);
		Customer customer = getSessionAttribute(AppConstants.CUSTOMER, request);
		if (customer != null) {
			shoppingCart = shoppingCartService.getShoppingCart(customer);
		}
		String cartCode = (String) request.getSession().getAttribute(AppConstants.SHOPPING_CART);
		if (!StringUtils.isBlank(cartCode)) {
			shoppingCart = shoppingCartService.getShoppingCartByCode(cartCode);
		}
		if (shoppingCart == null) {
			shoppingCart = shoppingCartService.createEmptyCart(customer);
		}
		shoppingCart = shoppingCartFacade.addItemsToShoppingCart(shoppingCart, item, store, customer);
		shoppingCartData = shoppingCartFacade.getShoppingCartData(shoppingCart, store);
		request.getSession().setAttribute(AppConstants.SHOPPING_CART, shoppingCart.getCode());
		LOGGER.info("shopping cart", shoppingCart);
		return ResponseEntity.ok().body(shoppingCartData);
	}

	@Timed
	@PostMapping("/{cartId}/cartItems/{itemId}")
	public ResponseEntity<CartItem> updateItem(Long cartId, Long itemId, int quantity) throws BusinessException {
		CartItem item = this.cartItemService.findCartItemByItemInfo(cartId, itemId);
		if (item == null) {
			throw new BusinessException("cartItem no exist");
		}
		item.setQuantity(quantity);
		this.cartItemService.save(item);
		return ResponseEntity.ok().body(item);
	}

	@Timed
	@DeleteMapping("/{cartId}/cartItems/{itemId}")
	public ResponseEntity<Void> deleteItem(@PathVariable("cartId") Long cartId, @PathVariable("itemId") Long itemId)
			throws Exception {
		LOGGER.info("delete cart {} item {}", cartId, itemId);
		CartItem item = this.cartItemService.findCartItemByItemInfo(cartId, itemId);
		if (item == null) {
			throw new BusinessException("cartItem not exist");
		}
		this.cartItemService.delete(itemId);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("cartItem", itemId.toString())).build();
	}

	@Timed
	@GetMapping("/miniCart")
	public ResponseEntity<ShoppingCartData> getMiniCart(final String cartCode, HttpServletRequest request) {
		try {
			MerchantStore store = getSessionAttribute(AppConstants.MERCHANT_STORE, request);
			Customer customer = getSessionAttribute(AppConstants.CUSTOMER, request);
			ShoppingCartData cart = shoppingCartFacade.getShoppingCartData(customer, store, cartCode);
			if (cart != null) {
				request.getSession().setAttribute(AppConstants.SHOPPING_CART, cart.getCode());
			}
			if (cart == null) {
				request.getSession().removeAttribute(AppConstants.SHOPPING_CART);
				cart = new ShoppingCartData();// create an empty cart
			}
			return ResponseEntity.ok().body(cart);
		} catch (Exception e) {
			LOGGER.error("Error while getting the shopping cart", e);
		}

		return null;

	}

}
