package com.smartshop.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.smartshop.domain.MerchantStore;

/**
 * Spring Data JPA repository for the MerchantStore entity.
 */
@SuppressWarnings("unused")
public interface MerchantStoreRepository
		extends JpaRepository<MerchantStore, Long>, QueryDslPredicateExecutor<MerchantStore> {

}
