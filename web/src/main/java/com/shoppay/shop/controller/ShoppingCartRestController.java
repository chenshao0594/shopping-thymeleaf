package com.shoppay.shop.controller;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.common.exception.ConversionException;
import com.shoppay.core.cart.Cart;
import com.shoppay.core.cart.CartItem;
import com.shoppay.core.cart.service.CartItemService;
import com.shoppay.core.cart.service.CartService;
import com.shoppay.core.customer.Customer;
import com.shoppay.core.facade.ShoppingCartFacade;
import com.shoppay.core.model.ShoppingCartData;
import com.shoppay.core.model.ShoppingCartItem;
import com.shoppay.core.utils.CustomerInfoContextHolder;
import com.shoppay.core.utils.CustomerInfoContextHolder.CustomerInfo;
import com.shoppay.web.rest.util.HeaderUtil;

@RestController("ShopCartRestController")
@RequestMapping("/cart")
public class ShoppingCartRestController extends AbstractShoppingController {
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
			final HttpServletRequest request, final HttpServletResponse response, final Locale locale,HttpSession session)
			throws BusinessException, ConversionException {
		LOGGER.info("shopping cart item  {}", item);
		CustomerInfo customerInfo = CustomerInfoContextHolder.getCustomerInfo();
		ShoppingCartData shoppingCartData = null;
		Cart shoppingCart = null;
		MerchantStore store = customerInfo.getMerchantStore();
		boolean isAnony = customerInfo.isAnony();
		if(isAnony){
			
		}
		Customer customer = customerInfo.getCustomer();
		if (customer != null) {
			shoppingCart = shoppingCartService.getShoppingCart(customer);
		}
		if (!StringUtils.isBlank(customerInfo.getCartCode())) {
			shoppingCart = shoppingCartService.getShoppingCartByCode(customerInfo.getCartCode());
		}
		if (shoppingCart == null) {
			shoppingCart = shoppingCartService.createEmptyCart(customer);
		}
		shoppingCart = shoppingCartFacade.addItemsToShoppingCart(shoppingCart, item, store, customer);
		shoppingCartData = shoppingCartFacade.getShoppingCartData(shoppingCart, store);
		customerInfo.setCartQuantity(shoppingCartData.getQuantity());
		customerInfo.setCartTotal(shoppingCartData.getTotal());
		customerInfo.setCartCode(shoppingCart.getCode());
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
	public ResponseEntity<ShoppingCartData> getMiniCart( HttpServletRequest request) {
		try {
			MerchantStore store = CustomerInfoContextHolder.getMerchantStore();
			Customer customer = CustomerInfoContextHolder.getCustomer();
			String cartCode = CustomerInfoContextHolder.getCartCode();
			ShoppingCartData cart = shoppingCartFacade.getShoppingCartData(customer, store, cartCode);
			return ResponseEntity.ok().body(cart);
		} catch (Exception e) {
			LOGGER.error("Error while getting the shopping cart", e);
		}
		return null;
	}
}
