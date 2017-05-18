package com.smartshop.core.catalog.service;

import com.smartshop.core.catalog.ProductOption;
import com.smartshop.domain.MerchantStore;
import com.smartshop.service.AbstractDomainService;

/**
 * Service Interface for managing ProductOption.
 */
public interface ProductOptionService extends AbstractDomainService<ProductOption, Long> {
	ProductOption getByCode(MerchantStore store, String optionCode);

}