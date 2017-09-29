package com.shoppay.core.catalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shoppay.common.domain.MerchantStore;
import com.shoppay.core.catalog.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	@Query("select distinct c from Category c where c.id!=-1 order by c.lineage, c.sortOrder asc")
	public List<Category> findByStore();
}
