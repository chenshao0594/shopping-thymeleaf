package com.shoppay.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.shoppay.core.cart.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>, QueryDslPredicateExecutor<Cart> {
	@Query("select c from Cart c left join fetch c.lineItems cl  where c.customerId = ?1")
	Cart findByCustomer(Long customerId);
}
