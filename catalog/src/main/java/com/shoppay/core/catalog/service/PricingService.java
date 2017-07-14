package com.shoppay.core.catalog.service;

import java.math.BigDecimal;

import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.core.catalog.Product;
import com.shoppay.core.catalog.pricing.FinalPrice;

public interface PricingService {
	/**
	 * Calculates the FinalPrice of a Product taking into account all defined
	 * prices and possible rebates
	 *
	 * @param product
	 * @return
	 * @throws BusinessException
	 */
	FinalPrice calculateProductPrice(Product product) throws BusinessException;

	/**
	 * Method to be used to print a displayable formated amount to the end user
	 *
	 * @param amount
	 * @param store
	 * @return
	 * @throws BusinessException
	 */
	String getDisplayAmount(BigDecimal amount, MerchantStore store) throws BusinessException;

	/**
	 * String format of the money amount without currency symbol
	 *
	 * @param amount
	 * @param store
	 * @return
	 * @throws BusinessException
	 */
	String getStringAmount(BigDecimal amount, MerchantStore store) throws BusinessException;
}