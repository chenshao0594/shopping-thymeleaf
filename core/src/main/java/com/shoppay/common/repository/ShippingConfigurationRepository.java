package com.shoppay.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.shoppay.domain.ShippingConfiguration;

public interface ShippingConfigurationRepository
		extends JpaRepository<ShippingConfiguration, Long>, QueryDslPredicateExecutor<ShippingConfiguration> {
}
