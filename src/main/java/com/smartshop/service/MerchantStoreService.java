package com.smartshop.service;

import com.smartshop.domain.MerchantStore;
import com.smartshop.exception.BusinessException;

/**
 * Service Interface for managing MerchantStore.
 */
public interface MerchantStoreService extends AbstractDomainService<MerchantStore, Long> {
	MerchantStore getByCode(String code) throws BusinessException;

}