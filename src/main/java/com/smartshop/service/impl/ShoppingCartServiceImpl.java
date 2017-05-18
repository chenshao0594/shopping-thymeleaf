package com.smartshop.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartshop.core.cart.ShoppingCart;
import com.smartshop.core.cart.ShoppingCartItem;
import com.smartshop.core.cart.service.ShoppingCartService;
import com.smartshop.core.catalog.Product;
import com.smartshop.domain.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.exception.BusinessException;
import com.smartshop.repository.ShoppingCartRepository;
import com.smartshop.repository.search.ShoppingCartSearchRepository;

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

	@Override
	public ShoppingCart getShoppingCart(Customer customer) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(ShoppingCart shoppingCart) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public ShoppingCart getById(Long id, MerchantStore store) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShoppingCart getByCode(String code, MerchantStore store) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShoppingCart getByCustomer(Customer customer) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isFreeShoppingCart(ShoppingCart cart) throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFreeShoppingCart(List<ShoppingCartItem> items) throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ShoppingCartItem populateShoppingCartItem(Product product) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCart(ShoppingCart cart) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeShoppingCart(ShoppingCart cart) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public ShoppingCart mergeShoppingCarts(ShoppingCart userShoppingCart, ShoppingCart sessionCart, MerchantStore store)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean requiresShipping(ShoppingCart cart) throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteShoppingCartItem(Long id) {
		// TODO Auto-generated method stub

	}

}