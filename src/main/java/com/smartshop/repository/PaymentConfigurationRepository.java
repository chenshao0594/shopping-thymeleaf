package com.smartshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.smartshop.domain.PaymentConfiguration;

public interface PaymentConfigurationRepository
		extends JpaRepository<PaymentConfiguration, Long>, QueryDslPredicateExecutor<PaymentConfiguration> {
}
