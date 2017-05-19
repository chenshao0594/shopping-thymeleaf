package com.smartshop.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartshop.admin.controller.AbstractDomainController;
import com.smartshop.admin.controller.ProductOptionController;
import com.smartshop.constants.AppConstants;
import com.smartshop.domain.ShippingConfiguration;
import com.smartshop.service.ShippingConfigurationService;

@Controller
@RequestMapping(AppConstants.ADMIN_PREFIX + "/" + ShippingConfigurationController.SECTION_KEY)
public class ShippingConfigurationController extends AbstractDomainController<ShippingConfiguration, Long> {

	private final Logger log = LoggerFactory.getLogger(ProductOptionController.class);
	public static final String SECTION_KEY = "shippingConfigurations";

	private final ShippingConfigurationService shippingConfigurationService;

	public ShippingConfigurationController(ShippingConfigurationService shippingConfigurationService) {
		super(shippingConfigurationService);
		this.shippingConfigurationService = shippingConfigurationService;
	}

	@Override
	protected String getSectionKey() {
		return this.SECTION_KEY;
	}

	@Override
	protected Class getEntityClass() {
		return ShippingConfiguration.class;
	}

}