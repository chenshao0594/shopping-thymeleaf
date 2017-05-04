package com.smart.shopping.repository.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.smart.shopping.domain.Attachment;

/**
 * Spring Data Elasticsearch repository for the Attachment entity.
 */
public interface AttachmentSearchRepository extends ElasticsearchRepository<Attachment, Long> {
}
