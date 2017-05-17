package com.smart.shopping.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.shopping.core.cart.ShoppingCart;
import com.smart.shopping.core.cart.service.ShoppingCartService;
import com.smart.shopping.repository.ShoppingCartRepository;
import com.smart.shopping.repository.search.ShoppingCartSearchRepository;

/**
 * Service Implementation for managing Category.
 */
@Service
@Transactional
public class ShoppingCartServiceImpl extends AbstractDomainServiceImpl<ShoppingCart, Long>
		implements ShoppingCartService {

	private final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);
	private final ShoppingCartRepository shoppingCartRepository;
	private final ShoppingCartSearchRepository shoppingCartSearchRepository;

	public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
			ShoppingCartSearchRepository shoppingCartSearchRepository) {
		super(shoppingCartRepository, shoppingCartSearchRepository);
		this.shoppingCartRepository = shoppingCartRepository;
		this.shoppingCartSearchRepository = shoppingCartSearchRepository;
	}

}