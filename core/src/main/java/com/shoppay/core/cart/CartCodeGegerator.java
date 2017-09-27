package com.shoppay.core.cart;

import java.util.UUID;

public final class CartCodeGegerator {
	
	public static String generateCode() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
