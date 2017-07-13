package com.smartshop.core.catalog.service;

import java.util.List;

import com.smartshop.core.catalog.Product;
import com.smartshop.core.catalog.ProductRelationship;
import com.smartshop.service.AbstractDomainService;

/**
 * Service Interface for managing Product.
 */
public interface ProductRelationshipService extends AbstractDomainService<ProductRelationship, Long> {

	List<Product> getRelations(final Product product);

	void deletes(Product product, List<Long> relationIds);

}