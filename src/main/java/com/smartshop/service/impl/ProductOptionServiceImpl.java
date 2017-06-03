
package com.smartshop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartshop.core.catalog.ProductOption;
import com.smartshop.core.catalog.QProductOption;
import com.smartshop.core.catalog.service.ProductOptionService;
import com.smartshop.repository.ProductOptionRepository;

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
	public boolean isExist(String optionCode) {
		QProductOption qProductOption = QProductOption.productOption;
		return productOptionRepository.exists(qProductOption.code.eq(optionCode));
	}

}
