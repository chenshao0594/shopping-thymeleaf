package com.shoppay.shop.controller;

import java.time.ZonedDateTime;
import java.util.Locale;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codahale.metrics.annotation.Timed;
import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.common.model.KeyAndPasswordVM;
import com.shoppay.common.service.MailService;
import com.shoppay.common.service.MessageService;
import com.shoppay.common.utils.RandomUtil;
import com.shoppay.core.cart.service.CartService;
import com.shoppay.core.customer.Customer;
import com.shoppay.core.customer.model.CustomerRO;
import com.shoppay.core.facade.CustomerFacade;
import com.shoppay.core.utils.CustomerInfoContextHolder;
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
	private MessageService messageService;

	@Inject
	private MailService mailService;

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
		MerchantStore merchantStore = CustomerInfoContextHolder.getMerchantStore();

		if(!customerRO.getPassword().equals(customerRO.getPassword2())) {
			LOGGER.warn("found validation error {}", bindingResult.getAllErrors());
			return ShoppingControllerConstants.Customer.register;	
		}else if (customerFacade.checkIfUserExists(customerRO.getEmailAddress(), merchantStore)) {
			LOGGER.warn("Customer with email  {} already exists for this store ", customerRO.getEmailAddress());
			FieldError error = new FieldError("userName", "userName",
					messageService.getMessage("registration.username.already.exists", null, Locale.ENGLISH));
			bindingResult.addError(error);
		}

		if (bindingResult.hasErrors()) {
			LOGGER.error("found validation error {}", bindingResult.getAllErrors());
			return ShoppingControllerConstants.Customer.register;
		}


		try {
			customerFacade.registerCustomer(customerRO, merchantStore);
		} catch (BusinessException e) {
			LOGGER.error("Error while registering customer.. ", e);
			ObjectError error = new ObjectError("registration",
					messageService.getMessage("registration.failed", null, Locale.ENGLISH));
			bindingResult.addError(error);
		}
		try {
			Customer c = customerFacade.getCustomerByUserName(customerRO.getEmailAddress(), merchantStore);
			this.shoppingCartService.createEmptyCart(c);
			customerFacade.authenticate(c, c.getEmailAddress(), customerRO.getPassword());
			return "redirect:/";

		} catch (BusinessException e) {
			LOGGER.error("Cannot authenticate user ", e);
			ObjectError error = new ObjectError("registration",
					messageService.getMessage("registration.failed",CustomerInfoContextHolder.getMerchantStore().getLocale(), null));
			bindingResult.addError(error);
			return ShoppingControllerConstants.Customer.register;
		}
	}

	@Timed
	@PostMapping(path = "/resetPassword/init")
	public String requestPasswordReset(String loginKey) throws BusinessException {
		MerchantStore store = CustomerInfoContextHolder.getMerchantStore();
		Customer customer = this.customerFacade.getCustomerByEmailAddress(loginKey, store);
		if(customer==null) {
			customer = this.customerFacade.getCustomerByUserName(loginKey, store);			
		}
		if(customer==null) {
			throw new BusinessException("{}  not exist ", loginKey) ;
		}
		customer.setResetKey(RandomUtil.generateResetKey());
		customer.setResetDate(ZonedDateTime.now());
		mailService.sendCustomerPasswordResetMail(customer);
		return ShoppingControllerConstants.Customer.resetPasswordInit;
	}

	@Timed
	@PostMapping(path = "changePassword")
	public String  changePassword(@RequestBody String password) {
		Customer customer = CustomerInfoContextHolder.getCustomer();
		customerFacade.changePassword(customer, password);
		return ShoppingControllerConstants.Customer.changePasswordSuccess;
	}

	@Timed
	@PostMapping(path="/resetPassword/finish")
	public String finishPasswordReset(@RequestBody KeyAndPasswordVM keyAndPassword, Model model) {
		Customer customer =	this.customerFacade.completePasswordReset(keyAndPassword.getNewPassword(), keyAndPassword.getKey());
		model.addAttribute("customer", customer);
		return ShoppingControllerConstants.Customer.resetPasswordSuccess;
	}
}
