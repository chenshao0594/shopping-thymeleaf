package com.smartshop.core.cart.service;

import com.smartshop.core.cart.CartItem;
import com.smartshop.service.AbstractDomainService;

public interface CartItemService extends AbstractDomainService<CartItem, Long> {

	CartItem findCartItemByItemInfo(Long productId, Long skuId, Long cartId);

}