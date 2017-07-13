package com.smartshop.core.catalog.service;

import java.util.List;
import java.util.Map;

import com.smartshop.core.catalog.Category;
import com.smartshop.core.catalog.Product;
import com.smartshop.service.AbstractDomainService;
import com.smartshop.shop.model.ProductOptionDTO;
import com.smartshop.shop.model.ProductOptionPricing;

/**
 * Service Interface for managing Product.
 */
public interface ProductService extends AbstractDomainService<Product, Long> {

	Iterable<Product> findOthers(Product product);

	void generateAdditionalSKUsByBatch(Long productId, List<Long> optionIds);

	List<Map<String, Long>> countProductsByCategories();

	List<ProductOptionPricing> buildSKUsPricing(Product product);

	List<ProductOptionDTO> buildProductOptionsDTO(Product product);

	String findNameById(Long productId);

	Iterable<Product> findRelations(Product product);

	void addRelations(long id, List<Long> ids);

	Product findBySearchURL(String searchURL);

	long countByCategory(Category category);

}