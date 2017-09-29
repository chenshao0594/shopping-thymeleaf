package com.shoppay.core.catalog.service;

import java.util.List;

import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.service.AbstractDomainService;
import com.shoppay.core.catalog.Category;

/**
 * Service Interface for managing Category.
 */
public interface CategoryService extends AbstractDomainService<Category, Long> {

	Category findBySearchURL(final String searchURL);
	
	
	List<Category> findAllByStore(final MerchantStore store);

}