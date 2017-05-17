package com.smart.shop.controller;

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
import com.smart.shopping.attachment.common.AttachmentEnum;
import com.smart.shopping.core.catalog.Product;
import com.smart.shopping.domain.Attachment;
import com.smart.shopping.service.AttachmentService;
import com.smart.shopping.service.ProductService;

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
		model.addObject("product", product);
		model.addObject("images", images);
		LOGGER.info("product images info {}", images);
		model.setViewName("shop/product/detail");
		return model;
	}

}
