package com.shoppay.core.catalog.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.shoppay.core.catalog.SKU;

public interface SKURepository extends JpaRepository<SKU, Long>, QueryDslPredicateExecutor<SKU> {

	@Query("select sku.retailPrice from SKU sku where sku.id=?1")
	BigDecimal findRetailPriceById(Long id);

}
