package com.smartshop.core.catalog;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import com.smartshop.common.domain.BusinessDomain;
import com.smartshop.core.enumeration.ProductRelationshipEnum;

@Entity
@Table(name = "product_relationship")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "productrelationship")
public class ProductRelationship extends BusinessDomain<Long, ProductRelationship> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6290541674864234880L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(targetEntity = Product.class)
	@JoinColumn(name = "PRODUCT_ID", updatable = false, nullable = true)
	private Product product = null;

	@ManyToOne(targetEntity = Product.class)
	@JoinColumn(name = "RELATED_PRODUCT_ID", updatable = false, nullable = true)
	private Product relatedProduct = null;

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private ProductRelationshipEnum type;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Product getRelatedProduct() {
		return relatedProduct;
	}

	public void setRelatedProduct(Product relatedProduct) {
		this.relatedProduct = relatedProduct;
	}

	public ProductRelationshipEnum getType() {
		return type;
	}

	public void setType(ProductRelationshipEnum type) {
		this.type = type;
	}

}
