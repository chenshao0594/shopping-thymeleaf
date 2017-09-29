package com.shoppay.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.shoppay.common.domain.MerchantStore;

/**
 * Spring Data JPA repository for the MerchantStore entity.
 */
@SuppressWarnings("unused")
public interface MerchantStoreRepository
		extends JpaRepository<MerchantStore, Long>, QueryDslPredicateExecutor<MerchantStore> {
	
	MerchantStore findByAddress(String address);
}
