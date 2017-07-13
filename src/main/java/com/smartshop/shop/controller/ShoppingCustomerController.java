package com.smartshop.shop.controller;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codahale.metrics.annotation.Timed;
import com.smartshop.common.ShoppingControllerConstants;
import com.smartshop.common.service.MessageService;
import com.smartshop.core.customer.Customer;
import com.smartshop.core.customer.model.CustomerRO;
import com.smartshop.core.order.SalesOrder;
import com.smartshop.core.order.service.SalesOrderService;
import com.smartshop.domain.MerchantStore;
import com.smartshop.exception.BusinessException;
import com.smartshop.service.CustomerFacade;
import com.smartshop.service.CustomerService;
import com.smartshop.service.MailService;
import com.smartshop.service.MerchantStoreService;
import com.smartshop.shop.utils.UserInfoContextHolder;

@Controller("ShopCustomerController")
@RequestMapping("/customer")

public class ShoppingCustomerController extends AbstractShoppingController {
	private final Logger LOGGER = LoggerFactory.getLogger(ShoppingCustomerController.class);

	@Inject
	private CustomerService customerService;

	@Inject
	private CustomerFacade customerFacade;

	@Inject
	private MerchantStoreService merchantStoreService;

	@Inject
	private MessageService messageService;

	@Inject
	private MailService emailService;

	@Inject
	private SalesOrderService orderService;

	@Timed
	@GetMapping(value = "/register")
	public ModelAndView displayRegistration(ModelAndView model) throws Exception {
		model.setViewName(ShoppingControllerConstants.Customer.register);
		return model;
	}

	@Timed
	@PostMapping(value = "/register")
	public String registerCustomer(@Valid CustomerRO customer, BindingResult bindingResult, Model model)
			throws Exception {
		MerchantStore merchantStore = this.merchantStoreService.findOne(1L);

		if (bindingResult.hasErrors()) {
			LOGGER.error("found validation error {}", bindingResult.getAllErrors());
			LOGGER.debug("found {} validation error while validating in customer registration ",
					bindingResult.getErrorCount());
			StringBuilder template = new StringBuilder().append(ShoppingControllerConstants.Customer.register);
			return template.toString();
		}

		if (customerFacade.checkIfUserExists(customer.getEmailAddress(), merchantStore)) {
			LOGGER.debug("Customer with email  {} already exists for this store ", customer.getEmailAddress());
			FieldError error = new FieldError("userName", "userName",
					messageService.getMessage("registration.username.already.exists", Locale.ENGLISH));
			bindingResult.addError(error);
		}
		try {
			customerFacade.registerCustomer(customer, merchantStore);
		} catch (BusinessException e) {
			LOGGER.error("Error while registering customer.. ", e);
			ObjectError error = new ObjectError("registration",
					messageService.getMessage("registration.failed", Locale.ENGLISH));
			bindingResult.addError(error);
			StringBuilder template = new StringBuilder().append(ShoppingControllerConstants.Customer.register);
			return template.toString();
		}
		try {
			// refresh customer
			Customer c = customerFacade.getCustomerByUserName(customer.getEmailAddress(), merchantStore);
			// authenticate
			customerFacade.authenticate(c, c.getEmailAddress(), customer.getPassword());
			return "redirect:/shop";

		} catch (BusinessException e) {
			LOGGER.error("Cannot authenticate user ", e);
			ObjectError error = new ObjectError("registration",
					messageService.getMessage("registration.failed", Locale.ENGLISH));
			bindingResult.addError(error);
		}
		return "/";

	}

	@GetMapping(value = "/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
	}

	@GetMapping(value = "/orders")
	public String orders(Model model) {
		Customer customer = UserInfoContextHolder.getCustomer();
		List<SalesOrder> orders = this.orderService.findByCustomer(customer);
		model.addAttribute("orders", orders);
		return ShoppingControllerConstants.Customer.customerOrders;
	}

}
