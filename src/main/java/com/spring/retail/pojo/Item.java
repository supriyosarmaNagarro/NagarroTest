package com.spring.retail.pojo;

import java.math.BigDecimal;

public class Item {
	
	private ItemCategory item;
	
	private BigDecimal price;

	public Item() {
		super();
	}

	public Item(ItemCategory item, BigDecimal price) {
		super();
		this.item = item;
		this.price = price;
	}

	public ItemCategory getItem() {
		return item;
	}

	public void setItem(ItemCategory item) {
		this.item = item;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Item [item=" + item + ", price=" + price + "]";
	}

}
