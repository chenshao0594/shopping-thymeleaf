package com.smart.shopping.service;

import java.util.List;
import java.util.Map;

import com.smart.shop.model.ProductOptionDTO;
import com.smart.shop.model.ProductOptionPricing;
import com.smart.shopping.core.catalog.Product;

/**
 * Service Interface for managing Product.
 */
public interface ProductService extends AbstractDomainService<Product, Long> {

	void generateAdditionalSKUsByBatch(Long productId, List<Long> optionIds);

	List<Map<String, Long>> countProductsByCategories();

	List<ProductOptionPricing> buildSKUsPricing(Product product);

	List<ProductOptionDTO> buildProductOptionsDTO(Product product);

}