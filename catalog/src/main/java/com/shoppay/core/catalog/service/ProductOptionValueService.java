package com.shoppay.core.catalog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shoppay.common.service.AbstractDomainService;
import com.shoppay.core.catalog.ProductOption;
import com.shoppay.core.catalog.ProductOptionValue;

/**
 * Service Interface for managing ProductOptionValue.
 */
public interface ProductOptionValueService extends AbstractDomainService<ProductOptionValue, Long> {
	public Page<ProductOptionValue> queryOptionValuesByOption(ProductOption productOption, Pageable pageable);

}