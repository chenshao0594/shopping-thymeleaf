package com.shoppay.shop.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codahale.metrics.annotation.Timed;
import com.shoppay.common.constants.ApplicationConstants;
import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.common.exception.ConversionException;
import com.shoppay.core.cart.Cart;
import com.shoppay.core.cart.service.CartService;
import com.shoppay.core.cart.service.ShoppingCartCalculationService;
import com.shoppay.core.catalog.service.PricingService;
import com.shoppay.core.customer.Customer;
import com.shoppay.core.customer.model.CustomerRO;
import com.shoppay.core.facade.CustomerFacade;
import com.shoppay.core.model.ShoppingCartData;
import com.shoppay.core.utils.CustomerInfoContextHolder;
import com.shoppay.populator.ShoppingCartDataPopulator;

@Controller("ShopCustomerRestController")
@RequestMapping("/customer")
public class ShoppingCustomerRestController extends AbstractShoppingController {
	private final Logger LOGGER = LoggerFactory.getLogger(ShoppingCustomerRestController.class);

	@Inject
	private CustomerFacade customerFacade;

	@Inject
	private CartService shoppingCartService;

	@Inject
	private ShoppingCartCalculationService shoppingCartCalculationService;

	@Inject
	private PricingService pricingService;

	@Timed
	@PostMapping(value = "/login")
	public String login(CustomerRO customerInfo, HttpServletRequest request, HttpServletResponse response) {
		try {
			LOGGER.debug("Authenticating user " + customerInfo.getName());
			MerchantStore store = CustomerInfoContextHolder.getMerchantStore();
			Customer customerModel = customerFacade.getCustomerByUserName(customerInfo.getName(), store);
			if (customerModel == null) {
				throw new BusinessException("customer not exist");
			}
			customerFacade.authenticate(customerModel, customerInfo.getName(), customerInfo.getPassword());
			String sessionShoppingCartCode = CustomerInfoContextHolder.getCartCode();
			if (!StringUtils.isBlank(sessionShoppingCartCode)) {
				Cart shoppingCart = customerFacade.mergeCart(customerModel, sessionShoppingCartCode, store);
				ShoppingCartData shoppingCartData = this.populateShoppingCartData(shoppingCart, store);
			} else {
				Cart cartModel = shoppingCartService.getShoppingCartByCustomer(customerModel);
//				if (cartModel != null) {
//					request.getSession().setAttribute(ApplicationConstants.SHOPPING_CART, cartModel.getCode());
//					Cookie c = new Cookie(ApplicationConstants.COOKIE_NAME_CART, cartModel.getCode());
//					c.setMaxAge(60 * 24 * 3600);
//					c.setPath(ApplicationConstants.SLASH);
//					response.addCookie(c);
//				}

			}

			StringBuilder cookieValue = new StringBuilder();
			cookieValue.append(store.getCode()).append("_").append(customerModel.getName());
			// set username in the cookie
			// Cookie c = new Cookie(AppConstants.COOKIE_NAME_USER,
			// cookieValue.toString());
			// c.setMaxAge(60 * 24 * 3600);
			// c.setPath(Constants.SLASH);
			// response.addCookie(c);

		} catch (AuthenticationException ex) {
			ex.printStackTrace();
			return "redirect:/login";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/";

	}

	private ShoppingCartData populateShoppingCartData(final Cart cartModel, final MerchantStore store) {
		ShoppingCartDataPopulator shoppingCartDataPopulator = new ShoppingCartDataPopulator();
		shoppingCartDataPopulator.setShoppingCartCalculationService(shoppingCartCalculationService);
		shoppingCartDataPopulator.setPricingService(pricingService);
		try {
			return shoppingCartDataPopulator.populate(cartModel, store);
		} catch (ConversionException ce) {
			LOGGER.error("Error in converting shopping cart to shopping cart data", ce);
		}
		return null;
	}

}
