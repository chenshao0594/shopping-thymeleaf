package com.shoppay.core.catalog.service.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.shoppay.common.service.impl.AbstractDomainServiceImpl;
import com.shoppay.core.catalog.QSKU;
import com.shoppay.core.catalog.SKU;
import com.shoppay.core.catalog.repository.SKURepository;
import com.shoppay.core.catalog.service.SKUService;

/**
 * Service Implementation for managing ProductOptionValue.
 */
@Service
@Transactional
public class SKUServiceImpl extends AbstractDomainServiceImpl<SKU, Long> implements SKUService {

	private final Logger LOGGER = LoggerFactory.getLogger(SKUServiceImpl.class);
	private final SKURepository skuRepository;

	public SKUServiceImpl(SKURepository skuRepository) {
		super(skuRepository);
		this.skuRepository = skuRepository;
	}

	@Override
	public BigDecimal findRetailPriceById(Long id) {
		LOGGER.info("sku id {}", id);
		return this.skuRepository.findRetailPriceById(id);
	}

	@Override
	public SKU findDefaultSKU(Long productId) {
		QSKU qsku = QSKU.sKU;
		BooleanExpression idExp = qsku.product.id.eq(productId);
		BooleanExpression defaultExp = qsku.isDefault.eq(true);
		return this.skuRepository.findOne(idExp.and(defaultExp));
	}

}
