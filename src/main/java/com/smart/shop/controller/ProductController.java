package com.smart.shop.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codahale.metrics.annotation.Timed;
import com.smart.shopping.core.catalog.Product;
import com.smart.shopping.service.ProductService;

@Controller("ShopProductController")
@RequestMapping("/product")
public class ProductController {

	@Inject
	private ProductService productService;

	@Timed
	@GetMapping(value = "/{id}")
	public ModelAndView detail(@PathVariable("id") Long id, ModelAndView model) throws Exception {
		Product product = this.productService.findOne(id);
		model.addObject("product", product);
		model.setViewName("shop/product/detail");
		return model;
	}

}
