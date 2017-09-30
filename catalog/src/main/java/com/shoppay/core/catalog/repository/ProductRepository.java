package com.shoppay.core.catalog.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.shoppay.core.catalog.Category;
import com.shoppay.core.catalog.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, QueryDslPredicateExecutor<Product> {

	@Query("select category.id as id, category.code as code , count(product.category.id) as count from Product product group by product.category.id")
	List<Map<String, Long>> countProductsByCategories();

	@Query("select product.name from Product product where product.id=?1")
	String findNameById(Long id);
	
	Page<Product>  findAllByCategory(Category category, Pageable pageable);
	
	Page<Product>  findAllBySearchUrl(String searchUrl, Pageable pageable);
	
	
	Page<Product>  findAllByNameContaining(String name, Pageable pageable);
	// select category_id, count(*) from product where available=1 group by
	// category_id;

}
