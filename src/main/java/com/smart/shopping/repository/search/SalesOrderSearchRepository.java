package com.smart.shopping.repository.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.smart.shopping.core.order.SalesOrder;

/**
 * Spring Data Elasticsearch repository for the Product entity.
 */
public interface SalesOrderSearchRepository extends ElasticsearchRepository<SalesOrder, Long> {
}
