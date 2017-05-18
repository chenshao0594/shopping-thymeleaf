package com.smartshop.shop.controller;

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
import com.smartshop.attachment.common.AttachmentEnum;
import com.smartshop.core.catalog.Product;
import com.smartshop.domain.Attachment;
import com.smartshop.service.AttachmentService;
import com.smartshop.service.ProductService;
import com.smartshop.shop.model.ProductOptionDTO;
import com.smartshop.shop.model.ProductOptionPricing;

@Controller("ShopProductController")
@RequestMapping("/product")
public class ProductController {
	private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Inject
	private ProductService productService;
	@Inject
	private AttachmentService attachmentService;

	@Timed
	@GetMapping(value = "/{id}")
	public ModelAndView detail(@PathVariable("id") Long id, ModelAndView model) throws Exception {
		Product product = this.productService.findOne(id);
		List<Attachment> images = this.attachmentService.findAllByBOInfo("product", id, AttachmentEnum.IMAGE);
		List<ProductOptionPricing> skuPricing = this.productService.buildSKUsPricing(product);
		List<ProductOptionDTO> allProductOptions = this.productService.buildProductOptionsDTO(product);
		Gson gson = new Gson();
		String skuPricingJson = gson.toJson(skuPricing);
		String allProductOptionsJson = gson.toJson(allProductOptions);
		model.addObject("product", product);
		model.addObject("images", images);
		model.addObject("skuPricing", skuPricingJson);
		model.addObject("allProductOptions", allProductOptionsJson);
		model.setViewName("shop/product/detail");
		return model;
	}

}
