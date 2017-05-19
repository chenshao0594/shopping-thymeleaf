package com.smartshop.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ShoppingCartItem implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	private String price;
	private String image;
	private BigDecimal productPrice;
	private int quantity;
	private Long productId;
	private Long skuId;
	private String productCode;
	private String code;// shopping cart code
	private String subTotal;

	private List<ShoppingCartAttribute> shoppingCartAttributes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

	public List<ShoppingCartAttribute> getShoppingCartAttributes() {
		return shoppingCartAttributes;
	}

	public void setShoppingCartAttributes(List<ShoppingCartAttribute> shoppingCartAttributes) {
		this.shoppingCartAttributes = shoppingCartAttributes;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	@Override
	public String toString() {
		return "ShoppingCartItem [id=" + id + ", name=" + name + ", price=" + price + ", image=" + image
				+ ", productPrice=" + productPrice + ", quantity=" + quantity + ", productId=" + productId + ", skuId="
				+ skuId + ", productCode=" + productCode + ", code=" + code + ", subTotal=" + subTotal
				+ ", shoppingCartAttributes=" + shoppingCartAttributes + "]";
	}

}
