package com.spring.retail.util;

import java.util.HashMap;

import com.spring.retail.pojo.ProductCategory;

public class ProductCategoryWise {
	
	private HashMap<String, ProductCategory> productAndCategory;

	public ProductCategoryWise() {
		super();
		this.productAndCategory = new HashMap<>();
		setProductAndCategory(productAndCategory);
	}

	public HashMap<String, ProductCategory> getProductAndCategory() {
		return productAndCategory;
	}

	public void setProductAndCategory(HashMap<String, ProductCategory> productAndCategory) {
		
		productAndCategory.put("Biscuits", ProductCategory.GROCERY);
		productAndCategory.put("Toothpaste", ProductCategory.GROCERY);
		productAndCategory.put("Milk", ProductCategory.GROCERY);
		productAndCategory.put("Soap", ProductCategory.GROCERY);
		productAndCategory.put("Shampoo", ProductCategory.GROCERY);
		productAndCategory.put("Mobile", ProductCategory.ELECTRONICS);
		productAndCategory.put("Jeans", ProductCategory.APPARELS);
		productAndCategory.put("Pen", ProductCategory.STATIONERY);
		productAndCategory.put("Pencil", ProductCategory.STATIONERY);
		productAndCategory.put("Books", ProductCategory.STATIONERY);
		productAndCategory.put("Oil", ProductCategory.GROCERY);
	}
	
	

}
