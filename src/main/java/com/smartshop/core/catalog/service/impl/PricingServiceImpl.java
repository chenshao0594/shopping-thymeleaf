package com.smartshop.core.catalog.service.impl;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartshop.core.catalog.Product;
import com.smartshop.core.catalog.price.FinalPrice;
import com.smartshop.core.catalog.service.PricingService;
import com.smartshop.domain.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.exception.BusinessException;

public class PricingServiceImpl implements PricingService {
	private final Logger LOGGER = LoggerFactory.getLogger(PricingServiceImpl.class);

	@Override
	public FinalPrice calculateProductPrice(Product product) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FinalPrice calculateProductPrice(Product product, Customer customer) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDisplayAmount(BigDecimal amount, MerchantStore store) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDisplayAmount(BigDecimal amount, Locale locale, Currency currency, MerchantStore store)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStringAmount(BigDecimal amount, MerchantStore store) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
