package com.smartshop.common;

public interface ShoppingControllerConstants {
	final static String REDIRECT = "redirect:";

	interface ShoppingCart {
		final static String shoppingCart = "shop/maincart";
	}

	interface Category {
		final static String category = "shop/category";
	}

	interface Catalogue {
		final static String catalogue = "shop/catalogue/catalogue";
	}

	interface Product {
		final static String product = "shop/product";
	}

	interface Items {
		final static String items_manufacturer = "shop/items/manufacturer";
	}

	interface Customer {
		final static String customer = "shop/customer";
		final static String customerLogon = "shop/customerLogon";
		final static String review = "shop/review";
		final static String register = "shop/register";
		final static String changePassword = "shop/customerPassword";
		final static String customerOrders = "shop/customer/Orders";
		final static String customerOrder = "shop/customerOrder";
		final static String Billing = "shop/customerAddress";
		final static String EditAddress = "shop/editCustomerAddress";
		final static String RegistrationPage = "shop/customer/registration.html";
	}

	interface Content {
		final static String content = "shop/content";
		final static String contactus = "shop/contactus";
	}

	interface Pages {
		final static String notFound = "shop/404";
		final static String timeout = "shop/timeout";
	}

	interface Merchant {
		final static String contactUs = "shop/contactus";
	}

	interface Checkout {
		final static String start = "shop/checkout/checkout_start";
		final static String address = "shop/checkout/checkout_address";
	}

	interface Payment {
		final static String methods = "shop/payment";
	}

	interface Search {
		final static String search = "shop/search";
	}

	interface Error {
		final static String accessDenied = "shop/accessDenied";
		final static String error = "shop/error";
	}

}
