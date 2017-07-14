package com.shoppay.core.catalog.service;

import java.util.List;

import com.shoppay.common.service.AbstractDomainService;
import com.shoppay.core.catalog.Product;
import com.shoppay.core.catalog.ProductRelationship;

/**
 * Service Interface for managing Product.
 */
public interface ProductRelationshipService extends AbstractDomainService<ProductRelationship, Long> {

	List<Product> getRelations(final Product product);

	void deletes(Product product, List<Long> relationIds);

}