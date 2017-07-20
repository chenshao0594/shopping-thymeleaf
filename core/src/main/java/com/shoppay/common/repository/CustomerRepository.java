package com.shoppay.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.shoppay.core.customer.Customer;

/**
 * Spring Data JPA repository for the Customer entity.
 */
@SuppressWarnings("unused")
public interface CustomerRepository extends JpaRepository<Customer, Long>, QueryDslPredicateExecutor<Customer> {
	
	@Query("SELECT customer from Customer customer where customer.emailAddress=?1")
	Customer findByEmailAddress(String emailAddress);
	
	
	@Query("SELECT customer from Customer customer where customer.name=?1")
	Customer findByName(String name);

}
