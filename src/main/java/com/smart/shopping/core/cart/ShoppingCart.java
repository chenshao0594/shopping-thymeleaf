package com.smart.shopping.core.cart;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import com.smart.shopping.domain.MerchantStore;
import com.smart.shopping.domain.common.BusinessDomain;

@Entity
@Table(name = "SHOPPING_CART")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "shoppingCart")
public class ShoppingCart extends BusinessDomain<Long, ShoppingCart> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8821603703205993575L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "CODE", unique = true, nullable = false)
	private String code;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "shoppingCart")
	private Set<ShoppingCartItem> lineItems = new HashSet<ShoppingCartItem>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MERCHANT_ID")
	private MerchantStore merchantStore;

	@Column(name = "CUSTOMER_ID", nullable = true)
	private Long customerId;

	@Transient
	private boolean obsolete = false;// when all items are obsolete

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Set<ShoppingCartItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(Set<ShoppingCartItem> lineItems) {
		this.lineItems = lineItems;
	}

	public MerchantStore getMerchantStore() {
		return merchantStore;
	}

	public void setMerchantStore(MerchantStore merchantStore) {
		this.merchantStore = merchantStore;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public boolean isObsolete() {
		return obsolete;
	}

	public void setObsolete(boolean obsolete) {
		this.obsolete = obsolete;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
