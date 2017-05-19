package com.smartshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartshop.core.cart.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
