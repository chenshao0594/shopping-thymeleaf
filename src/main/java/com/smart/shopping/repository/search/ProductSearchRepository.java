package com.smart.shopping.repository.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.smart.shopping.core.catalog.Product;

/**
 * Spring Data Elasticsearch repository for the Product entity.
 */
public interface ProductSearchRepository extends ElasticsearchRepository<Product, Long> {
}
