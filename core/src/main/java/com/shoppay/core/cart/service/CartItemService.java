package com.shoppay.core.cart.service;

import com.shoppay.common.service.AbstractDomainService;
import com.shoppay.core.cart.CartItem;

public interface CartItemService extends AbstractDomainService<CartItem, Long> {

	CartItem findCartItemByItemInfo(Long productId, Long skuId, Long cartId);

	CartItem findCartItemByItemInfo(Long cartId, Long itemId);

}