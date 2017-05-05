
package com.smart.shopping.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.shopping.core.catalog.ProductOption;
import com.smart.shopping.core.catalog.service.ProductOptionService;
import com.smart.shopping.domain.MerchantStore;
import com.smart.shopping.repository.ProductOptionRepository;

/**
 * Service Implementation for managing ProductOption.
 */
@Service
@Transactional
public class ProductOptionServiceImpl extends AbstractDomainServiceImpl<ProductOption, Long>
		implements ProductOptionService {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductOptionServiceImpl.class);
	private final ProductOptionRepository productOptionRepository;

	public ProductOptionServiceImpl(ProductOptionRepository productOptionRepository) {
		super(productOptionRepository, null);
		this.productOptionRepository = productOptionRepository;
	}

	@Override
	public ProductOption getByCode(MerchantStore store, String optionCode) {
		return productOptionRepository.findByCode(store.getId(), optionCode);
	}

}
