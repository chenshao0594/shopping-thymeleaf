package com.shoppay.repository.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.shoppay.core.order.SalesOrder;

/**
 * Spring Data Elasticsearch repository for the Product entity.
 */
public interface SalesOrderSearchRepository extends ElasticsearchRepository<SalesOrder, Long> {
}
