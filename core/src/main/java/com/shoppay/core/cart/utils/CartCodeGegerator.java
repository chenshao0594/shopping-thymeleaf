package com.shoppay.core.cart.utils;

import java.util.UUID;

public final class CartCodeGegerator {
	
	public static String generateCode() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
