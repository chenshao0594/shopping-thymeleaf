package com.smartshop.service;

import java.util.List;
import java.util.Map;

import com.smartshop.core.catalog.Product;
import com.smartshop.shop.model.ProductOptionDTO;
import com.smartshop.shop.model.ProductOptionPricing;

/**
 * Service Interface for managing Product.
 */
public interface ProductService extends AbstractDomainService<Product, Long> {

	void generateAdditionalSKUsByBatch(Long productId, List<Long> optionIds);

	List<Map<String, Long>> countProductsByCategories();

	List<ProductOptionPricing> buildSKUsPricing(Product product);

	List<ProductOptionDTO> buildProductOptionsDTO(Product product);

}