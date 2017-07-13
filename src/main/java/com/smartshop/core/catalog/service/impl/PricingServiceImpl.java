package com.smartshop.core.catalog.service.impl;

import java.math.BigDecimal;
import java.text.NumberFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.smartshop.constants.AppConstants;
import com.smartshop.core.catalog.Product;
import com.smartshop.core.catalog.pricing.FinalPrice;
import com.smartshop.core.catalog.service.PricingService;
import com.smartshop.core.customer.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.exception.BusinessException;

@Service("pricingService")
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
		NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(store.getLocale());
		currencyInstance.setCurrency(store.getCurrency());
		return currencyInstance.format(amount.doubleValue());
	}

	@Override
	public String getStringAmount(BigDecimal amount, MerchantStore store) throws BusinessException {
		if (amount == null) {
			return "";
		}
		NumberFormat nf = null;
		nf = NumberFormat.getInstance(AppConstants.DEFAULT_LOCALE);

		nf.setMaximumFractionDigits(Integer.parseInt(Character.toString(AppConstants.DECIMALCOUNT)));
		nf.setMinimumFractionDigits(Integer.parseInt(Character.toString(AppConstants.DECIMALCOUNT)));

		return nf.format(amount);
	}

}
