package com.spring.retail.pojo;

import java.math.BigDecimal;

/**
 * POJO class for Product details.
 */
public class Product {
	
	private ProductCategory productCategory;
	
	private BigDecimal price;
	
	private String productName;

	public Product() {
		super();
	}

	public Product(ProductCategory productCategory, BigDecimal price, String productName) {
		super();
		this.productCategory = productCategory;
		this.price = price;
		this.productName = productName;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "Product [productCategory=" + productCategory + ", price=" + price + "]";
	}

}
