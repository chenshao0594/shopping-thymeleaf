package com.shoppay.web.constants;

public interface ShoppingControllerConstants {
	public static final String REDIRECT = "redirect:";
	public static final String ORDER_KEY = "order-session";
	public static final String HOME="home";

	interface ShoppingCart {
		final static String shoppingCart = "maincart";
		final static String detail = "cart/detail";
	}

	interface Category {
		final static String category = "category";
	}

	interface Catalog {
		final static String catalogue = "catalog/catalogue";
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
		final static String customerOrders = "customer/orders";
		final static String Billing = "customerAddress";
		final static String EditAddress = "editCustomerAddress";
		final static String RegistrationPage = "customer/registration.html";
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
	}

	interface Search {
		final static String search = "search";
	}

	interface Error {
		final static String accessDenied = "accessDenied";
		final static String error = "error";
	}

}
