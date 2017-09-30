package com.shoppay.core.catalog.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shoppay.common.service.AbstractDomainService;
import com.shoppay.core.catalog.Category;
import com.shoppay.core.catalog.Product;
import com.shoppay.core.catalog.model.ProductOptionDTO;
import com.shoppay.core.catalog.model.ProductOptionPricing;

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
	
	Page<Product> findAllByCategory(Category category,Pageable pageable);
	
	Page<Product> searchByName(String name, Pageable pageable);

}