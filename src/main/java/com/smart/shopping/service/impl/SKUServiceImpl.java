package com.smart.shopping.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.shopping.core.catalog.SKU;
import com.smart.shopping.repository.SKURepository;
import com.smart.shopping.repository.search.SKUSearchRepository;
import com.smart.shopping.service.SKUService;

/**
 * Service Implementation for managing ProductOptionValue.
 */
@Service
@Transactional
public class SKUServiceImpl extends AbstractDomainServiceImpl<SKU, Long> implements SKUService {

	private final Logger LOGGER = LoggerFactory.getLogger(SKUServiceImpl.class);
	private final SKURepository skuRepository;
	private final SKUSearchRepository skuSearchRepository;

	public SKUServiceImpl(SKURepository skuRepository, SKUSearchRepository skuSearchRepository) {
		super(skuRepository, skuSearchRepository);
		this.skuRepository = skuRepository;
		this.skuSearchRepository = skuSearchRepository;
	}
}
