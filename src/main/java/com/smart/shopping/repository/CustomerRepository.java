package com.smart.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.shopping.domain.Customer;

/**
 * Spring Data JPA repository for the Customer entity.
 */
@SuppressWarnings("unused")
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
