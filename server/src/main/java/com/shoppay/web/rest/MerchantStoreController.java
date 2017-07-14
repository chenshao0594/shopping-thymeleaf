package com.shoppay.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shoppay.admin.controller.AbstractDomainController;
import com.shoppay.common.constants.AppConstants;
import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.service.MerchantStoreService;

/**
 * REST controller for managing MerchantStore.
 */
@Controller
@RequestMapping(AppConstants.ADMIN_PREFIX + "/" + MerchantStoreController.SECTION_KEY)
public class MerchantStoreController extends AbstractDomainController<MerchantStore, Long> {

	private final Logger log = LoggerFactory.getLogger(MerchantStoreController.class);
	public static final String SECTION_KEY = "merchantStores";
	private static final Class ENTITY_CLASS = MerchantStore.class;

	private final MerchantStoreService merchantStoreService;

	public MerchantStoreController(MerchantStoreService merchantStoreService) {
		super(merchantStoreService);
		this.merchantStoreService = merchantStoreService;
	}

	@Override
	protected void preNew(ModelAndView model) {

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