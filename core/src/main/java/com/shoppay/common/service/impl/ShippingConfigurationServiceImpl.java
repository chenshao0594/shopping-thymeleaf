
package com.shoppay.common.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppay.common.repository.ShippingConfigurationRepository;
import com.shoppay.common.service.ShippingConfigurationService;
import com.shoppay.domain.ShippingConfiguration;

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
		super(shippingConfigurationRepository);
		this.shippingConfigurationRepository = shippingConfigurationRepository;
	}

}
