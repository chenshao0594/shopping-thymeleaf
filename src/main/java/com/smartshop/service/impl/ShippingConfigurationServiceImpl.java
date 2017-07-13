
package com.smartshop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartshop.app.repository.ShippingConfigurationRepository;
import com.smartshop.domain.ShippingConfiguration;
import com.smartshop.service.ShippingConfigurationService;

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
