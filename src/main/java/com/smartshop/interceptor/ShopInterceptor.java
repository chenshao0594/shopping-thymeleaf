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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.smartshop.constants.AppConstants;
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
		String authorization = request.getHeader("Authorization");
		LOGGER.info("The authorization is: {}", authorization);
		request.setCharacterEncoding("UTF-8");
		try {
			/** merchant store **/
			MerchantStore store = (MerchantStore) request.getSession().getAttribute(AppConstants.MERCHANT_STORE);
			String storeCode = request.getParameter(STORE_REQUEST_PARAMETER);
			if (!StringUtils.isBlank(storeCode)) {
				if (store != null) {
					if (!store.getCode().equals(storeCode)) {
						store = setMerchantStoreInSession(request, storeCode);
					}
				} else {
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
				if (customer.getMerchantStore().getId() != store.getId()) {
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
					String ipAddress = "";// GeoLocationUtils.getClientIpAddress(request);
					Address geoAddress = null;// customerService.getCustomerAddress(store,
												// ipAddress);
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

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			modelAndView.addObject("continueShopping",
					request.getSession().getAttribute(AppConstants.CONTINUE_SHOPPING));
			modelAndView.addObject("cartTotal", request.getSession().getAttribute(AppConstants.CART_TOTAL));
			modelAndView.addObject("cartItemsTotal", request.getSession().getAttribute(AppConstants.CARTITEMS_TOTAL));
		}

	}

	private MerchantStore setMerchantStoreInSession(HttpServletRequest request, String storeCode) throws Exception {
		if (storeCode == null || request == null)
			return null;
		MerchantStore store = merchantService.getByCode(storeCode);
		if (store != null) {
			request.getSession().setAttribute(AppConstants.MERCHANT_STORE, store);
			request.getSession().setAttribute(AppConstants.CONTINUE_SHOPPING, store.getContinueShoppingURL());
		}
		return store;
	}

}
