package com.shoppay.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.service.MerchantStoreService;

/**
 * REST controller for managing MerchantStore.
 */
@Controller
@RequestMapping( "/merchantStore")
public class MerchantStoreController extends AbstractDomainController<MerchantStore, Long> {

	private final Logger log = LoggerFactory.getLogger(MerchantStoreController.class);

	private final MerchantStoreService merchantStoreService;

	public MerchantStoreController(MerchantStoreService merchantStoreService) {
		super(merchantStoreService, MerchantStore.class);
		this.merchantStoreService = merchantStoreService;
	}

	@Override
	protected void preNew(ModelAndView model) {

	}

	

}