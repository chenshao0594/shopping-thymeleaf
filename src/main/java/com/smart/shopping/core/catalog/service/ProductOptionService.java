package com.smart.shopping.core.catalog.service;

import com.smart.shopping.core.catalog.ProductOption;
import com.smart.shopping.domain.MerchantStore;
import com.smart.shopping.service.AbstractDomainService;

/**
 * Service Interface for managing ProductOption.
 */
public interface ProductOptionService extends AbstractDomainService<ProductOption, Long> {
	ProductOption getByCode(MerchantStore store, String optionCode);

}