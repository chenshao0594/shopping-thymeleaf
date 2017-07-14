package com.shoppay.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.elasticsearch.annotations.Document;

import com.shoppay.attachment.common.AttachmentEnum;
import com.shoppay.common.domain.BusinessDomain;

/**
 * A Attachment.
 */
@Entity
@Table(name = "attachment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "attachment")
public class Attachment extends BusinessDomain<Long, Attachment> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name = "name", nullable = false, updatable = false)
	private String name;

	@Column(name = "content_type", nullable = false, updatable = false)
	private String contentType;

	@Column(name = "jhi_size", updatable = false)
	private Long size;

	@NotEmpty
	@Column(name = "bo_name", nullable = false, updatable = false)
	private String boName;

	@NotNull
	@Column(name = "bo_id", nullable = false, updatable = false)
	private Long boId;

	@NotNull
	@Enumerated(value = EnumType.STRING)
	private AttachmentEnum attachmentType = AttachmentEnum.ATTACHMENT;

	@NotEmpty
	private String path;

	public Attachment() {

	}

	public Attachment(String boName, Long boId) {
		this.boName = boName;
		this.boId = boId;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public Attachment name(String name) {
		this.name = name;
		return this;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContentType() {
		return contentType;
	}

	public Attachment contentType(String contentContentType) {
		this.contentType = contentContentType;
		return this;
	}

	public void setContentType(String contentContentType) {
		this.contentType = contentContentType;
	}

	public Long getSize() {
		return size;
	}

	public Attachment size(Long size) {
		this.size = size;
		return this;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getBoName() {
		return boName;
	}

	public Attachment boName(String boName) {
		this.boName = boName;
		return this;
	}

	public void setBoName(String boName) {
		this.boName = boName;
	}

	public Long getBoId() {
		return boId;
	}

	public Attachment boId(Long boId) {
		this.boId = boId;
		return this;
	}

	public void setBoId(Long boId) {
		this.boId = boId;
	}

	public AttachmentEnum getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(AttachmentEnum attachmentType) {
		this.attachmentType = attachmentType;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
