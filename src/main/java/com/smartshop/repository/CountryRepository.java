package com.smartshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.smartshop.core.common.Country;

public interface CountryRepository extends JpaRepository<Country, Long>, QueryDslPredicateExecutor<Country> {

}
