package com.smart.shopping.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.shopping.admin.controller.AbstractDomainController;
import com.smart.shopping.admin.controller.ProductOptionController;
import com.smart.shopping.config.AppConstants;
import com.smart.shopping.core.catalog.service.PaymentConfigurationService;
import com.smart.shopping.domain.PaymentConfiguration;

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