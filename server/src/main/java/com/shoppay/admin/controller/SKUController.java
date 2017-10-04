package com.shoppay.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppay.core.catalog.SKU;
import com.shoppay.core.catalog.service.SKUService;

/**
 * REST controller for managing ProductOption.
 */
@Controller
@RequestMapping( "/sku")
public class SKUController extends AbstractDomainController<SKU, Long> {

	private final Logger log = LoggerFactory.getLogger(SKUController.class);
	private final SKUService SKUService;

	public SKUController(SKUService SKUService) {
		super(SKUService, SKU.class);
		this.SKUService = SKUService;
	}

	

}