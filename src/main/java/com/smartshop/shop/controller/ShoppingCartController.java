package com.smartshop.shop.controller;

import java.util.Locale;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.codahale.metrics.annotation.Timed;
import com.smartshop.config.AppConstants;
import com.smartshop.core.cart.Cart;
import com.smartshop.core.cart.service.CartService;
import com.smartshop.domain.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.facade.ShoppingCartFacade;
import com.smartshop.shop.model.ShoppingCartData;
import com.smartshop.shop.model.ShoppingCartItem;

@Controller("ShopCartController")
@RequestMapping("/cart")
public class ShoppingCartController extends AbstractShopController {

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
	public @ResponseBody ShoppingCartData addShoppingCartItem(@RequestBody final ShoppingCartItem item,
			final HttpServletRequest request, final HttpServletResponse response, final Locale locale)
			throws Exception {

		ShoppingCartData shoppingCart = null;
		// Look in the HttpSession to see if a customer is logged in
		MerchantStore store = getSessionAttribute(AppConstants.MERCHANT_STORE, request);
		Customer customer = getSessionAttribute(AppConstants.CUSTOMER, request);
		if (customer != null) {
			Cart customerCart = shoppingCartService.getByCustomer(customer);
			if (customerCart != null) {
				shoppingCart = shoppingCartFacade.getShoppingCartData(customerCart);

				// TODO if shoppingCart != null ?? merge
				// TODO maybe they have the same code
				// TODO what if codes are different (-- merge carts, keep the
				// latest one, delete the oldest, switch codes --)
			}
		}

		if (shoppingCart == null && !StringUtils.isBlank(item.getCode())) {
			shoppingCart = shoppingCartFacade.getShoppingCartData(item.getCode(), store);
		}

		// if shoppingCart is null create a new one
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCartData();
			String code = UUID.randomUUID().toString().replaceAll("-", "");
			shoppingCart.setCode(code);
		}

		shoppingCart = shoppingCartFacade.addItemsToShoppingCart(shoppingCart, item, store, customer);
		request.getSession().setAttribute(AppConstants.SHOPPING_CART, shoppingCart.getCode());

		/******************************************************/
		// TODO validate all of this

		// if a customer exists in http session
		// if a cart does not exist in httpsession
		// get cart from database
		// if a cart exist in the database add the item to the cart and put cart
		// in httpsession and save to the database
		// else a cart does not exist in the database, create a new one, set the
		// customer id, set the cart in the httpsession
		// else a cart exist in the httpsession, add item to httpsession cart
		// and save to the database
		// else no customer in httpsession
		// if a cart does not exist in httpsession
		// create a new one, set the cart in the httpsession
		// else a cart exist in the httpsession, add item to httpsession cart
		// and save to the database

		/**
		 * Tested with the following : what if you add item in the shopping cart
		 * as an anonymous user later on you log in to process with checkout but
		 * the system retrieves a previous shopping cart saved in the database
		 * for that customer in that case we need to synchronize both carts and
		 * the original one (the one with the customer id) supercedes the
		 * current cart in session the system will have to deal with the
		 * original one and remove the latest
		 */

		// **more implementation details
		// calculate the price of each item by using ProductPriceUtils in
		// sm-core
		// for each product in the shopping cart get the product
		// invoke productPriceUtils.getFinalProductPrice
		// from FinalPrice get final price which is the calculated price given
		// attributes and discounts
		// set each item price in ShoppingCartItem.price

		return shoppingCart;

	}

	@Timed
	@DeleteMapping()
	public ModelAndView deleteItem(ModelAndView model) throws Exception {
		model.setViewName("shop/cart/detail");
		return model;
	}
}
