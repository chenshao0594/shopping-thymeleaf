package com.smartshop.core.catalog.service;

import java.math.BigDecimal;

import com.smartshop.core.catalog.SKU;
import com.smartshop.service.AbstractDomainService;

/**
 * Service Interface for managing Product.
 */
public interface SKUService extends AbstractDomainService<SKU, Long> {

	BigDecimal findRetailPriceById(Long id);

	SKU findDefaultSKU(Long productId);

}