package com.smart.shopping.repository.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.smart.shopping.core.cart.ShoppingCart;

/**
 * Spring Data Elasticsearch repository for the Category entity.
 */
public interface ShoppingCartSearchRepository extends ElasticsearchRepository<ShoppingCart, Long> {
}
