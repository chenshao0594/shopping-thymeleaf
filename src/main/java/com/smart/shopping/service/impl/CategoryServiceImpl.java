package com.smart.shopping.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.shopping.core.catalog.Category;
import com.smart.shopping.core.catalog.service.CategoryService;
import com.smart.shopping.repository.CategoryRepository;
import com.smart.shopping.repository.search.CategorySearchRepository;

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

}