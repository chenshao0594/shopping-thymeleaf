package com.smartshop.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.smartshop.core.payment.PaymentConfiguration;

public interface PaymentConfigurationRepository
		extends JpaRepository<PaymentConfiguration, Long>, QueryDslPredicateExecutor<PaymentConfiguration> {
}
