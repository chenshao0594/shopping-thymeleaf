package com.smart.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.smart.shopping.core.order.SalesOrder;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long>, QueryDslPredicateExecutor<SalesOrder> {

}
