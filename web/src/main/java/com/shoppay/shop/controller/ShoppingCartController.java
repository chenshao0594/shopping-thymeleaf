package com.shoppay.shop.controller;

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
import com.shoppay.common.constants.ApplicationConstants;
import com.shoppay.common.domain.MerchantStore;
import com.shoppay.core.cart.Cart;
import com.shoppay.core.cart.service.CartService;
import com.shoppay.core.customer.Customer;
import com.shoppay.core.facade.ShoppingCartFacade;
import com.shoppay.shop.model.ShoppingCartData;
import com.shoppay.shop.utils.CustomerInfoContextHolder;
import com.shoppay.web.constants.ShoppingControllerConstants;

@Controller("ShopCartController")
@RequestMapping("/shoppingcart")
public class ShoppingCartController extends AbstractShoppingController {

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
		MerchantStore store = CustomerInfoContextHolder.getMerchantStore();
		Customer customer = CustomerInfoContextHolder.getCustomer();
		if (customer != null) {
			shoppingCart = shoppingCartService.getShoppingCart(customer);
		}
		String cartCode = (String) request.getSession().getAttribute(ApplicationConstants.SHOPPING_CART);
		if (!StringUtils.isBlank(cartCode)) {
			shoppingCart = shoppingCartService.getShoppingCartByCode(cartCode);
		}
		if (shoppingCart == null) {
			shoppingCart = shoppingCartService.createEmptyCart(customer);
		}
		shoppingCartData = shoppingCartFacade.getShoppingCartData(shoppingCart, store);
		request.getSession().setAttribute(ApplicationConstants.SHOPPING_CART, shoppingCart.getCode());
		boolean isEmpty = CollectionUtils.isEmpty(shoppingCartData.getShoppingCartItems());
		model.addObject("shoppingcart", shoppingCartData);
		model.addObject("isEmpty", isEmpty);
		model.setViewName(ShoppingControllerConstants.ShoppingCart.detail);
		return model;
	}

}
