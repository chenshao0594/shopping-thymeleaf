package com.shoppay.common.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shoppay.common.attachment.AttachmentEnum;
import com.shoppay.common.domain.Attachment;

/**
 * Service Interface for managing Attachment.
 */
public interface AttachmentService {

	/**
	 * Save a attachment.
	 *
	 * @param attachment
	 *            the entity to save
	 * @return the persisted entity
	 */
	Attachment save(Attachment attachment);

	/**
	 * Get all the attachments.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	Page<Attachment> findAll(Pageable pageable);

	/**
	 * Get the "id" attachment.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	Attachment findOne(Long id);

	/**
	 * Delete the "id" attachment.
	 *
	 * @param id
	 *            the id of the entity
	 */
	void delete(Long id);

	/**
	 * Search for the attachment corresponding to the query.
	 *
	 * @param query
	 *            the query of the search
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	Page<Attachment> search(String query, Pageable pageable);

	Page<Attachment> findAllByBOInfo(String boName, Long boId, Pageable pageable);

	Attachment findThumbnail(String boName, Long boId);

	List<Attachment> findAllByBOInfo(String boName, Long boId, AttachmentEnum attachmentType);
}
