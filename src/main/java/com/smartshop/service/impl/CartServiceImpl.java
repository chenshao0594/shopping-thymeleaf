package com.smartshop.service.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartshop.core.cart.Cart;
import com.smartshop.core.cart.CartItem;
import com.smartshop.core.cart.QCart;
import com.smartshop.core.cart.service.CartService;
import com.smartshop.core.catalog.Product;
import com.smartshop.domain.Customer;
import com.smartshop.domain.MerchantStore;
import com.smartshop.exception.BusinessException;
import com.smartshop.repository.CartRepository;
import com.smartshop.repository.search.ShoppingCartSearchRepository;

/**
 * Service Implementation for managing Category.
 */
@Service
@Transactional
public class CartServiceImpl extends AbstractDomainServiceImpl<Cart, Long> implements CartService {

	private final Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);
	private final CartRepository shoppingCartRepository;
	private final ShoppingCartSearchRepository shoppingCartSearchRepository;

	public CartServiceImpl(CartRepository shoppingCartRepository,
			ShoppingCartSearchRepository shoppingCartSearchRepository) {
		super(shoppingCartRepository, shoppingCartSearchRepository);
		this.shoppingCartRepository = shoppingCartRepository;
		this.shoppingCartSearchRepository = shoppingCartSearchRepository;
	}

	@Override
	public Cart getShoppingCart(Customer customer) throws BusinessException {
		return shoppingCartRepository.findByCustomer(customer.getId());
	}

	@Override
	public Cart getById(Long id, MerchantStore store) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart getByCode(String code, MerchantStore store) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isFreeShoppingCart(Cart cart) throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFreeShoppingCart(List<CartItem> items) throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CartItem populateShoppingCartItem(Product product) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart mergeShoppingCarts(Cart userShoppingCart, Cart sessionCart, MerchantStore store)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean requiresShipping(Cart cart) throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteShoppingCartItem(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Cart createEmptyCart(Customer customer) {
		Cart cart = new Cart();
		String code = UUID.randomUUID().toString().replaceAll("-", "");
		cart.setCode(code);
		// cart.setCustomerId(customer.getId());
		this.shoppingCartRepository.save(cart);
		return cart;
	}

	@Override
	public Cart getShoppingCartByCode(String cartCode) {
		QCart qCart = QCart.cart;
		return this.shoppingCartRepository.findOne(qCart.code.eq(cartCode));
	}

}