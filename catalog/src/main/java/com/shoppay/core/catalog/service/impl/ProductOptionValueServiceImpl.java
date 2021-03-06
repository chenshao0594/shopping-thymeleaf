package com.shoppay.core.catalog.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppay.common.service.impl.AbstractDomainServiceImpl;
import com.shoppay.core.catalog.ProductOption;
import com.shoppay.core.catalog.ProductOptionValue;
import com.shoppay.core.catalog.repository.ProductOptionValueRepository;
import com.shoppay.core.catalog.service.ProductOptionValueService;

/**
 * Service Implementation for managing ProductOptionValue.
 */
@Service
@Transactional
public class ProductOptionValueServiceImpl extends AbstractDomainServiceImpl<ProductOptionValue, Long>
		implements ProductOptionValueService {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductOptionValueServiceImpl.class);
	private final ProductOptionValueRepository productOptionValueRepository;

	public ProductOptionValueServiceImpl(ProductOptionValueRepository productOptionValueRepository) {
		super(productOptionValueRepository);
		this.productOptionValueRepository = productOptionValueRepository;
	}

	@Override
	public Page<ProductOptionValue> queryOptionValuesByOption(ProductOption productOption, Pageable pageable) {
		// QProductOptionValue qProductOptionValue =
		// QProductOptionValue.productOptionValue;
		// return
		// this.productOptionValueRepository.findAll(qProductOptionValue.productOption.eq(productOption),
		// pageable);

		return null;
	}

}
