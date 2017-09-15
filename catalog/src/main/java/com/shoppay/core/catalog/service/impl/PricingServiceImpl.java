package com.shoppay.core.catalog.service.impl;

import java.math.BigDecimal;
import java.text.NumberFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shoppay.common.constants.ApplicationConstants;
import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.core.catalog.Product;
import com.shoppay.core.catalog.pricing.FinalPrice;
import com.shoppay.core.catalog.service.PricingService;

@Service("pricingService")
public class PricingServiceImpl implements PricingService {
	private final Logger LOGGER = LoggerFactory.getLogger(PricingServiceImpl.class);

	@Override
	public FinalPrice calculateProductPrice(Product product) throws BusinessException {
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
		nf = NumberFormat.getInstance(ApplicationConstants.DEFAULT_LOCALE);

		nf.setMaximumFractionDigits(Integer.parseInt(Character.toString(ApplicationConstants.DECIMALCOUNT)));
		nf.setMinimumFractionDigits(Integer.parseInt(Character.toString(ApplicationConstants.DECIMALCOUNT)));

		return nf.format(amount);
	}

}
