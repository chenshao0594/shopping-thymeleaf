package com.smart.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.smart.shopping.domain.ShippingConfiguration;

public interface ShippingConfigurationRepository
		extends JpaRepository<ShippingConfiguration, Long>, QueryDslPredicateExecutor<ShippingConfiguration> {
}
