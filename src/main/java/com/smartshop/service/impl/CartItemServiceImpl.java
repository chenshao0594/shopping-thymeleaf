package com.smartshop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.smartshop.core.cart.CartItem;
import com.smartshop.core.cart.QCartItem;
import com.smartshop.core.cart.service.CartItemService;
import com.smartshop.repository.CartItemRepository;

/**
 * Service Implementation for managing Category.
 */
@Service
@Transactional
public class CartItemServiceImpl extends AbstractDomainServiceImpl<CartItem, Long> implements CartItemService {

	private final Logger LOGGER = LoggerFactory.getLogger(CartItemServiceImpl.class);
	private final CartItemRepository shoppingCartItemRepository;

	public CartItemServiceImpl(CartItemRepository shoppingCartItemRepository) {
		super(shoppingCartItemRepository, null);
		this.shoppingCartItemRepository = shoppingCartItemRepository;
	}

	@Override
	public CartItem findCartItemByItemInfo(Long productId, Long skuId, Long cartId) {
		QCartItem qCartItem = QCartItem.cartItem;
		BooleanExpression cartExp = qCartItem.shoppingCart.id.eq(cartId);
		BooleanExpression productExp = qCartItem.productId.eq(productId);
		BooleanExpression skuExp = qCartItem.skuId.eq(skuId);
		BooleanExpression resultExp = cartExp.and(productExp).and(skuExp);
		return this.shoppingCartItemRepository.findOne(resultExp);
	}

}