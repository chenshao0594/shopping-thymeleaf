package com.smartshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.smartshop.core.catalog.ProductOption;

public interface ProductOptionRepository
		extends JpaRepository<ProductOption, Long>, QueryDslPredicateExecutor<ProductOption> {

	ProductOption findByCode(String optionCode);
}
