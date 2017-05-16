package com.smart.shop.controller;

import java.util.Locale;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.smart.shop.common.ShopControllerConstants;
import com.smart.shop.customer.CustomerModel;
import com.smart.shopping.common.service.MessageService;
import com.smart.shopping.domain.Customer;
import com.smart.shopping.domain.MerchantStore;
import com.smart.shopping.service.CustomerFacade;
import com.smart.shopping.service.CustomerService;
import com.smart.shopping.service.MerchantStoreService;

@Controller("ShopCustomerController")
@RequestMapping("/customer")
public class CustomerRegistrationController {
	private final Logger LOGGER = LoggerFactory.getLogger(CustomerRegistrationController.class);

	@Inject
	private CustomerService customerService;

	@Inject
	private CustomerFacade customerFacade;

	@Inject
	private MerchantStoreService merchantStoreService;

	@Inject
	private MessageService messageService;

	@GetMapping(value = "/registration")
	public ModelAndView displayRegistration(ModelAndView model) throws Exception {
		model.setViewName("shop/register");
		return model;
	}

	@PostMapping(value = "/register")
	public String registerCustomer(@Valid @ModelAttribute("customer") CustomerModel customer,
			BindingResult bindingResult, Model model) throws Exception {
		MerchantStore merchantStore = this.merchantStoreService.findOne(1L);

		if (bindingResult.hasErrors()) {
			LOGGER.debug("found {} validation error while validating in customer registration ",
					bindingResult.getErrorCount());
			StringBuilder template = new StringBuilder().append(ShopControllerConstants.Tiles.Customer.register);
			return template.toString();

		}

		if (customerFacade.checkIfUserExists(customer.getUserName(), merchantStore)) {
			LOGGER.debug("Customer with username {} already exists for this store ", customer.getUserName());
			FieldError error = new FieldError("userName", "userName",
					messageService.getMessage("registration.username.already.exists", Locale.ENGLISH));
			bindingResult.addError(error);
		}
		try {
			CustomerModel customerData = customerFacade.registerCustomer(customer, merchantStore);
		} catch (Exception e) {
			LOGGER.error("Error while registering customer.. ", e);
			ObjectError error = new ObjectError("registration",
					messageService.getMessage("registration.failed", Locale.ENGLISH));
			bindingResult.addError(error);
			StringBuilder template = new StringBuilder().append(ShopControllerConstants.Tiles.Customer.register);
			return template.toString();
		}

		/**
		 * Send registration email
		 */
		/*
		 * emailTemplatesUtils.sendRegistrationEmail(customer, merchantStore,
		 * locale, request.getContextPath());
		 */

		try {
			// refresh customer
			Customer c = customerFacade.getCustomerByUserName(customer.getUserName(), merchantStore);
			// authenticate
			customerFacade.authenticate(c, c.getEmailAddress(), c.getPassword());

			return "redirect:/shop/customer/dashboard.html";

		} catch (Exception e) {
			LOGGER.error("Cannot authenticate user ", e);
			ObjectError error = new ObjectError("registration",
					messageService.getMessage("registration.failed", Locale.ENGLISH));
			bindingResult.addError(error);
		}

		StringBuilder template = new StringBuilder().append(ShopControllerConstants.Tiles.Customer.register);
		return template.toString();

	}

}
