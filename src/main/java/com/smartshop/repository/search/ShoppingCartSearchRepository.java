package com.smartshop.repository.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.smartshop.core.cart.Cart;

/**
 * Spring Data Elasticsearch repository for the Category entity.
 */
public interface ShoppingCartSearchRepository extends ElasticsearchRepository<Cart, Long> {
}
