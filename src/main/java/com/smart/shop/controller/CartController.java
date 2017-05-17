package com.smart.shop.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codahale.metrics.annotation.Timed;
import com.smart.shopping.core.cart.service.ShoppingCartService;

@Controller("ShopCartController")
@RequestMapping("/cart")
public class CartController {

	@Inject
	private ShoppingCartService cartService;

	@Timed
	@GetMapping()
	public ModelAndView detail(ModelAndView model) throws Exception {
		model.setViewName("shop/cart/detail");
		return model;
	}

	@Timed
	@PostMapping()
	public ModelAndView addItem(ModelAndView model) throws Exception {
		model.setViewName("shop/cart/detail");
		return model;
	}

	@Timed
	@DeleteMapping()
	public ModelAndView deleteItem(ModelAndView model) throws Exception {
		model.setViewName("shop/cart/detail");
		return model;
	}
}
