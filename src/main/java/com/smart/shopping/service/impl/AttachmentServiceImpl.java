package com.smart.shopping.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.smart.shopping.domain.Attachment;
import com.smart.shopping.domain.QAttachment;
import com.smart.shopping.repository.AttachmentRepository;
import com.smart.shopping.repository.search.AttachmentSearchRepository;
import com.smart.shopping.service.AttachmentService;

/**
 * Service Implementation for managing Attachment.
 */
@Service
@Transactional
public class AttachmentServiceImpl implements AttachmentService {

	private final Logger log = LoggerFactory.getLogger(AttachmentServiceImpl.class);

	private final AttachmentRepository attachmentRepository;

	private final AttachmentSearchRepository attachmentSearchRepository;

	public AttachmentServiceImpl(AttachmentRepository attachmentRepository,
			AttachmentSearchRepository attachmentSearchRepository) {
		this.attachmentRepository = attachmentRepository;
		this.attachmentSearchRepository = attachmentSearchRepository;
	}

	/**
	 * Save a attachment.
	 *
	 * @param attachment
	 *            the entity to save
	 * @return the persisted entity
	 */
	@Override
	public Attachment save(Attachment attachment) {
		log.debug("Request to save Attachment : {}", attachment);
		Attachment result = attachmentRepository.save(attachment);
		attachmentSearchRepository.save(result);
		return result;
	}

	/**
	 * Get all the attachments.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Attachment> findAll(Pageable pageable) {
		log.debug("Request to get all Attachments");
		Page<Attachment> result = attachmentRepository.findAll(pageable);
		return result;
	}

	/**
	 * Get one attachment by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Attachment findOne(Long id) {
		log.debug("Request to get Attachment : {}", id);
		Attachment attachment = attachmentRepository.findOne(id);
		return attachment;
	}

	/**
	 * Delete the attachment by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Attachment : {}", id);
		attachmentRepository.delete(id);
		attachmentSearchRepository.delete(id);
	}

	/**
	 * Search for the attachment corresponding to the query.
	 *
	 * @param query
	 *            the query of the search
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Attachment> search(String query, Pageable pageable) {
		log.debug("Request to search for a page of Attachments for query {}", query);
		Page<Attachment> result = attachmentSearchRepository.search(queryStringQuery(query), pageable);
		return result;
	}

	@Override
	public Page<Attachment> findAllByBOInfo(String boName, Long boId, Pageable pageable) {
		QAttachment qAttachment = QAttachment.attachment;
		BooleanExpression boname = qAttachment.boName.eq(boName);
		BooleanExpression boid = qAttachment.boId.eq(boId);
		Page<Attachment> pages = this.attachmentRepository.findAll(boid.and(boname), pageable);
		return pages;
	}

	@Override
	public List<Attachment> findAllByBOInfo(String boName, Long boId) {
		QAttachment qAttachment = QAttachment.attachment;
		BooleanExpression boname = qAttachment.boName.eq(boName);
		BooleanExpression boid = qAttachment.boId.eq(boId);
		List<Attachment> results = (List<Attachment>) this.attachmentRepository.findAll(boid.and(boname));
		return results;
	}

}
