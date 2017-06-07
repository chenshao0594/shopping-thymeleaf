package com.smartshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smartshop.core.catalog.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	@Query("select distinct c from Category c order by c.lineage, c.sortOrder asc")
	public List<Category> findByStore();

	// select code , count(*) from category where parent_id=-1 group by code;

}
