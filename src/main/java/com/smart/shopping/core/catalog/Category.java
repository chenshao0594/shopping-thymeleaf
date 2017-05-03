package com.smart.shopping.core.catalog;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smart.shopping.domain.common.BusinessDomain;

/**
 * A Category.
 */
@Entity
@Table(name = "category")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "category")
public class Category extends BusinessDomain<Long, Category> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "depth")
	private Integer depth=0;

	@Column(name = "sort_order")
	private Integer sortOrder=0;

	@Column(name = "status")
	private Boolean categoryStatus;

	@Column(name = "lineage")
	private String lineage;

	@Column(name = "visible")
	private Boolean visible;

	@NotNull
	@Column(name = "code", nullable = false)
	private String code;

	@Column(name = "image")
	private String categoryImage;

	@JsonIgnore
	@OneToMany(mappedBy = "parent")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<Category> categories = new HashSet<>();

	@ManyToOne(targetEntity = Category.class)
	@JoinColumn(name = "PARENT_ID")
	private Category parent;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDepth() {
		return depth;
	}

	public Category depth(Integer depth) {
		this.depth = depth;
		return this;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public Category sortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
		return this;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Boolean isCategoryStatus() {
		return categoryStatus;
	}

	public Category categoryStatus(Boolean categoryStatus) {
		this.categoryStatus = categoryStatus;
		return this;
	}

	public void setCategoryStatus(Boolean categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	public String getLineage() {
		return lineage;
	}

	public Category lineage(String lineage) {
		this.lineage = lineage;
		return this;
	}

	public void setLineage(String lineage) {
		this.lineage = lineage;
	}

	public Boolean isVisible() {
		return visible;
	}

	public Category visible(Boolean visible) {
		this.visible = visible;
		return this;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public String getCode() {
		return code;
	}

	public Category code(String code) {
		this.code = code;
		return this;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCategoryImage() {
		return categoryImage;
	}

	public Category categoryImage(String categoryImage) {
		this.categoryImage = categoryImage;
		return this;
	}

	public void setCategoryImage(String categoryImage) {
		this.categoryImage = categoryImage;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public Category categories(Set<Category> categories) {
		this.categories = categories;
		return this;
	}

	public Category addCategories(Category category) {
		this.categories.add(category);
		category.setParent(this);
		return this;
	}

	public Category removeCategories(Category category) {
		this.categories.remove(category);
		category.setParent(null);
		return this;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Category getParent() {
		return parent;
	}

	public Category parent(Category category) {
		this.parent = category;
		return this;
	}

	public void setParent(Category category) {
		this.parent = category;
	}

}
