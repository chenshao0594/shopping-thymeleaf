package com.smartshop.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codahale.metrics.annotation.Timed;

@Controller("ShopCheckOutController")
@RequestMapping("/checkout")
public class CheckOutController extends AbstractShopController {

	private final Logger LOGGER = LoggerFactory.getLogger(CheckOutController.class);
	private final static String CHECKOUT_START = "shop/checkout/checkout_start";

	@Timed
	@GetMapping()
	public String checkout(ModelAndView model, final HttpServletRequest request) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LOGGER.info("principal user {}", principal);
		if (principal instanceof UserDetails) {
			LOGGER.info("UserDetails user {}", principal);
			String username = ((UserDetails) principal).getUsername();
		} else {
			LOGGER.info(" not UserDetails user {}", principal.getClass());
			String username = principal.toString();
			return CHECKOUT_START;
		}

		return CHECKOUT_START;

	}

}
