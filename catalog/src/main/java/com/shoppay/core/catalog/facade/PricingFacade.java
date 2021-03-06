package com.shoppay.core.catalog.facade;

import java.math.BigDecimal;

import com.shoppay.core.catalog.SKU;

public interface PricingFacade {

	public BigDecimal calculate(SKU sku, int quantity);

}
