package com.smart.shopping.service.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.shopping.core.catalog.Product;
import com.smart.shopping.core.catalog.service.CategoryService;
import com.smart.shopping.core.catalog.service.ProductOptionService;
import com.smart.shopping.core.catalog.service.ProductOptionValueService;
import com.smart.shopping.repository.ProductRepository;
import com.smart.shopping.repository.search.ProductSearchRepository;
import com.smart.shopping.service.ProductService;

/**
 * Service Implementation for managing Product.
 */
@Service
@Transactional
public class ProductServiceImpl extends AbstractDomainServiceImpl<Product, Long> implements ProductService {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	private final ProductRepository productRepository;
	private final ProductSearchRepository productSearchRepository;
	@Inject
	CategoryService categoryService;
	@Inject
	ProductOptionService productOptionService;

	@Inject
	ProductOptionValueService productOptionValueService;

	public ProductServiceImpl(ProductRepository productRepository, ProductSearchRepository productSearchRepository) {
		super(productRepository, productSearchRepository);
		this.productRepository = productRepository;
		this.productSearchRepository = productSearchRepository;
	}

}