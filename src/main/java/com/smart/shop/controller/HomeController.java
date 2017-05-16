package com.smart.shop.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codahale.metrics.annotation.Timed;
import com.smart.shopping.core.catalog.service.CategoryService;
import com.smart.shopping.exception.BusinessException;
import com.smart.shopping.service.ProductService;

@Controller("ShopHomeController")
@RequestMapping("/shop")
public class HomeController {
	private final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Timed
	@GetMapping()
	public ModelAndView querySKUs(ModelAndView model) throws BusinessException {
		LOGGER.info("REQUEST HOME ....");
		List<Map<String, Long>> counts = this.productService.countProductsByCategories();
		LOGGER.info("counts map {}", counts);
		model.addObject("products", this.productService.findAll(new PageRequest(0, 20)));
		model.addObject("categoryinfo", counts);
		model.setViewName("shop/home");
		return model;
	}
}
