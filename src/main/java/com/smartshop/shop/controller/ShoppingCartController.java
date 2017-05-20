package com.smartshop.shop.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codahale.metrics.annotation.Timed;
import com.smartshop.constants.AppConstants;
import com.smartshop.core.cart.Cart;
import com.smartshop.core.cart.service.CartService;
import com.smartshop.domain.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.facade.ShoppingCartFacade;
import com.smartshop.shop.model.ShoppingCartData;

@Controller("ShopCartController")
@RequestMapping("/shoppingcart")
public class ShoppingCartController extends AbstractShopController {

	private final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartController.class);

	@Inject
	private CartService shoppingCartService;

	@Inject
	private ShoppingCartFacade shoppingCartFacade;

	@Timed
	@GetMapping()
	public ModelAndView detail(ModelAndView model, final HttpServletRequest request) throws Exception {

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
		shoppingCartData = shoppingCartFacade.getShoppingCartData(shoppingCart, store);
		request.getSession().setAttribute(AppConstants.SHOPPING_CART, shoppingCart.getCode());
		boolean isEmpty = CollectionUtils.isEmpty(shoppingCartData.getShoppingCartItems());
		model.addObject("shoppingcart", shoppingCartData);
		model.addObject("isEmpty", isEmpty);
		model.setViewName("shop/cart/detail");
		return model;
	}

}
