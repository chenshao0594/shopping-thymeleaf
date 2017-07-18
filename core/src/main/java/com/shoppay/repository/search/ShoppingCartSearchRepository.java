package com.shoppay.repository.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.shoppay.core.cart.Cart;

/**
 * Spring Data Elasticsearch repository for the Category entity.
 */
public interface ShoppingCartSearchRepository extends ElasticsearchRepository<Cart, Long> {
}
