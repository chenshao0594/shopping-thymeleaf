package com.shoppay.web.constants;

public interface ShoppingControllerConstants {
	public static final String REDIRECT = "redirect:";
	public static final String ORDER_KEY = "order-session";
	public static final String HOME="home";

	interface ShoppingCart {
		final static String shoppingCart = "maincart";
		final static String detail = "cart/detail";
	}

	interface Catalog {
		final static String catalogue = "catalog/catalogue";
		final static String search = "catalog/search";
	}

	interface Product {
		final static String product = "product";
		final static String detail = "product/detail";
	}

	interface Items {
		final static String items_manufacturer = "items/manufacturer";
	}

	interface Customer {
		final static String customerLogon = "customerLogon";
		final static String review = "review";
		final static String register = "register";
		final static String changePassword = "customerPassword";
		final static String Billing = "customerAddress";
		final static String EditAddress = "editCustomerAddress";
		final static String resetPasswordInit="password/reset_init";
		final static String resetPasswordSuccess="password/reset_success";
		final static String changePasswordInit="password/change_init";
		final static String changePasswordSuccess="password/change_success";

	}

	interface Content {
		final static String content = "content";
		final static String contactus = "contactus";
	}

	interface Pages {
		final static String notFound = "404";
		final static String timeout = "timeout";
	}

	interface Merchant {
		final static String contactUs = "contactus";
	}

	interface Checkout {
		final static String start = "checkout/checkout_start";
		final static String address = "checkout/checkout_address";
	}

	interface Payment {
		final static String methods = "payment/payments";
		final static String success = "payment/success"; 
		final static String cancel = "payment/cancel"; 
	}

	interface Search {
		final static String search = "search";
	}

	interface Order {
		final static String list = "order/list";
		final static String detail = "order/detail";
		
	}
	interface Error {
		final static String accessDenied = "accessDenied";
		final static String error = "error";
	}

}
