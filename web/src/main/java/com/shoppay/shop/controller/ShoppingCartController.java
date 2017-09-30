package com.shoppay.shop.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codahale.metrics.annotation.Timed;
import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.common.exception.ConversionException;
import com.shoppay.core.cart.Cart;
import com.shoppay.core.cart.service.CartService;
import com.shoppay.core.facade.ShoppingCartFacade;
import com.shoppay.shop.model.ShoppingCartData;
import com.shoppay.shop.utils.CustomerInfoContextHolder;
import com.shoppay.web.constants.ShoppingControllerConstants;

@Controller("ShopCartController")
@RequestMapping("/shoppingcart")
public class ShoppingCartController extends AbstractShoppingController {

	private final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartController.class);

	@Inject
	private CartService shoppingCartService;

	@Inject
	private ShoppingCartFacade shoppingCartFacade;

	@Timed
	@GetMapping()
	public ModelAndView detail(ModelAndView model, final HttpServletRequest request) throws Exception {
		MerchantStore store = CustomerInfoContextHolder.getMerchantStore();
		String cartCode = CustomerInfoContextHolder.getCartCode();
		if (StringUtils.isBlank(cartCode)) {
			throw new BusinessException("can't work");
		}
		Cart shoppingCart = shoppingCartService.getShoppingCartByCode(cartCode);
		ShoppingCartData shoppingCartData = shoppingCartFacade.getShoppingCartData(shoppingCart, store);
		boolean isEmpty = CollectionUtils.isEmpty(shoppingCartData.getShoppingCartItems());
		
		CustomerInfoContextHolder.getCustomerInfo().setCartTotal(shoppingCartData.getTotal());
		CustomerInfoContextHolder.getCustomerInfo().setCartQuantity(shoppingCartData.getQuantity());
		
		model.addObject("shoppingcart", shoppingCartData);
		model.addObject("isEmpty", isEmpty);
		model.setViewName(ShoppingControllerConstants.ShoppingCart.detail);
		return model;
	}
	
	@Timed
	@GetMapping("/{cartId}/items/{itemId}")
	public String delete(@PathVariable final long cartId, @PathVariable final long itemId, ModelAndView model ) throws BusinessException, ConversionException {
		MerchantStore store = CustomerInfoContextHolder.getMerchantStore();
		this.shoppingCartFacade.removeCartItem(itemId, cartId, store);
		return "redirect:/shoppingcart";
	}

}
