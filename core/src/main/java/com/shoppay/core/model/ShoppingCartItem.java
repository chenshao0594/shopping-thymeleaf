package com.shoppay.core.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ShoppingCartItem implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private String productName;
	private String skuName;
	private String price;
	private String image;
	private BigDecimal itemPrice;
	private int quantity;
	private Long productId;
	private Long skuId;
	private String code;// shopping cart code
	private String subTotal;

	private List<ShoppingCartAttribute> shoppingCartAttributes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	@Override
	public String toString() {
		return "ShoppingCartItem [id=" + id + ", productName=" + productName + ", skuName=" + skuName + ", price="
				+ price + ", image=" + image + ", itemPrice=" + itemPrice + ", quantity=" + quantity + ", productId="
				+ productId + ", skuId=" + skuId + ", code=" + code + ", subTotal=" + subTotal
				+ ", shoppingCartAttributes=" + shoppingCartAttributes + "]";
	}

}
