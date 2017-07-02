package com.smartshop.core.facade;

import java.math.BigDecimal;

import com.smartshop.core.catalog.SKU;

public interface PricingFacade {

	public BigDecimal calculate(SKU sku, int quantity);

}
