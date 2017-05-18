package com.smartshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartshop.core.cart.Cart;

public interface ShoppingCartRepository extends JpaRepository<Cart, Long> {

}
