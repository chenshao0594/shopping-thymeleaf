package com.smartshop.core.catalog.service;

import java.math.BigDecimal;

import com.smartshop.core.catalog.Product;
import com.smartshop.core.catalog.pricing.FinalPrice;
import com.smartshop.core.customer.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.exception.BusinessException;

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
	 * Calculates the FinalPrice of a Product taking into account all defined
	 * prices and possible rebates. It also applies other calculation based on
	 * the customer
	 *
	 * @param product
	 * @param customer
	 * @return
	 * @throws BusinessException
	 */
	FinalPrice calculateProductPrice(Product product, Customer customer) throws BusinessException;

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