package com.shoppay.core.catalog.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.service.impl.AbstractDomainServiceImpl;
import com.shoppay.core.catalog.Category;
import com.shoppay.core.catalog.repository.CategoryRepository;
import com.shoppay.core.catalog.repository.search.CategorySearchRepository;
import com.shoppay.core.catalog.service.CategoryService;

/**
 * Service Implementation for managing Category.
 */
@Service
@Transactional
public class CategoryServiceImpl extends AbstractDomainServiceImpl<Category, Long> implements CategoryService {

	private final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);
	private final CategoryRepository categoryRepository;
	private final CategorySearchRepository categorySearchRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository,
			CategorySearchRepository categorySearchRepository) {
		super(categoryRepository, categorySearchRepository);
		this.categoryRepository = categoryRepository;
		this.categorySearchRepository = categorySearchRepository;
	}

	@Override
	public Category findBySearchURL(String searchURL) {
		return null;
	}

	@Override
	public List<Category> findCategoryTreeByStore(MerchantStore store) {
		List<Category>  lists = this.categoryRepository.findCategoryTreeByStore();
		return lists;
	}

}