
package com.shoppay.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppay.app.repository.ShippingConfigurationRepository;
import com.shoppay.common.service.impl.AbstractDomainServiceImpl;
import com.shoppay.domain.ShippingConfiguration;
import com.shoppay.service.ShippingConfigurationService;

/**
 * Service Implementation for managing ProductOption.
 */
@Service
@Transactional
public class ShippingConfigurationServiceImpl extends AbstractDomainServiceImpl<ShippingConfiguration, Long>
		implements ShippingConfigurationService {

	private final Logger LOGGER = LoggerFactory.getLogger(ShippingConfigurationServiceImpl.class);
	private final ShippingConfigurationRepository shippingConfigurationRepository;

	public ShippingConfigurationServiceImpl(ShippingConfigurationRepository shippingConfigurationRepository) {
		super(shippingConfigurationRepository, null);
		this.shippingConfigurationRepository = shippingConfigurationRepository;
	}

}
