package com.smart.shopping.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.shopping.core.cart.ShoppingCartItem;
import com.smart.shopping.core.cart.service.ShoppingCartItemService;
import com.smart.shopping.repository.ShoppingCartItemRepository;

/**
 * Service Implementation for managing Category.
 */
@Service
@Transactional
public class ShoppingCartItemServiceImpl extends AbstractDomainServiceImpl<ShoppingCartItem, Long>
		implements ShoppingCartItemService {

	private final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartItemServiceImpl.class);
	private final ShoppingCartItemRepository shoppingCartItemRepository;

	public ShoppingCartItemServiceImpl(ShoppingCartItemRepository shoppingCartItemRepository) {
		super(shoppingCartItemRepository, null);
		this.shoppingCartItemRepository = shoppingCartItemRepository;
	}

}