package com.shoppay.web.interceptor;

import java.util.Locale;
import java.util.UUID;

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

import com.shoppay.common.constants.ApplicationConstants;
import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.service.MerchantStoreService;
import com.shoppay.core.cart.Cart;
import com.shoppay.core.cart.CartCodeGegerator;
import com.shoppay.core.cart.service.CartService;
import com.shoppay.core.customer.Customer;
import com.shoppay.core.customer.service.CustomerService;
import com.shoppay.shop.utils.CustomerInfoContextHolder;
import com.shoppay.shop.utils.CustomerInfoContextHolder.CustomerInfo;

public class WebInterceptor extends HandlerInterceptorAdapter {
	private final Logger LOGGER = LoggerFactory.getLogger(WebInterceptor.class);
	private final static String STORE_REQUEST_PARAMETER = "store";

	@Inject
	private MerchantStoreService merchantService;

	@Inject
	private CustomerService customerService;

	@Inject
	private CartService cartService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOGGER.info("shop interceptor .....");
		CustomerInfo userInfo = new CustomerInfo();
		String authorization = request.getHeader("Authorization");
		LOGGER.info("The authorization is: {}", authorization);
		request.setCharacterEncoding("UTF-8");
		try {
			/** merchant store **/
			MerchantStore store = (MerchantStore) request.getSession().getAttribute(ApplicationConstants.MERCHANT_STORE);
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
			userInfo.setMerchantStore(store);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			Customer customer = null;
			String shoppingCartCode = null;
			if(auth!=null) {
				customer = customerService.findCustomerByEmailAddress(auth.getName());
			}

			if(customer!=null) {
				userInfo.setCustomer(customer);
				userInfo.setAnony(false);
				Cart cart = this.cartService.getShoppingCartByCustomer(customer);
				shoppingCartCode = cart.getCode();
			}else {
				userInfo.setAnony(true);
				shoppingCartCode = (String) request.getSession().getAttribute(ApplicationConstants.SHOPPING_CART);
				if(StringUtils.isEmpty(shoppingCartCode)) {
					shoppingCartCode = CartCodeGegerator.generateCode();
					request.getSession().setAttribute(ApplicationConstants.SHOPPING_CART, shoppingCartCode);
				}
			}
			/** language & locale **/
			// will to enhance
			LocaleContextHolder.setLocale(Locale.ENGLISH);
			userInfo.setCartCode(shoppingCartCode);
		} catch (Exception e) {
			LOGGER.error("Error in StoreFilter", e);
		}
		System.out.println("customer info is : " + userInfo);
		CustomerInfoContextHolder.setUserInfo(userInfo);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			modelAndView.addObject("continueShopping",
					CustomerInfoContextHolder.getMerchantStore().getContinueShoppingURL());
			modelAndView.addObject("cartTotal", request.getSession().getAttribute(ApplicationConstants.CART_TOTAL));
			modelAndView.addObject("cartItemsTotal", request.getSession().getAttribute(ApplicationConstants.CARTITEMS_TOTAL));
		}
		CustomerInfoContextHolder.clear();
	}

	private MerchantStore setMerchantStoreInSession(HttpServletRequest request, String storeCode) throws Exception {
		if (storeCode == null || request == null)
			return null;
		MerchantStore store = merchantService.getByCode(storeCode);
		if (store != null) {
			request.getSession().setAttribute(ApplicationConstants.MERCHANT_STORE, store);
			request.getSession().setAttribute(ApplicationConstants.CONTINUE_SHOPPING, store.getContinueShoppingURL());
		}
		return store;
	}
}
