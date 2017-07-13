package com.smartshop.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.smartshop.domain.Attachment;

/**
 * Spring Data JPA repository for the Attachment entity.
 */
@SuppressWarnings("unused")
public interface AttachmentRepository extends JpaRepository<Attachment, Long>, QueryDslPredicateExecutor<Attachment> {

}
