package com.smartshop.constants;

/**
 * Application constants.
 */
public final class AppConstants {
	public final static String SLASH = "/";
	public final static String BLANK = "";

	public final static String BASE_PATH = "/static/images/";
	// Regex for acceptable logins
	public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";

	public static final String SYSTEM_ACCOUNT = "system";
	public static final String ANONYMOUS_USER = "anonymoususer";
	public static final String ADMIN_PREFIX = "/admin";
	public static final String CUSTOMER_PREFIX = "/customer";
	public static final String MERCHANT_STORE = "merchantStore";
	public static final String LANG = "en";
	// customer
	public static final String CUSTOMER = "customer";
	public final static String ANONYMOUS_CUSTOMER = "ANONYMOUS_CUSTOMER";
	public final static String PERMISSION_CUSTOMER_AUTHENTICATED = "AUTH_CUSTOMER";

	// shopping cart
	public final static String SHOPPING_CART = "SHOPPING-CART";
	public final static String STORE_CODE = "STRORE-CODE";
	public final static String COOKIE_NAME_CART = "cart";

	public final static String CART_TOTAL = "CART_TOTAL";
	public final static String CARTITEMS_TOTAL = "CARTITEMS_TOTAL";

	public final static String CONTINUE_SHOPPING = "CONTINUE_SHOPPING";
	// temp
	public final static String LINK_CODE = "LINK_CODE";

	private AppConstants() {
	}
}
