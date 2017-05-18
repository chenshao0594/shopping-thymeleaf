package com.smartshop.core.catalog;

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

import com.smartshop.domain.MerchantStore;
import com.smartshop.domain.common.BusinessDomain;
import com.smartshop.domain.common.BusinessDomainInterface;

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

	@Column(name = "product_option_value_image")
	private String productOptionValueImage;

	@NotEmpty
	@Column(name = "code", nullable = false)
	private String code;

	@Column(name = "sort_order")
	private Integer sortOrder;

	@Column(name = "display_only")
	private Boolean displayOnly;

	@ManyToOne(fetch = FetchType.LAZY)
	private MerchantStore merchantStore;

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

	public String getProductOptionValueImage() {
		return productOptionValueImage;
	}

	public ProductOptionValue productOptionValueImage(String productOptionValueImage) {
		this.productOptionValueImage = productOptionValueImage;
		return this;
	}

	public void setProductOptionValueImage(String productOptionValueImage) {
		this.productOptionValueImage = productOptionValueImage;
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

	public MerchantStore getMerchantStore() {
		return merchantStore;
	}

	public ProductOptionValue merchantStore(MerchantStore merchantStore) {
		this.merchantStore = merchantStore;
		return this;
	}

	public void setMerchantStore(MerchantStore merchantStore) {
		this.merchantStore = merchantStore;
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
