
package com.shoppay.core.catalog.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppay.common.service.impl.AbstractDomainServiceImpl;
import com.shoppay.core.catalog.ProductOption;
import com.shoppay.core.catalog.QProductOption;
import com.shoppay.core.catalog.repository.ProductOptionRepository;
import com.shoppay.core.catalog.service.ProductOptionService;

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
