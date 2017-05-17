package com.smart.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.shopping.core.cart.ShoppingCartItem;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {

}
