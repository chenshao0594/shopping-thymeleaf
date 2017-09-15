package com.shoppay.core.catalog.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;

import com.shoppay.common.constants.ApplicationConstants;
import com.shoppay.common.domain.MerchantStore;

public class ProductPriceUtils {
	private final static String DECIMALCOUNT = "2";
	private final static String DECIMALPOINT = ".";
	private final static String THOUSANDPOINT = ",";

	public static String getAdminFormatedAmount(MerchantStore store, BigDecimal amount) throws Exception {

		if (amount == null) {
			return "";
		}
		NumberFormat nf = null;
		nf = NumberFormat.getInstance(ApplicationConstants.DEFAULT_LOCALE);
		nf.setMaximumFractionDigits(Integer.parseInt(DECIMALCOUNT));
		nf.setMinimumFractionDigits(Integer.parseInt(DECIMALCOUNT));
		return nf.format(amount);
	}

}
