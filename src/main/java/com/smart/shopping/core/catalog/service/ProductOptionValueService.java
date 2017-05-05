package com.smart.shopping.core.catalog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.smart.shopping.core.catalog.ProductOption;
import com.smart.shopping.core.catalog.ProductOptionValue;
import com.smart.shopping.service.AbstractDomainService;

/**
 * Service Interface for managing ProductOptionValue.
 */
public interface ProductOptionValueService extends AbstractDomainService<ProductOptionValue, Long> {
	public Page<ProductOptionValue> queryOptionValuesByOption(ProductOption productOption, Pageable pageable);

}