
package com.smartshop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartshop.core.catalog.service.PaymentConfigurationService;
import com.smartshop.domain.PaymentConfiguration;
import com.smartshop.repository.PaymentConfigurationRepository;

/**
 * Service Implementation for managing ProductOption.
 */
@Service
@Transactional
public class PaymentConfigurationServiceImpl extends AbstractDomainServiceImpl<PaymentConfiguration, Long>
		implements PaymentConfigurationService {

	private final Logger LOGGER = LoggerFactory.getLogger(PaymentConfigurationServiceImpl.class);
	private final PaymentConfigurationRepository paymentConfigurationRepository;

	public PaymentConfigurationServiceImpl(PaymentConfigurationRepository paymentConfigurationRepository) {
		super(paymentConfigurationRepository, null);
		this.paymentConfigurationRepository = paymentConfigurationRepository;
	}

}
