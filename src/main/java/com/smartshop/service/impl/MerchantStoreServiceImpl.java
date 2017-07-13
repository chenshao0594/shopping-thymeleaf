package com.smartshop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartshop.app.repository.MerchantStoreRepository;
import com.smartshop.domain.MerchantStore;
import com.smartshop.domain.QMerchantStore;
import com.smartshop.exception.BusinessException;
import com.smartshop.service.MerchantStoreService;

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

}
