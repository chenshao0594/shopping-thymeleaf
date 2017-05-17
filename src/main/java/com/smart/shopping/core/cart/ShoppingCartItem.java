package com.smart.shopping.core.cart;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import com.smart.shopping.core.catalog.Product;
import com.smart.shopping.domain.common.LiteDomain;

@Entity
@Table(name = "SHOPPING_CART_ITEM")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "shoppingCartItem")
public class ShoppingCartItem extends LiteDomain<Long, ShoppingCartItem> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5478104738410227947L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(targetEntity = ShoppingCart.class)
	@JoinColumn(name = "SHP_CART_ID", nullable = false)
	private ShoppingCart shoppingCart;

	@Column(name = "QUANTITY")
	private Integer quantity = new Integer(1);

	@Column(name = "PRODUCT_ID", nullable = false)
	private Long productId;

	@Transient
	private boolean productVirtual;

	@Transient
	private BigDecimal itemPrice;// item final price including all rebates

	@Transient
	private BigDecimal subTotal;// item final price * quantity

	@Transient
	private Product product;

	@Transient
	private boolean obsolete = false;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public boolean isProductVirtual() {
		return productVirtual;
	}

	public void setProductVirtual(boolean productVirtual) {
		this.productVirtual = productVirtual;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public boolean isObsolete() {
		return obsolete;
	}

	public void setObsolete(boolean obsolete) {
		this.obsolete = obsolete;
	}

}
