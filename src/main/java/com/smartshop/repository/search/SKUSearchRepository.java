package com.smartshop.repository.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.smartshop.core.catalog.SKU;

/**
 * Spring Data Elasticsearch repository for the Product entity.
 */
public interface SKUSearchRepository extends ElasticsearchRepository<SKU, Long> {
}
