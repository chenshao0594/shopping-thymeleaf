package com.smart.shopping.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.shopping.core.catalog.Category;
import com.smart.shopping.core.catalog.service.CategoryService;
import com.smart.shopping.service.AbstractDomainService;



@Controller
@RequestMapping("/"+CategoryController.SECTION_KEY)
public class CategoryController  extends AbstractDomainController<Category, Long> {
	
	private final Logger log = LoggerFactory.getLogger(CategoryController.class);
	public static final String SECTION_KEY = "categories";
	private static final String ENTITY_NAME = "category";

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super(categoryService);
		this.categoryService = categoryService;
	}

	@Override
	protected String getSectionKey() {
		return this.SECTION_KEY;
	}

	@Override
	protected String getEntityName() {
		return this.ENTITY_NAME;
	}

}
