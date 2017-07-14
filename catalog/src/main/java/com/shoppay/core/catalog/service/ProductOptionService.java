package com.shoppay.core.catalog.service;

import com.shoppay.common.service.AbstractDomainService;
import com.shoppay.core.catalog.ProductOption;

/**
 * Service Interface for managing ProductOption.
 */
public interface ProductOptionService extends AbstractDomainService<ProductOption, Long> {
	boolean isExist(String optionCode);

}