package com.smart.shopping.service;

import java.util.List;

import com.smart.shopping.core.catalog.Product;

/**
 * Service Interface for managing Product.
 */
public interface ProductService extends AbstractDomainService<Product, Long> {

	void generateAdditionalSKUsByBatch(Long productId, List<Long> optionIds);
}