package com.smartshop.service.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartshop.core.catalog.QSKU;
import com.smartshop.core.catalog.SKU;
import com.smartshop.repository.SKURepository;
import com.smartshop.repository.search.SKUSearchRepository;
import com.smartshop.service.SKUService;

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

	@Override
	public BigDecimal findRetailPriceById(Long id) {
		LOGGER.info("sku id {}", id);
		return this.skuRepository.findRetailPriceById(id);
	}

	@Override
	public SKU findDefaultSKU(Long productId) {
		QSKU qsku = QSKU.sKU;
		return this.skuRepository.findOne(qsku.product.id.eq(productId));
	}

}
