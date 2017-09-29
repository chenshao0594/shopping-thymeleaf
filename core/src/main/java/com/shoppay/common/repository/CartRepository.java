package com.shoppay.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.shoppay.core.cart.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>, QueryDslPredicateExecutor<Cart> {
	@Query("select c from Cart c left join fetch c.lineItems cl  where c.customerId = ?1")
	Cart findByCustomer(Long customerId);
	@Query("select c from Cart c left join fetch c.lineItems cl  where c.code = ?1 and c.merchantStore.id=?2")
	Cart findByCode(String code, long merchantStoreId);
}
