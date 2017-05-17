package com.smart.shopping.core.catalog;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.elasticsearch.annotations.Document;

import com.smart.shopping.domain.common.BusinessDomain;

@Entity
@Table(name = "product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "product")
public class Product extends BusinessDomain<Long, Product> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "metatag_description")
	private String metatagDescription;

	@Column(name = "search_url")
	private String searchUrl;

	@Column(name = "metatag_keywords")
	private String metatagKeywords;

	@Column(name = "highlight")
	private String highlight;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "brief_description")
	private String briefDescription;

	@Column(name = "metatag_title")
	private String metatagTitle;

	@NotNull
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "has_sku")
	private boolean hasSKU = false;

	@Column(name = "external_dl")
	private String externalDl;

	@Column(name = "product_height", precision = 10, scale = 2)
	private BigDecimal productHeight;

	@Column(name = "product_weight", precision = 10, scale = 2)
	private BigDecimal productWeight;

	@Column(name = "product_shipeable")
	private Boolean productShipeable;

	@Column(name = "product_ordered")
	private Integer productOrdered;

	@Min(value = 0, message = "The Product sale price must bo not lee than zero.")
	@Column(name = "sale_price", precision = 10, scale = 2)
	private BigDecimal salePrice;

	@Min(value = 0, message = "The Product retail price must bo not lee than zero.")
	@Column(name = "retail_price", precision = 10, scale = 2)
	private BigDecimal retailPrice;

	@Column(name = "date_available")
	private LocalDate dateAvailable;

	@Column(name = "sort_order")
	private Integer sortOrder;

	@Column(name = "available")
	private Boolean available = true;

	@Column(name = "ref_sku")
	private String refSku;

	@Column(name = "remark")
	private String remark;

	@Column(name = "width", precision = 10, scale = 2)
	private BigDecimal width;

	@Column(name = "length", precision = 10, scale = 2)
	private BigDecimal length;

	@NotEmpty
	@Column(name = "sku", nullable = false)
	private String sku;

	@ManyToOne
	private Category category;

	@Column(name = "standard_price", precision = 10, scale = 2)
	private BigDecimal standardPrice;

	@ManyToMany(fetch = FetchType.EAGER)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@JoinTable(name = "product_option_xref", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "product_option_id", referencedColumnName = "ID"))
	private Set<ProductOption> productOptions = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, targetEntity = SKU.class, mappedBy = "product", cascade = CascadeType.ALL)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	protected Set<SKU> additionalSKUs = new HashSet<>();

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getProductHeight() {
		return productHeight;
	}

	public Product productHeight(BigDecimal productHeight) {
		this.productHeight = productHeight;
		return this;
	}

	public void setProductHeight(BigDecimal productHeight) {
		this.productHeight = productHeight;
	}

	public BigDecimal getProductWeight() {
		return productWeight;
	}

	public Product productWeight(BigDecimal productWeight) {
		this.productWeight = productWeight;
		return this;
	}

	public void setProductWeight(BigDecimal productWeight) {
		this.productWeight = productWeight;
	}

	public Boolean isProductShipeable() {
		return productShipeable;
	}

	public Product productShipeable(Boolean productShipeable) {
		this.productShipeable = productShipeable;
		return this;
	}

	public void setProductShipeable(Boolean productShipeable) {
		this.productShipeable = productShipeable;
	}

	public Integer getProductOrdered() {
		return productOrdered;
	}

	public Product productOrdered(Integer productOrdered) {
		this.productOrdered = productOrdered;
		return this;
	}

	public void setProductOrdered(Integer productOrdered) {
		this.productOrdered = productOrdered;
	}

	public String getMetatagDescription() {
		return metatagDescription;
	}

	public void setMetatagDescription(String metatagDescription) {
		this.metatagDescription = metatagDescription;
	}

	public String getSearchUrl() {
		return searchUrl;
	}

	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
	}

	public String getMetatagKeywords() {
		return metatagKeywords;
	}

	public void setMetatagKeywords(String metatagKeywords) {
		this.metatagKeywords = metatagKeywords;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMetatagTitle() {
		return metatagTitle;
	}

	public void setMetatagTitle(String metatagTitle) {
		this.metatagTitle = metatagTitle;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

	public String getExternalDl() {
		return externalDl;
	}

	public void setExternalDl(String externalDl) {
		this.externalDl = externalDl;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}

	public LocalDate getDateAvailable() {
		return dateAvailable;
	}

	public void setDateAvailable(LocalDate dateAvailable) {
		this.dateAvailable = dateAvailable;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public String getRefSku() {
		return refSku;
	}

	public void setRefSku(String refSku) {
		this.refSku = refSku;
	}

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public BigDecimal getLength() {
		return length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public BigDecimal getStandardPrice() {
		return standardPrice;
	}

	public void setStandardPrice(BigDecimal standardPrice) {
		this.standardPrice = standardPrice;
	}

	public Set<ProductOption> getProductOptions() {
		return productOptions;
	}

	public void setProductOptions(Set<ProductOption> productOptions) {
		this.productOptions = productOptions;
	}

	public Boolean getProductShipeable() {
		return productShipeable;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<SKU> getAdditionalSKUs() {
		return additionalSKUs;
	}

	public void setAdditionalSKUs(Set<SKU> additionalSKUs) {
		this.additionalSKUs = additionalSKUs;
	}

	public boolean isHasSKU() {
		return hasSKU;
	}

	public void setHasSKU(boolean hasSKU) {
		this.hasSKU = hasSKU;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBriefDescription() {
		return briefDescription;
	}

	public void setBriefDescription(String briefDescription) {
		this.briefDescription = briefDescription;
	}

}