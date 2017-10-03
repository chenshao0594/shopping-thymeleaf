package com.shoppay.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shoppay.common.constants.ApplicationConstants;
import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.service.MerchantStoreService;
import com.shoppay.core.customer.Customer;

/**
 * REST controller for managing MerchantStore.
 */
@Controller
@RequestMapping(ApplicationConstants.ADMIN_PREFIX + "/merchantStore")
public class MerchantStoreController extends AbstractDomainController<MerchantStore, Long> {

	private final Logger log = LoggerFactory.getLogger(MerchantStoreController.class);
	public static final String SECTION_KEY = "merchantStores";
	private static final Class ENTITY_CLASS = MerchantStore.class;

	private final MerchantStoreService merchantStoreService;

	public MerchantStoreController(MerchantStoreService merchantStoreService) {
		super(merchantStoreService, MerchantStore.class);
		this.merchantStoreService = merchantStoreService;
	}

	@Override
	protected void preNew(ModelAndView model) {

	}

	

}