package com.shoppay.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shoppay.core.catalog.Category;
import com.shoppay.core.catalog.service.CategoryService;

@Controller
@RequestMapping( "/category")
public class CategoryController extends AbstractDomainController<Category, Long> {

	private final Logger log = LoggerFactory.getLogger(CategoryController.class);

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super(categoryService, Category.class);
		this.categoryService = categoryService;
	}

	@Override
	protected void preNew(ModelAndView model) {
		List<Category> categories = this.categoryService.list();
		model.addObject("categories", categories);
	}

}
