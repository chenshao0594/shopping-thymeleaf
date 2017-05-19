package com.smartshop.shop.controller;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.codahale.metrics.annotation.Timed;
import com.smartshop.constants.AppConstants;
import com.smartshop.core.cart.Cart;
import com.smartshop.core.cart.service.CartService;
import com.smartshop.domain.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.exception.BusinessException;
import com.smartshop.facade.ShoppingCartFacade;
import com.smartshop.shop.model.ShoppingCartData;
import com.smartshop.shop.model.ShoppingCartItem;

@RestController("ShopCartController")
@RequestMapping("/cart")
public class ShoppingCartController extends AbstractShopController {
	private final Logger log = LoggerFactory.getLogger(ShoppingCartController.class);

	@Inject
	private CartService shoppingCartService;

	@Inject
	private ShoppingCartFacade shoppingCartFacade;

	@Timed
	@GetMapping()
	public ModelAndView detail(ModelAndView model) throws Exception {
		model.setViewName("shop/cart/detail");
		return model;
	}

	@Timed
	@PostMapping()
	public ResponseEntity<ShoppingCartData> addShoppingCartItem(final ShoppingCartItem item,
			final HttpServletRequest request, final HttpServletResponse response, final Locale locale)
			throws BusinessException {
		log.info("shopping cart item  {}", item);
		ShoppingCartData shoppingCartData = null;
		Cart shoppingCart = null;
		MerchantStore store = getSessionAttribute(AppConstants.MERCHANT_STORE, request);
		Customer customer = getSessionAttribute(AppConstants.CUSTOMER, request);
		if (customer != null) {
			shoppingCart = shoppingCartService.getShoppingCart(customer);
		}
		if (shoppingCart == null) {
			shoppingCart = shoppingCartService.createEmptyCart(customer);
		}
		shoppingCart = shoppingCartFacade.addItemsToShoppingCart(shoppingCart, item, store, customer);

		shoppingCartData = shoppingCartFacade.getShoppingCartData(shoppingCart);
		request.getSession().setAttribute(AppConstants.SHOPPING_CART, shoppingCart.getCode());
		log.info("shopping cart", shoppingCart);
		return ResponseEntity.ok().body(shoppingCartData);
	}

	@Timed
	@DeleteMapping()
	public ModelAndView deleteItem(ModelAndView model) throws Exception {
		model.setViewName("shop/cart/detail");
		return model;
	}
}
