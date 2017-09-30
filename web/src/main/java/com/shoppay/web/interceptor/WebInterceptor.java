package com.shoppay.web.interceptor;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
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
import com.shoppay.core.cart.service.CartService;
import com.shoppay.core.customer.Customer;
import com.shoppay.core.customer.service.CustomerService;
import com.shoppay.shop.utils.CustomerInfoContextHolder;
import com.shoppay.shop.utils.CustomerInfoContextHolder.CustomerInfo;

public class WebInterceptor extends HandlerInterceptorAdapter {
	private final Logger LOGGER = LoggerFactory.getLogger(WebInterceptor.class);

	private final static String CART_TOTAL="cartTotal";
	private final static String CART_QUANTITY="cartQuantity";
	
	private final static String CONTINUE_SHOPPING_URL="shoppingURL";

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
		String url = request.getRequestURL().toString();
		String hostName = request.getHeader("Host");
		try {
			/** merchant store **/
			MerchantStore store = this.merchantService.getByCode("DEFAULT");
			userInfo.setMerchantStore(store);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Customer customer = null;
			String shoppingCartCode = null;
			if(auth!=null && !auth.getName().equals("anonymousUser")) {
				customer = customerService.findCustomerByEmailAddress(auth.getName());
			}
			if(customer!=null ) {
				userInfo.setCustomer(customer);
				userInfo.setAnony(false);
				Cart cart = this.cartService.getShoppingCartByCustomer(customer);
				shoppingCartCode = cart.getCode();
			}else {
				userInfo.setAnony(true);
				Map<String, String> cookieMap = this.readCookieMap(request);
				String cartCodeInCookie = cookieMap.get(ApplicationConstants.SHOPPING_CART);
				if(StringUtils.isEmpty(cartCodeInCookie)) {
					Cart cart = this.cartService.createEmptyCart(customer);
					shoppingCartCode=cart.getCode();
				}else {
					shoppingCartCode = cartCodeInCookie;
				}
			}
			Cookie cookie = new Cookie(ApplicationConstants.SHOPPING_CART, shoppingCartCode);
			cookie.setMaxAge(30 * 60);// 设置为30min
			cookie.setPath("/");
			response.addCookie(cookie);
			LocaleContextHolder.setLocale(Locale.ENGLISH);
			userInfo.setCartTotal((String)request.getSession().getAttribute(CART_TOTAL));
			userInfo.setCartQuantity((Integer)request.getSession().getAttribute(CART_QUANTITY));
			userInfo.setCartCode(shoppingCartCode);
		} catch (Exception e) {
			LOGGER.error("Error in StoreFilter", e);
		}
		CustomerInfoContextHolder.setCustomerInfo(userInfo);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		request.getSession().setAttribute(CONTINUE_SHOPPING_URL, CustomerInfoContextHolder.getMerchantStore().getContinueShoppingURL());
		request.getSession().setAttribute(CART_TOTAL, CustomerInfoContextHolder.getCartTotal());
		request.getSession().setAttribute(CART_QUANTITY, CustomerInfoContextHolder.getCartQuantity());
		Cookie cookie = new Cookie(ApplicationConstants.SHOPPING_CART, CustomerInfoContextHolder.getCartCode());
		cookie.setMaxAge(30 * 60);// 设置为30min
		cookie.setPath("/");
		response.addCookie(cookie);
	}


	@Override  
	public void afterCompletion(HttpServletRequest request, 	HttpServletResponse response, Object handler, Exception ex)  
					throws Exception {  
		CustomerInfoContextHolder.clear();
	}
	private  Map<String,String> readCookieMap(HttpServletRequest request){    
		Map<String,String> cookieMap = new HashMap<String,String>();  
		Cookie[] cookies = request.getCookies();  
		if(null!=cookies){  
			for(Cookie cookie : cookies){  
				cookieMap.put(StringUtils.trim(cookie.getName()), cookie.getValue());  
			}  
		}  
		return cookieMap;  
	}  

}
