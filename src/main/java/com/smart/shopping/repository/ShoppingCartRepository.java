package com.smart.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.shopping.core.cart.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

}
