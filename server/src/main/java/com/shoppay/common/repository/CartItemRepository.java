package com.shoppay.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.shoppay.core.cart.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>, QueryDslPredicateExecutor<CartItem> {

}
