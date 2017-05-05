package com.smart.shopping.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.shopping.domain.MerchantStore;
import com.smart.shopping.repository.MerchantStoreRepository;
import com.smart.shopping.service.MerchantStoreService;

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

}
