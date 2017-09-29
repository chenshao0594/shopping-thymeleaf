package com.shoppay.shop.controller;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codahale.metrics.annotation.Timed;
import com.shoppay.common.constants.ApplicationConstants;
import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.common.exception.ConversionException;
import com.shoppay.common.service.MerchantStoreService;
import com.shoppay.common.service.MessageService;
import com.shoppay.core.cart.Cart;
import com.shoppay.core.cart.service.CartService;
import com.shoppay.core.cart.service.ShoppingCartCalculationService;
import com.shoppay.core.catalog.service.PricingService;
import com.shoppay.core.customer.Customer;
import com.shoppay.core.customer.model.CustomerRO;
import com.shoppay.core.facade.CustomerFacade;
import com.shoppay.populator.ShoppingCartDataPopulator;
import com.shoppay.shop.model.ShoppingCartData;
import com.shoppay.shop.utils.CustomerInfoContextHolder;
import com.shoppay.web.constants.ShoppingControllerConstants;

@RequestMapping()
@Controller("ShopAccountController")
public class ShoppingAccountController extends AbstractShoppingController {
	private final Logger LOGGER = LoggerFactory.getLogger(ShoppingAccountController.class);

	@Inject
	private CustomerFacade customerFacade;

	@Inject
	private CartService shoppingCartService;

	@Inject
	private ShoppingCartCalculationService shoppingCartCalculationService;

	@Inject
	private PricingService pricingService;
	
	@Inject
	private MessageService messageService;
	
	@Inject
	private MerchantStoreService merchantStoreService;
	
	@Timed
	@GetMapping(value = "/register")
	public ModelAndView displayRegistration(ModelAndView model) throws Exception {
		model.setViewName(ShoppingControllerConstants.Customer.register);
		return model;
	}

	@Timed
	@Transactional
	@PostMapping(value = "/register")
	public String registerCustomer(@Valid CustomerRO customerRO, BindingResult bindingResult, Model model)
			throws Exception {
		MerchantStore merchantStore = this.merchantStoreService.findOne(1L);
		if (bindingResult.hasErrors()) {
			LOGGER.error("found validation error {}", bindingResult.getAllErrors());
			StringBuilder template = new StringBuilder().append(ShoppingControllerConstants.Customer.register);
			return template.toString();
		}

		if (customerFacade.checkIfUserExists(customerRO.getEmailAddress(), merchantStore)) {
			LOGGER.debug("Customer with email  {} already exists for this store ", customerRO.getEmailAddress());
			FieldError error = new FieldError("userName", "userName",
					messageService.getMessage("registration.username.already.exists", null, Locale.ENGLISH));
			bindingResult.addError(error);
		}
		try {
			customerFacade.registerCustomer(customerRO, merchantStore);
		} catch (BusinessException e) {
			LOGGER.error("Error while registering customer.. ", e);
			ObjectError error = new ObjectError("registration",
					messageService.getMessage("registration.failed", null, Locale.ENGLISH));
			bindingResult.addError(error);
			StringBuilder template = new StringBuilder().append(ShoppingControllerConstants.Customer.register);
			return template.toString();
		}
		try {
			Customer c = customerFacade.getCustomerByUserName(customerRO.getEmailAddress(), merchantStore);
			this.shoppingCartService.createEmptyCart(c);
			customerFacade.authenticate(c, c.getEmailAddress(), customerRO.getPassword());
			return "redirect:/";

		} catch (BusinessException e) {
			LOGGER.error("Cannot authenticate user ", e);
			ObjectError error = new ObjectError("registration",
					messageService.getMessage("registration.failed", Locale.ENGLISH, null));
			bindingResult.addError(error);
		}
		return "/";

	}

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
			String sessionShoppingCartCode = (String) request.getSession().getAttribute(ApplicationConstants.SHOPPING_CART);
			if (!StringUtils.isBlank(sessionShoppingCartCode)) {
				Cart shoppingCart = customerFacade.mergeCart(customerModel, sessionShoppingCartCode, store);
				ShoppingCartData shoppingCartData = this.populateShoppingCartData(shoppingCart, store);
				if (shoppingCartData != null) {
					request.getSession().setAttribute(ApplicationConstants.SHOPPING_CART, shoppingCartData.getCode());
				} else {
					// DELETE COOKIE
					Cookie c = new Cookie(ApplicationConstants.COOKIE_NAME_CART, "");
					c.setMaxAge(0);
					c.setPath(ApplicationConstants.SLASH);
					response.addCookie(c);
				}

			} else {
				Cart cartModel = shoppingCartService.getShoppingCartByCustomer(customerModel);
				if (cartModel != null) {
					request.getSession().setAttribute(ApplicationConstants.SHOPPING_CART, cartModel.getCode());
					Cookie c = new Cookie(ApplicationConstants.COOKIE_NAME_CART, cartModel.getCode());
					c.setMaxAge(60 * 24 * 3600);
					c.setPath(ApplicationConstants.SLASH);
					response.addCookie(c);
				}

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
