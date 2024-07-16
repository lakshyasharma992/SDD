package com.sdd.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

	private String name;
	private double price;
	private boolean isGroceryItem;

	public Product(String name, double price, boolean isGroceryItem) {
		this.name = name;
		this.price = price;
		this.isGroceryItem = isGroceryItem;
	}
}
