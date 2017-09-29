package com.shoppay.common.service;

import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.exception.BusinessException;

/**
 * Service Interface for managing MerchantStore.
 */
public interface MerchantStoreService extends AbstractDomainService<MerchantStore, Long> {
	MerchantStore getByCode(String code) throws BusinessException;
	MerchantStore getByAddress(String host);

}