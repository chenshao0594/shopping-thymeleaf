package com.smart.shopping.config;

/**
 * Application constants.
 */
public final class AppConstants {

	// Regex for acceptable logins
	public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";

	public static final String SYSTEM_ACCOUNT = "system";
	public static final String ANONYMOUS_USER = "anonymoususer";

	public static final String ADMIN_PREFIX = "/admin";

	private AppConstants() {
	}
}
