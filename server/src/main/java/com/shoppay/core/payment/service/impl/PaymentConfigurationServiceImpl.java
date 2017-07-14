
package com.shoppay.core.payment.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppay.app.repository.PaymentConfigurationRepository;
import com.shoppay.common.service.impl.AbstractDomainServiceImpl;
import com.shoppay.core.payment.PaymentConfiguration;
import com.shoppay.core.payment.service.PaymentConfigurationService;

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
