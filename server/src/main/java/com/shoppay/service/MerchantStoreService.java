package com.shoppay.service;

import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.common.service.AbstractDomainService;

/**
 * Service Interface for managing MerchantStore.
 */
public interface MerchantStoreService extends AbstractDomainService<MerchantStore, Long> {
	MerchantStore getByCode(String code) throws BusinessException;

}