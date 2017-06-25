package com.smartshop.shop.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codahale.metrics.annotation.Timed;
import com.smartshop.domain.Customer;
import com.smartshop.service.CountryService;
import com.smartshop.service.CustomerService;

@Controller("ShopCheckOutController")
@RequestMapping("/checkout")
public class ShoppingCheckOutController extends AbstractShoppingController {

	private final Logger LOGGER = LoggerFactory.getLogger(ShoppingCheckOutController.class);
	private final static String CHECKOUT_START = "shop/checkout/checkout_start";
	private final static String CHECKOUT_ADDRESS = "shop/checkout/checkout_address";
	@Inject
	private CustomerService customerService;

	@Inject
	private CountryService countryService;

	@Timed
	@GetMapping()
	public ModelAndView checkout(ModelAndView model, final HttpServletRequest request) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LOGGER.info("principal user {}", principal);
		if (principal instanceof UserDetails) {
			LOGGER.info("UserDetails user {}", principal);
			String username = ((UserDetails) principal).getUsername();
			Customer customer = customerService.findCustomerByName(username);
			Page page = this.countryService.findAll(null);
			model.addObject("countries", page.getContent());
			model.addObject("customer", customer);
			model.setViewName(CHECKOUT_ADDRESS);
		} else {
			LOGGER.info(" not UserDetails user {}", principal.getClass());
			model.setViewName(CHECKOUT_START);
		}
		return model;
	}

}
