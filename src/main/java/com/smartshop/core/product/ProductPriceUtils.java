package com.smartshop.core.product;

import java.math.BigDecimal;
import java.text.NumberFormat;

import com.smartshop.constants.AppConstants;
import com.smartshop.domain.MerchantStore;

public class ProductPriceUtils {
	private final static String DECIMALCOUNT = "2";
	private final static String DECIMALPOINT = ".";
	private final static String THOUSANDPOINT = ",";

	public static String getAdminFormatedAmount(MerchantStore store, BigDecimal amount) throws Exception {

		if (amount == null) {
			return "";
		}
		NumberFormat nf = null;
		nf = NumberFormat.getInstance(AppConstants.DEFAULT_LOCALE);
		nf.setMaximumFractionDigits(Integer.parseInt(DECIMALCOUNT));
		nf.setMinimumFractionDigits(Integer.parseInt(DECIMALCOUNT));
		return nf.format(amount);
	}

}
