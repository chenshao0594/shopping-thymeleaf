package com.smart.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.smart.shopping.core.catalog.ProductOption;

public interface ProductOptionRepository
		extends JpaRepository<ProductOption, Long>, QueryDslPredicateExecutor<ProductOption> {

	ProductOption findByCode(Long id, String optionCode);
}
