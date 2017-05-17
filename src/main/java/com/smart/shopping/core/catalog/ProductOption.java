package com.smart.shopping.core.catalog;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import com.smart.shopping.core.enumeration.ProductOptionEnum;
import com.smart.shopping.domain.MerchantStore;
import com.smart.shopping.domain.common.BusinessDomain;

/**
 * A ProductOption.
 */
@Entity
@Table(name = "product_option")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "productoption")
public class ProductOption extends BusinessDomain<Long, ProductOption> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "read_only")
	private Boolean readOnly = true;

	@Column(name = "type")
	@Enumerated(value = EnumType.STRING)
	private ProductOptionEnum type = ProductOptionEnum.TEXT;

	@NotNull
	@Column(name = "code", nullable = false)
	private String code;

	@Column(name = "Sort_order")
	private Integer sortOrder;

	private String description;

	@OneToMany(mappedBy = "productOption", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@OrderBy("sortOrder")
	private Set<ProductOptionValue> productOptionValues = new HashSet<>();

	@ManyToOne()
	private MerchantStore merchantStore;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Boolean isReadOnly() {
		return readOnly;
	}

	public ProductOption readOnly(Boolean readOnly) {
		this.readOnly = readOnly;
		return this;
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}

	public ProductOptionEnum getType() {
		return type;
	}

	public ProductOption type(ProductOptionEnum productOptionType) {
		this.type = productOptionType;
		return this;
	}

	public void setType(ProductOptionEnum productOptionType) {
		this.type = productOptionType;
	}

	public String getCode() {
		return code;
	}

	public ProductOption code(String code) {
		this.code = code;
		return this;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getReadOnly() {
		return readOnly;
	}

	public MerchantStore getMerchantStore() {
		return merchantStore;
	}

	public ProductOption merchantStore(MerchantStore merchantStore) {
		this.merchantStore = merchantStore;
		return this;
	}

	public void setMerchantStore(MerchantStore merchantStore) {
		this.merchantStore = merchantStore;
	}

	public Set<ProductOptionValue> getProductOptionValues() {
		return productOptionValues;
	}

	public void setProductOptionValues(Set<ProductOptionValue> productOptionValues) {
		this.productOptionValues = productOptionValues;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Override
	public String toString() {
		return "ProductOption [id=" + id + ", readOnly=" + readOnly + ", productOptionType=" + type + ", code=" + code
				+ ", sortOrder=" + sortOrder + ", description=" + description + ", productOptionValues="
				+ productOptionValues + ", merchantStore=" + merchantStore + "]";
	}

}
