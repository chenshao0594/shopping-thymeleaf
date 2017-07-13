package com.smartshop.core.catalog.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartshop.catalog.repository.CategoryRepository;
import com.smartshop.core.catalog.Category;
import com.smartshop.core.catalog.QCategory;
import com.smartshop.core.catalog.service.CategoryService;
import com.smartshop.repository.search.CategorySearchRepository;
import com.smartshop.service.impl.AbstractDomainServiceImpl;

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
		QCategory q = QCategory.category;
		// this.categoryRepository.findOne(q.sear)
		return null;
	}

}