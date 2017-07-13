package com.smartshop.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.smartshop.core.catalog.ProductRelationship;

public interface ProductRelationshipRepository
		extends JpaRepository<ProductRelationship, Long>, QueryDslPredicateExecutor<ProductRelationship> {

}
