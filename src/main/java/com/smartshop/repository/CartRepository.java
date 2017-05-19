package com.smartshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smartshop.core.cart.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	@Query("select c from Cart c left join fetch c.lineItems cl  where c.customerId = ?1")
	Cart findByCustomer(Long customerId);
}
