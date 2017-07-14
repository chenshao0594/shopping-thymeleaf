package com.shoppay.core.facade;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.shoppay.core.catalog.SKU;

@Service
public class PricingFacadeImpl implements PricingFacade {

	@Override
	public BigDecimal calculate(SKU sku, int quantity) {
		BigDecimal total = sku.getRetailPrice();
		return total.multiply(new BigDecimal(quantity));
	}

}
