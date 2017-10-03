package com.shoppay.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppay.common.constants.ApplicationConstants;
import com.shoppay.common.service.ShippingConfigurationService;
import com.shoppay.domain.ShippingConfiguration;

@Controller
@RequestMapping(ApplicationConstants.ADMIN_PREFIX + "/shippingConfiguration")
public class ShippingConfigurationController extends AbstractDomainController<ShippingConfiguration, Long> {

	private final Logger log = LoggerFactory.getLogger(ProductOptionController.class);

	private final ShippingConfigurationService shippingConfigurationService;

	public ShippingConfigurationController(ShippingConfigurationService shippingConfigurationService) {
		super(shippingConfigurationService,ShippingConfiguration.class);
		this.shippingConfigurationService = shippingConfigurationService;
	}

	

}