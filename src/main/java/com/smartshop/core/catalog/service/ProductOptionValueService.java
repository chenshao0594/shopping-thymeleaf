package com.smartshop.core.catalog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.smartshop.core.catalog.ProductOption;
import com.smartshop.core.catalog.ProductOptionValue;
import com.smartshop.service.AbstractDomainService;

/**
 * Service Interface for managing ProductOptionValue.
 */
public interface ProductOptionValueService extends AbstractDomainService<ProductOptionValue, Long> {
	public Page<ProductOptionValue> queryOptionValuesByOption(ProductOption productOption, Pageable pageable);

}