package com.shoppay.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppay.core.payment.PaymentConfiguration;
import com.shoppay.core.payment.service.PaymentConfigurationService;

@Controller
@RequestMapping( "/paymentConfiguration")
public class PaymentConfigurationController extends AbstractDomainController<PaymentConfiguration, Long> {

	private final Logger log = LoggerFactory.getLogger(ProductOptionController.class);
	public static final String SECTION_KEY = "paymentConfigurations";

	private final PaymentConfigurationService paymentConfigurationService;

	public PaymentConfigurationController(PaymentConfigurationService paymentConfigurationService) {
		super(paymentConfigurationService, PaymentConfiguration.class);
		this.paymentConfigurationService = paymentConfigurationService;
	}

}