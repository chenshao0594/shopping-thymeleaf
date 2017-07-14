package com.shoppay.core.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.shoppay.core.catalog.ProductRelationship;

public interface ProductRelationshipRepository
		extends JpaRepository<ProductRelationship, Long>, QueryDslPredicateExecutor<ProductRelationship> {

}
