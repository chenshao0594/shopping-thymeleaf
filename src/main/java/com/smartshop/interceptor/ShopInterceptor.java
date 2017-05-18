package com.smartshop.interceptor;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.smartshop.common.utils.GeoLocationUtils;
import com.smartshop.config.AppConstants;
import com.smartshop.core.common.Address;
import com.smartshop.domain.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.security.AuthoritiesConstants;
import com.smartshop.service.CustomerService;
import com.smartshop.service.MerchantStoreService;
import com.smartshop.shop.model.customer.AnonymousCustomer;

public class ShopInterceptor extends HandlerInterceptorAdapter {
	private final Logger LOGGER = LoggerFactory.getLogger(ShopInterceptor.class);
	private final static String STORE_REQUEST_PARAMETER = "store";

	@Inject
	private MerchantStoreService merchantService;

	@Inject
	private CustomerService customerService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		try {
			/** merchant store **/
			MerchantStore store = (MerchantStore) request.getSession().getAttribute(AppConstants.MERCHANT_STORE);

			String storeCode = request.getParameter(STORE_REQUEST_PARAMETER);

			// remove link set from controllers for declaring active - inactive
			// links
			request.removeAttribute(AppConstants.LINK_CODE);

			if (!StringUtils.isBlank(storeCode)) {
				if (store != null) {
					if (!store.getCode().equals(storeCode)) {
						store = setMerchantStoreInSession(request, storeCode);
					}
				} else { // when url sm-shop/shop is being loaded for first time
							// store is null
					store = setMerchantStoreInSession(request, storeCode);
				}
			}

			if (store == null) {
				store = setMerchantStoreInSession(request, MerchantStore.DEFAULT_STORE);
			}
			request.setAttribute(AppConstants.MERCHANT_STORE, store);
			/** customer **/
			Customer customer = (Customer) request.getSession().getAttribute(AppConstants.CUSTOMER);
			if (customer != null) {
				if (customer.getMerchantStore().getId().intValue() != store.getId().intValue()) {
					request.getSession().removeAttribute(AppConstants.CUSTOMER);
				}
				if (!customer.isAnonymous()) {
					if (!request.isUserInRole(AuthoritiesConstants.CUSTOMER)) {
						request.removeAttribute(AppConstants.CUSTOMER);
					}
				}
				request.setAttribute(AppConstants.CUSTOMER, customer);
			}

			if (customer == null) {

				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if (auth != null && request.isUserInRole("AUTH_CUSTOMER")) {
					customer = customerService.findCustomerByEmailAddress(auth.getName());
					if (customer != null) {
						request.setAttribute(AppConstants.CUSTOMER, customer);
					}
				}

			}

			AnonymousCustomer anonymousCustomer = (AnonymousCustomer) request.getSession()
					.getAttribute(AppConstants.ANONYMOUS_CUSTOMER);
			if (anonymousCustomer == null) {

				Address address = null;
				try {

					String ipAddress = GeoLocationUtils.getClientIpAddress(request);
					Address geoAddress = customerService.getCustomerAddress(store, ipAddress);
					if (geoAddress != null) {
						address = new Address();
						address.setCountry(geoAddress.getCountry());
						address.setCity(geoAddress.getCity());
						address.setZone(geoAddress.getZone());
					}
				} catch (Exception ce) {
					LOGGER.error("Cannot get geo ip component ", ce);
				}

				if (address == null) {
					address = new Address();
					address.setCountry(store.getCountry().getIsoCode());
					if (store.getZone() != null) {
						address.setZone(store.getZone().getCode());
					} else {
						address.setStateProvince(store.getStateProvince());
					}
				}
				anonymousCustomer = new AnonymousCustomer();
				anonymousCustomer.setBilling(address);
				request.getSession().setAttribute(AppConstants.ANONYMOUS_CUSTOMER, anonymousCustomer);
			} else {
				request.setAttribute(AppConstants.ANONYMOUS_CUSTOMER, anonymousCustomer);
			}

			/** language & locale **/
			/*
			 * Language language = languageUtils.getRequestLanguage( request,
			 * response); request.setAttribute(AppConstants. LANGUAGE,
			 * language);
			 *
			 * Locale locale = languageService.toLocale(language);
			 */
			// Locale locale = LocaleContextHolder.getLocale();

			// will to enhance
			LocaleContextHolder.setLocale(Locale.ENGLISH);

			/**
			 * Breadcrumbs will move to common
			 *
			 **/
			// setBreadcrumb(request, Locale.ENGLISH);

			/**
			 * Get global objects Themes are built on a similar way displaying
			 * Header, Body and Footer Header and Footer are displayed on each
			 * page Some themes also contain side bars which may include similar
			 * emements
			 *
			 * Elements from Header : - CMS links - Customer - Mini shopping
			 * cart - Store name / logo - Top categories - Search
			 *
			 * Elements from Footer : - CMS links - Store address - Global
			 * payment information - Global shipping information
			 */

			// get from the cache first
			/**
			 * The cache for each object contains 2 objects, a Cache and a
			 * Missed-Cache Get objects from the cache If not null use those
			 * objects If null, get entry from missed-cache If missed-cache not
			 * null then nothing exist If missed-cache null, add missed-cache
			 * entry and load from the database If objects from database not
			 * null store in cache
			 */

			/*******
			 * Top Categories **
			 *
			 * need future research
			 *
			 ******/

			// this.setTopCategories(store, language, request);

			/******* Configuration objects *******/
			/**
			 * SHOP configuration type Should contain - Different configuration
			 * flags - Google analytics - Facebook page - Twitter handle - Show
			 * customer login - ...
			 */

			// this.getMerchantConfigurations(store, request);

			/******* Shopping Cart *********/

			String shoppingCarCode = (String) request.getSession().getAttribute(AppConstants.SHOPPING_CART);
			if (shoppingCarCode != null) {
				request.setAttribute(AppConstants.SHOPPING_CART, shoppingCarCode);
			}

		} catch (Exception e) {
			LOGGER.error("Error in StoreFilter", e);
		}

		return true;

	}

	private MerchantStore setMerchantStoreInSession(HttpServletRequest request, String storeCode) throws Exception {
		if (storeCode == null || request == null)
			return null;
		MerchantStore store = merchantService.getByCode(storeCode);
		if (store != null) {
			request.getSession().setAttribute(AppConstants.MERCHANT_STORE, store);
		}
		return store;
	}

}
