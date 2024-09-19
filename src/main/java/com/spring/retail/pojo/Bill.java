package com.spring.retail.pojo;

import java.util.List;

public class Bill {
	
	private List<Item> items;

	public Bill() {
		super();
	}

	public Bill(List<Item> items) {
		super();
		this.items = items;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Bill [items=" + items + "]";
	}

}
