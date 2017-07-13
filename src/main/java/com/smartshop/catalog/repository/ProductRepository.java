package com.smartshop.catalog.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.smartshop.core.catalog.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, QueryDslPredicateExecutor<Product> {

	@Query("select category.id as id, category.code as code , count(product.category.id) as count from Product product group by product.category.id")
	List<Map<String, Long>> countProductsByCategories();

	@Query("select product.name from Product product where product.id=?1")
	String findNameById(Long id);

	// select category_id, count(*) from product where available=1 group by
	// category_id;

}
