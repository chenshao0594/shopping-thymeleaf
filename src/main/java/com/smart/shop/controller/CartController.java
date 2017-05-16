package com.smart.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codahale.metrics.annotation.Timed;

@Controller("ShopCartController")
@RequestMapping("/cart")
public class CartController {

	@Timed
	@GetMapping()
	public ModelAndView detail(ModelAndView model) throws Exception {
		model.setViewName("shop/cart/detail");
		return model;
	}

}
