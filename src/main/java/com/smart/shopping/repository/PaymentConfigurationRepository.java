package com.smart.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.smart.shopping.domain.PaymentConfiguration;

public interface PaymentConfigurationRepository
		extends JpaRepository<PaymentConfiguration, Long>, QueryDslPredicateExecutor<PaymentConfiguration> {
}
