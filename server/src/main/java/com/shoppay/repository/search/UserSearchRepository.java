package com.shoppay.repository.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.shoppay.core.user.User;

/**
 * Spring Data Elasticsearch repository for the User entity.
 */
public interface UserSearchRepository extends ElasticsearchRepository<User, Long> {
}
