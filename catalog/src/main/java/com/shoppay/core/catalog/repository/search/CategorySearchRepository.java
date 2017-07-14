package com.shoppay.core.catalog.repository.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.shoppay.core.catalog.Category;

/**
 * Spring Data Elasticsearch repository for the Category entity.
 */
public interface CategorySearchRepository extends ElasticsearchRepository<Category, Long> {
}
