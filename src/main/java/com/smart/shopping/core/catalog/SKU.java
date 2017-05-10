package com.smart.shopping.core.catalog;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.elasticsearch.annotations.Document;

import com.smart.shopping.core.enumeration.StatusEnum;
import com.smart.shopping.domain.common.BusinessDomain;

@Entity
@Table(name = "sku")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "sku")
public class SKU extends BusinessDomain<Long, SKU> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@NotEmpty
	@Column(name = "sku_attributes", updatable = false)
	private String attributes;

	@Column(name = "description")
	private String description;

	@NotNull
	@Column(name = "RETAIL_PRICE", precision = 19, scale = 2, nullable = false)
	protected BigDecimal retailPrice = BigDecimal.ZERO;

	@NotNull
	@Column(name = "SALE_PRICE", precision = 19, scale = 2, nullable = false)
	private BigDecimal salePrice = BigDecimal.ZERO;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusEnum status = StatusEnum.ACTIVITY;

	@ManyToOne(optional = true, targetEntity = Product.class, fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "ADDL_PRODUCT_ID")
	protected Product product;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@JoinTable(name = "sku_product_option_value_xref", joinColumns = @JoinColumn(name = "sku_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "product_option_value_id", referencedColumnName = "ID"))
	protected Set<ProductOptionValue> productOptionValues = new HashSet<ProductOptionValue>();

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Set<ProductOptionValue> getProductOptionValues() {
		return productOptionValues;
	}

	public void setProductOptionValues(Set<ProductOptionValue> productOptionValues) {
		this.productOptionValues = productOptionValues;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

}