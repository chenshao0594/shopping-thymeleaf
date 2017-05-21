package com.smartshop.shop.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.smartshop.constants.AppConstants;
import com.smartshop.core.cart.Cart;
import com.smartshop.core.cart.service.CartService;
import com.smartshop.core.cart.service.ShoppingCartCalculationService;
import com.smartshop.core.catalog.service.PricingService;
import com.smartshop.customer.CustomerRO;
import com.smartshop.domain.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.service.CustomerFacade;
import com.smartshop.shop.model.ShoppingCartData;

@RestController("ShopCustomerRestController")
@RequestMapping("/customer")
public class CustomerRestController extends AbstractShopController {
	private final Logger LOGGER = LoggerFactory.getLogger(CustomerRestController.class);

	@Inject
	private AuthenticationManager customerAuthenticationManager;

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
	public ResponseEntity<Void> login(CustomerRO customerInfo, HttpServletRequest request) {
		try {
			LOGGER.debug("Authenticating user " + customerInfo.getName());
			MerchantStore store = (MerchantStore) request.getAttribute(AppConstants.MERCHANT_STORE);
			// Language language = (Language) request.getAttribute("LANGUAGE");
			Customer customerModel = customerFacade.getCustomerByUserName(customerInfo.getName(), store);
			if (customerModel == null) {
			}

			if (!customerModel.getMerchantStore().getCode().equals(store.getCode())) {
			}

			customerFacade.authenticate(customerModel, customerInfo.getName(), customerInfo.getPassword());
			// set customer in the http session
			super.setSessionAttribute(AppConstants.CUSTOMER, customerModel, request);

			LOGGER.info("Fetching and merging Shopping Cart data");
			String sessionShoppingCartCode = (String) request.getSession().getAttribute(AppConstants.SHOPPING_CART);
			if (!StringUtils.isBlank(sessionShoppingCartCode)) {
				Cart shoppingCart = customerFacade.mergeCart(customerModel, sessionShoppingCartCode, store);
				ShoppingCartData shoppingCartData = this.populateShoppingCartData(shoppingCart, store, language);
				if (shoppingCartData != null) {
					// jsonObject.addEntry(AppConstants.SHOPPING_CART,
					// shoppingCartData.getCode());
					request.getSession().setAttribute(AppConstants.SHOPPING_CART, shoppingCartData.getCode());

					// set cart in the cookie
					// Cookie c = new Cookie(AppConstants.COOKIE_NAME_CART,
					// shoppingCartData.getCode());
					// c.setMaxAge(60 * 24 * 3600);
					// c.setPath(Constants.SLASH);
					// response.addCookie(c);

				} else {
					// DELETE COOKIE
					// Cookie c = new Cookie(AppConstants.COOKIE_NAME_CART, "");
					// c.setMaxAge(0);
					// c.setPath(AppConstants.SLASH);
					// response.addCookie(c);
				}

			} else {
				Cart cartModel = shoppingCartService.getByCustomer(customerModel);
				if (cartModel != null) {
					// jsonObject.addEntry(Constants.SHOPPING_CART,
					// cartModel.getShoppingCartCode());
					// request.getSession().setAttribute(Constants.SHOPPING_CART,
					// cartModel.getShoppingCartCode());
					// Cookie c = new Cookie(Constants.COOKIE_NAME_CART,
					// cartModel.getShoppingCartCode());
					// c.setMaxAge(60 * 24 * 3600);
					// c.setPath(Constants.SLASH);
					// response.addCookie(c);
				}

			}

			StringBuilder cookieValue = new StringBuilder();
			cookieValue.append(store.getCode()).append("_").append(customerModel.getNick());

			// set username in the cookie
			// Cookie c = new Cookie(AppConstants.COOKIE_NAME_USER,
			// cookieValue.toString());
			// c.setMaxAge(60 * 24 * 3600);
			// c.setPath(Constants.SLASH);
			// response.addCookie(c);

		} catch (AuthenticationException ex) {
		} catch (Exception e) {
		}

		return null;

	}
}
