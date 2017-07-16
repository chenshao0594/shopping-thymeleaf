package com.shoppay.common.utils;

import java.math.BigDecimal;

public class MoneyFormatUtils {
	public static String formatPrice(BigDecimal price) {
		if (price == null) {
			return "Not Available";
		}
		return "$" + price.toString();
	}
}
