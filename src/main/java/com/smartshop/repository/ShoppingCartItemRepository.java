package com.smartshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartshop.core.cart.CartItem;

public interface ShoppingCartItemRepository extends JpaRepository<CartItem, Long> {

}
