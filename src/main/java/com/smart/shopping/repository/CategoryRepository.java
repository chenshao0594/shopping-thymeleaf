package com.smart.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.shopping.core.catalog.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {


}
