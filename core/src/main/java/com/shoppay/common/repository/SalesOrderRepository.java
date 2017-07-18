package com.shoppay.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.shoppay.core.order.SalesOrder;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long>, QueryDslPredicateExecutor<SalesOrder> {

}
