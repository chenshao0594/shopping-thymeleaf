package com.shoppay.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.shoppay.core.payment.PaymentConfiguration;

public interface PaymentConfigurationRepository
		extends JpaRepository<PaymentConfiguration, Long>, QueryDslPredicateExecutor<PaymentConfiguration> {
}
