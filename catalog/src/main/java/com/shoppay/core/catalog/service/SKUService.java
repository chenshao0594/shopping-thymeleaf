package com.shoppay.core.catalog.service;

import java.math.BigDecimal;

import com.shoppay.common.service.AbstractDomainService;
import com.shoppay.core.catalog.SKU;

/**
 * Service Interface for managing Product.
 */
public interface SKUService extends AbstractDomainService<SKU, Long> {

	BigDecimal findRetailPriceById(Long id);

	SKU findDefaultSKU(Long productId);

}