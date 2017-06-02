package com.smartshop.service.impl;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
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
import com.smartshop.service.ProductService;
import com.smartshop.shop.model.ShoppingCartItem;

/**
 * Service Implementation for managing Category.
 */
@Service
@Transactional
public class CartServiceImpl extends AbstractDomainServiceImpl<Cart, Long> implements CartService {

	private final Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);
	private final CartRepository shoppingCartRepository;
	private final ShoppingCartSearchRepository shoppingCartSearchRepository;

	@Inject
	private ProductService productService;

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
	public Cart mergeShoppingCarts(final Cart userShoppingCart, final Cart sessionCart, MerchantStore store)
			throws BusinessException {
		if (sessionCart.getCustomerId() != null && sessionCart.getCustomerId() == userShoppingCart.getCustomerId()) {
			LOGGER.info("Session Shopping cart belongs to same logged in user");
			if (CollectionUtils.isNotEmpty(userShoppingCart.getLineItems())
					&& CollectionUtils.isNotEmpty(sessionCart.getLineItems())) {
				return userShoppingCart;
			}
		}

		LOGGER.info("Starting merging shopping carts");
		this.save(userShoppingCart);
		this.delete(sessionCart);
		return userShoppingCart;
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

	@Override
	public Cart getShoppingCartByCustomer(Customer customer) {
		QCart qCart = QCart.cart;
		return this.shoppingCartRepository.findOne(qCart.customerId.eq(customer.getId()));
	}

	private Set<ShoppingCartItem> getShoppingCartItems(final Cart sessionCart, final MerchantStore store) {
		return null;
	}

}