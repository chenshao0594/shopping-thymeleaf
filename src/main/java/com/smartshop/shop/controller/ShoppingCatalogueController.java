package com.smartshop.shop.controller;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codahale.metrics.annotation.Timed;
import com.smartshop.common.ShoppingControllerConstants;
import com.smartshop.core.catalog.Category;
import com.smartshop.core.catalog.Product;
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

	@Timed
	@GetMapping("")
	public String catalogue(Model model, Pageable pageable, HttpServletRequest request, HttpServletResponse response,
			Locale locale) throws Exception {
		Page<Category> page = this.categoryService.findAll(null);
		Page<Product> productPage = this.productService.findAll(pageable);
		model.addAttribute("categories", page.getContent());
		model.addAttribute("page", productPage);
		return ShoppingControllerConstants.Catalogue.catalogue;
	}

	@Timed
	@GetMapping("/{friendlyUrl}.html")
	public String displayCategoryNoReference(@PathVariable final String friendlyUrl, Model model,
			HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
		return this.displayCategory(friendlyUrl, null, model, request, response, locale);
	}

	@Timed
	@GetMapping("/{friendlyUrl}.html/ref={ref}")
	public String displayCategoryWithReference(@PathVariable final String friendlyUrl, @PathVariable final String ref,
			Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {

		return this.displayCategory(friendlyUrl, ref, model, request, response, locale);
	}

	private String displayCategory(final String friendlyUrl, final String ref, Model model, HttpServletRequest request,
			HttpServletResponse response, Locale locale) throws Exception {

		Category category = categoryService.findBySearchURL(friendlyUrl);
		if (category == null) {
			LOGGER.error("No category found for friendlyUrl " + friendlyUrl);
			return ShoppingControllerConstants.Catalogue.catalogue;
		}
		if (!category.isVisible()) {
			return ShoppingControllerConstants.Catalogue.catalogue;
		}
		return ShoppingControllerConstants.Catalogue.catalogue;
	}
	// @Timed
	// @GetMapping(value = "")
	// public ModelAndView catalogue(Pageable pageable, ModelAndView model)
	// throws Exception {
	//
	// Page<Product> productPage = this.productService.findAll(pageable);
	// model.addObject("page", productPage);
	// model.setViewName(ShopControllerConstants.Catalogue.catalogue);
	// return model;
	//
	// }

}
