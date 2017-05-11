package com.smart.shopping.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart.shopping.admin.controller.AbstractDomainController;
import com.smart.shopping.config.AppConstants;
import com.smart.shopping.domain.MerchantStore;
import com.smart.shopping.service.MerchantStoreService;

/**
 * REST controller for managing MerchantStore.
 */
@RestController
@RequestMapping("/" + AppConstants.ADMIN_PREFIX + "/" + MerchantStoreController.SECTION_KEY)
public class MerchantStoreController extends AbstractDomainController<MerchantStore, Long> {

	private final Logger log = LoggerFactory.getLogger(MerchantStoreController.class);
	public static final String SECTION_KEY = "merchant-stores";
	private static final Class ENTITY_CLASS = MerchantStore.class;

	private final MerchantStoreService merchantStoreService;

	public MerchantStoreController(MerchantStoreService merchantStoreService) {
		super(merchantStoreService);
		this.merchantStoreService = merchantStoreService;
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