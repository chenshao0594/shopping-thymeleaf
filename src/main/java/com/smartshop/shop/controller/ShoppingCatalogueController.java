package com.smartshop.shop.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartshop.core.catalog.service.CategoryService;
import com.smartshop.service.ProductService;

@Controller("ShopCatalogueController")
@RequestMapping("/catalogue")
public class ShoppingCatalogueController {

	private final Logger LOGGER = LoggerFactory.getLogger(ShoppingCatalogueController.class);

	@Inject
	private CategoryService categoryService;
	@Inject
	private ProductService productService;

}
