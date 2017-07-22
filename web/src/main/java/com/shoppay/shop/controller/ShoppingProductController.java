package com.shoppay.shop.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codahale.metrics.annotation.Timed;
import com.google.gson.Gson;
import com.shoppay.common.attachment.AttachmentEnum;
import com.shoppay.common.domain.Attachment;
import com.shoppay.common.service.AttachmentService;
import com.shoppay.core.catalog.Product;
import com.shoppay.core.catalog.SKU;
import com.shoppay.core.catalog.model.ProductOptionDTO;
import com.shoppay.core.catalog.model.ProductOptionPricing;
import com.shoppay.core.catalog.service.ProductService;
import com.shoppay.core.catalog.service.SKUService;
import com.shoppay.web.constants.ShoppingControllerConstants;

@Controller("ShopProductController")
@RequestMapping("/product")
public class ShoppingProductController {
	private final Logger LOGGER = LoggerFactory.getLogger(ShoppingProductController.class);

	@Inject
	private ProductService productService;

	@Inject
	private SKUService skuService;
	@Inject
	private AttachmentService attachmentService;

	@Timed
	@GetMapping(value = "/{id}")
	public ModelAndView detail(@PathVariable("id") Long id, ModelAndView model) throws Exception {
		Product product = this.productService.findOne(id);
		List<Attachment> images = this.attachmentService.findAllByBOInfo("product", id, AttachmentEnum.IMAGE);
		List<ProductOptionPricing> skuPricing = this.productService.buildSKUsPricing(product);
		List<ProductOptionDTO> allProductOptions = this.productService.buildProductOptionsDTO(product);
		SKU defaultSKU = this.skuService.findDefaultSKU(id);
		product.setDefaultSKU(defaultSKU);
		Gson gson = new Gson();
		String skuPricingJson = gson.toJson(skuPricing);
		String allProductOptionsJson = gson.toJson(allProductOptions);
		model.addObject("product", product);
		model.addObject("images", images);
		model.addObject("skuPricing", skuPricingJson);
		model.addObject("allProductOptions", allProductOptionsJson);
		model.setViewName(ShoppingControllerConstants.Product.detail);
		return model;
	}

	// @Timed
	// @GetMapping(value = "/{searchURL}")
	// public ModelAndView findBySearchURL(@PathVariable("searchURL") String
	// url, ModelAndView model) throws Exception {
	// Product product = this.productService.findBySearchURL(url);
	// List<Attachment> images =
	// this.attachmentService.findAllByBOInfo("product", product.getId(),
	// AttachmentEnum.IMAGE);
	// List<ProductOptionPricing> skuPricing =
	// this.productService.buildSKUsPricing(product);
	// List<ProductOptionDTO> allProductOptions =
	// this.productService.buildProductOptionsDTO(product);
	// SKU defaultSKU = this.skuService.findDefaultSKU(product.getId());
	// product.setDefaultSKU(defaultSKU);
	// Gson gson = new Gson();
	// String skuPricingJson = gson.toJson(skuPricing);
	// String allProductOptionsJson = gson.toJson(allProductOptions);
	// model.addObject("product", product);
	// model.addObject("images", images);
	// model.addObject("skuPricing", skuPricingJson);
	// model.addObject("allProductOptions", allProductOptionsJson);
	// model.setViewName("shop/product/detail");
	// return model;
	// }

}
