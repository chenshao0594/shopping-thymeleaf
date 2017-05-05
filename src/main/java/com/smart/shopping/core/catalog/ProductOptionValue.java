package com.smart.shopping.core.catalog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import com.smart.shopping.domain.MerchantStore;
import com.smart.shopping.domain.common.BusinessDomain;
import com.smart.shopping.domain.common.BusinessDomainInterface;

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

	@NotNull
	@Column(name = "code", nullable = false)
	private String code;

	@Column(name = "product_option_value_sort_order")
	private Integer productOptionValueSortOrder;

	@Column(name = "product_option_display_only")
	private Boolean productOptionDisplayOnly;

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

	public Integer getProductOptionValueSortOrder() {
		return productOptionValueSortOrder;
	}

	public ProductOptionValue productOptionValueSortOrder(Integer productOptionValueSortOrder) {
		this.productOptionValueSortOrder = productOptionValueSortOrder;
		return this;
	}

	public void setProductOptionValueSortOrder(Integer productOptionValueSortOrder) {
		this.productOptionValueSortOrder = productOptionValueSortOrder;
	}

	public Boolean isProductOptionDisplayOnly() {
		return productOptionDisplayOnly;
	}

	public ProductOptionValue productOptionDisplayOnly(Boolean productOptionDisplayOnly) {
		this.productOptionDisplayOnly = productOptionDisplayOnly;
		return this;
	}

	public void setProductOptionDisplayOnly(Boolean productOptionDisplayOnly) {
		this.productOptionDisplayOnly = productOptionDisplayOnly;
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
