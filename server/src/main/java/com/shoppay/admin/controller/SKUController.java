package com.shoppay.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppay.common.constants.AppConstants;
import com.shoppay.core.catalog.SKU;
import com.shoppay.core.catalog.service.SKUService;

/**
 * REST controller for managing ProductOption.
 */
@Controller
@RequestMapping(AppConstants.ADMIN_PREFIX + "/" + SKUController.SECTION_KEY)
public class SKUController extends AbstractDomainController<SKU, Long> {

	private final Logger log = LoggerFactory.getLogger(SKUController.class);
	public static final String SECTION_KEY = "skus";
	private static final Class ENTITY_CLASS = SKU.class;

	private final SKUService SKUService;

	public SKUController(SKUService SKUService) {
		super(SKUService);
		this.SKUService = SKUService;
	}

	@Override
	protected String getSectionKey() {
		return SECTION_KEY;
	}

	@Override
	protected Class getEntityClass() {
		return ENTITY_CLASS;
	}

}