package com.smartshop.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.smartshop.domain.ShippingConfiguration;

public interface ShippingConfigurationRepository
		extends JpaRepository<ShippingConfiguration, Long>, QueryDslPredicateExecutor<ShippingConfiguration> {
}
