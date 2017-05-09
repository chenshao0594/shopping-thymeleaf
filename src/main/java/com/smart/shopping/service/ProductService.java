package com.smart.shopping.service;

import com.smart.shopping.core.catalog.Product;

/**
 * Service Interface for managing Product.
 */
public interface ProductService extends AbstractDomainService<Product, Long> {

	void generateAdditionalSKUsByBatch(Long productId);
}