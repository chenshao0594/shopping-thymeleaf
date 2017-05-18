package com.smartshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.smartshop.core.catalog.SKU;

public interface SKURepository extends JpaRepository<SKU, Long>, QueryDslPredicateExecutor<SKU> {

}
