package com.smartshop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartshop.core.cart.ShoppingCartItem;
import com.smartshop.core.cart.service.ShoppingCartItemService;
import com.smartshop.repository.ShoppingCartItemRepository;

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