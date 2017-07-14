package com.shoppay.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shoppay.constants.AppConstants;
import com.shoppay.core.catalog.Category;
import com.shoppay.core.catalog.service.CategoryService;

@Controller
@RequestMapping(AppConstants.ADMIN_PREFIX + "/" + CategoryController.SECTION_KEY)
public class CategoryController extends AbstractDomainController<Category, Long> {

	private final Logger log = LoggerFactory.getLogger(CategoryController.class);
	public static final String SECTION_KEY = "categories";
	private static final Class ENTITY_CLASS = Category.class;

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super(categoryService);
		this.categoryService = categoryService;
	}

	@Override
	protected void preNew(ModelAndView model) {
		List<Category> categories = this.categoryService.list();
		model.addObject("categories", categories);
	}

	@Override
	protected String getSectionKey() {
		return this.SECTION_KEY;
	}

	@Override
	protected Class getEntityClass() {
		return this.ENTITY_CLASS;
	}

}
