package com.smartshop.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartshop.admin.controller.AbstractDomainController;
import com.smartshop.admin.controller.ProductOptionController;
import com.smartshop.config.AppConstants;
import com.smartshop.core.catalog.service.PaymentConfigurationService;
import com.smartshop.domain.PaymentConfiguration;

@Controller
@RequestMapping(AppConstants.ADMIN_PREFIX + "/" + PaymentConfigurationController.SECTION_KEY)
public class PaymentConfigurationController extends AbstractDomainController<PaymentConfiguration, Long> {

	private final Logger log = LoggerFactory.getLogger(ProductOptionController.class);
	public static final String SECTION_KEY = "paymentConfigurations";

	private final PaymentConfigurationService paymentConfigurationService;

	public PaymentConfigurationController(PaymentConfigurationService paymentConfigurationService) {
		super(paymentConfigurationService);
		this.paymentConfigurationService = paymentConfigurationService;
	}

	@Override
	protected String getSectionKey() {
		return this.SECTION_KEY;
	}

	@Override
	protected Class getEntityClass() {
		return PaymentConfiguration.class;
	}

}