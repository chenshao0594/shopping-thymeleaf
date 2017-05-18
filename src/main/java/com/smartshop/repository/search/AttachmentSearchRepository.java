package com.smartshop.repository.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.smartshop.domain.Attachment;

/**
 * Spring Data Elasticsearch repository for the Attachment entity.
 */
public interface AttachmentSearchRepository extends ElasticsearchRepository<Attachment, Long> {
}
