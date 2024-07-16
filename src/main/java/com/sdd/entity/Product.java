package com.sdd.entity;

public class Product {
	
	 private String name;
     private double price;
     private boolean isGroceryItem;
     
     public Product(String name, double price, boolean isGroceryItem) {
         this.name = name;
         this.price = price;
         this.isGroceryItem = isGroceryItem;
     }
     
     public double getPrice() {
         return price;
     }
     
     public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isGroceryItem() {
		return isGroceryItem;
	}

	public void setGroceryItem(boolean isGroceryItem) {
		this.isGroceryItem = isGroceryItem;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isGrocery() {
         return isGroceryItem;
     }
}
