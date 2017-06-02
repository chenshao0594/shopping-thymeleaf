package com.smartshop.service;

import java.math.BigDecimal;

import com.smartshop.core.catalog.SKU;

/**
 * Service Interface for managing Product.
 */
public interface SKUService extends AbstractDomainService<SKU, Long> {

	BigDecimal findRetailPriceById(Long id);

	SKU findDefaultSKU(Long productId);

}