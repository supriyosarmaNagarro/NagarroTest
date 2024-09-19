package com.spring.retail.pojo;

import java.util.List;

public class Bill {
	
	private List<Product> products;

	public Bill() {
		super();
	}

	public Bill(List<Product> products) {
		super();
		this.products = products;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Bill [products=" + products + "]";
	}

}
