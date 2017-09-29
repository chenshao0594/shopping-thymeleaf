package com.shoppay.common.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.domain.QMerchantStore;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.common.repository.MerchantStoreRepository;
import com.shoppay.common.service.MerchantStoreService;

/**
 * Service Implementation for managing MerchantStore.
 */
@Service
@Transactional
public class MerchantStoreServiceImpl extends AbstractDomainServiceImpl<MerchantStore, Long>
		implements MerchantStoreService {

	private final Logger LOGGER = LoggerFactory.getLogger(MerchantStoreServiceImpl.class);
	private final MerchantStoreRepository merchantStoreRepository;

	public MerchantStoreServiceImpl(MerchantStoreRepository merchantStoreRepository) {
		super(merchantStoreRepository, null);
		this.merchantStoreRepository = merchantStoreRepository;
	}

	@Override
	public MerchantStore getByCode(String code) throws BusinessException {
		QMerchantStore qMerchantStore = QMerchantStore.merchantStore;
		return this.merchantStoreRepository.findOne(qMerchantStore.code.eq(code));
	}

	@Override
	public MerchantStore getByAddress(String host) {
		return this.merchantStoreRepository.findByAddress(host);
	}

}
