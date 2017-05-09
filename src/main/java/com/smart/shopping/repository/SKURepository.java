package com.smart.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.smart.shopping.core.catalog.SKU;

public interface SKURepository extends JpaRepository<SKU, Long>, QueryDslPredicateExecutor<SKU> {

}
