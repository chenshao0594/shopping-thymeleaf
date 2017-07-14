package com.shoppay.core.cart.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.shoppay.app.repository.CartItemRepository;
import com.shoppay.common.service.impl.AbstractDomainServiceImpl;
import com.shoppay.core.cart.CartItem;
import com.shoppay.core.cart.QCartItem;
import com.shoppay.core.cart.service.CartItemService;

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

	@Override
	public CartItem findCartItemByItemInfo(Long cartId, Long itemId) {
		QCartItem qCartItem = QCartItem.cartItem;
		BooleanExpression cartExp = qCartItem.shoppingCart.id.eq(cartId);
		BooleanExpression productExp = qCartItem.id.eq(itemId);
		BooleanExpression resultExp = cartExp.and(productExp);
		return this.shoppingCartItemRepository.findOne(resultExp);
	}

}