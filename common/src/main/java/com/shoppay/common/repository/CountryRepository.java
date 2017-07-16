package com.shoppay.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.shoppay.common.reference.Country;

public interface CountryRepository extends JpaRepository<Country, Long>, QueryDslPredicateExecutor<Country> {

}
