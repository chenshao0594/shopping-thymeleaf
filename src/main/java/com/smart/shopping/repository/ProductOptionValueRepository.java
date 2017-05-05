package com.smart.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.smart.shopping.core.catalog.ProductOptionValue;

public interface ProductOptionValueRepository
		extends JpaRepository<ProductOptionValue, Long>, QueryDslPredicateExecutor<ProductOptionValue> {
}
