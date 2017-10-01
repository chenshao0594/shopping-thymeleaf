package com.shoppay.shop.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codahale.metrics.annotation.Timed;
import com.shoppay.common.domain.MerchantStore;
import com.shoppay.core.catalog.Category;
import com.shoppay.core.catalog.Product;
import com.shoppay.core.catalog.service.CategoryService;
import com.shoppay.core.catalog.service.ProductService;
import com.shoppay.shop.model.CategoryNode;
import com.shoppay.shop.utils.CustomerInfoContextHolder;
import com.shoppay.web.constants.ShoppingControllerConstants;

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
	public String catalogue(Model model, Pageable pageable, HttpServletRequest request) throws Exception {
		MerchantStore store = CustomerInfoContextHolder.getMerchantStore();
		List<Category> categories = this.categoryService.findCategoryTreeByStore(store);
		Page<Product> productPage = this.productService.findAll(pageable);
		model.addAttribute("categories",  categories);
		model.addAttribute("page", productPage);
		return ShoppingControllerConstants.Catalog.catalogue;
	}
	
	@Timed
	@GetMapping("/{catalogueId}")
	public String catalogue(@PathVariable long catalogueId, Model model, Pageable pageable, HttpServletRequest request) throws Exception {
		LOGGER.info("find products by catelogueId {}", catalogueId);
		MerchantStore store = CustomerInfoContextHolder.getMerchantStore();
		Category category = this.categoryService.findOne(catalogueId);
		List<Category> categories = this.categoryService.findCategoryTreeByStore(store);
		Page<Product> productPage = this.productService.findAllByCategory(category, pageable);
		model.addAttribute("categories", categories);
		model.addAttribute("page", productPage);
		return ShoppingControllerConstants.Catalog.catalogue;
	}

	
	@Timed
	@PostMapping("/search")
	public String search(String query, Model model, Pageable pageable, HttpSession session) throws Exception {
		Page<Product> productPage = this.productService.searchByName(query, pageable);
		session.setAttribute("query", query);
		model.addAttribute("page", productPage);
		return ShoppingControllerConstants.Catalog.search;
	}

//	@Timed
//	@GetMapping("/{friendlyUrl}")
//	public String displayCategoryNoReference(@PathVariable final String friendlyUrl, Model model,
//			HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {
//		return this.displayCategory(friendlyUrl, null, model, request, response, locale);
//	}

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
			return ShoppingControllerConstants.Catalog.catalogue;
		}
		if (!category.isVisible()) {
			return ShoppingControllerConstants.Catalog.catalogue;
		}
		return ShoppingControllerConstants.Catalog.catalogue;
	}
	
	private List<CategoryNode> buildCategoryNode(List<Category>  categories){
		List<CategoryNode> nodes = new LinkedList<CategoryNode>();
		for(Category item : categories) {
			CategoryNode node = new CategoryNode();
			node.setId(item.getId());
			node.setpId(item.getParent().getId());
			node.setParent(item.getParent().getId()==-1);
			node.setOpen(item.getParent().getId()==-1);
			node.setName(item.getCode());
			nodes.add(node);
		}
		return nodes;
	}
}
