package com.shoppay.core.catalog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.elasticsearch.annotations.Document;

import com.shoppay.common.domain.BusinessDomain;
import com.shoppay.common.domain.BusinessDomainInterface;

/**
 * A ProductOptionValue.
 */
@Entity
@Table(name = "product_option_value")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "productoptionvalue")
public class ProductOptionValue extends BusinessDomain<Long, ProductOption> implements BusinessDomainInterface {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name = "code", nullable = false)
	private String code;

	@Column(name = "sort_order")
	private Integer sortOrder;

	@Column(name = "display_only")
	private Boolean displayOnly;

	@ManyToOne(fetch = FetchType.LAZY)
	private ProductOption productOption;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Boolean getDisplayOnly() {
		return displayOnly;
	}

	public void setDisplayOnly(Boolean displayOnly) {
		this.displayOnly = displayOnly;
	}

	public String getCode() {
		return code;
	}

	public ProductOptionValue code(String code) {
		this.code = code;
		return this;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ProductOption getProductOption() {
		return productOption;
	}

	public ProductOptionValue productOption(ProductOption productOption) {
		this.productOption = productOption;
		return this;
	}

	public void setProductOption(ProductOption productOption) {
		this.productOption = productOption;
	}

}
