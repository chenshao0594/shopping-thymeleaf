package com.shoppay.core.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.shoppay.core.catalog.ProductOptionValue;

public interface ProductOptionValueRepository
		extends JpaRepository<ProductOptionValue, Long>, QueryDslPredicateExecutor<ProductOptionValue> {
}
