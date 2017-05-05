package com.smart.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.shopping.domain.MerchantStore;

/**
 * Spring Data JPA repository for the MerchantStore entity.
 */
@SuppressWarnings("unused")
public interface MerchantStoreRepository extends JpaRepository<MerchantStore, Long> {

}
