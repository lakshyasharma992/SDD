package com.sdd.discount;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sdd.entity.Product;
import com.sdd.exception.ProductNotFoundException;

@SpringBootApplication
public class DiscountCalculatorApplication {

	public static void main(String[] args) {

		List<Product> employeeProducts = Arrays.asList(new Product("TV", 10000, false),
				new Product("WheatFlour", 120, true), new Product("Fridge", 9000, false));

		double employeeBill = discountCalculate("Employee", 0, employeeProducts);
		System.out.println("Bill for employee " + employeeBill);

		List<Product> customerProducts = Arrays.asList(new Product("TV", 10000, false),
				new Product("KitchenUtensils", 1200, true), new Product("AC", 8000, false));

		double customerBill = discountCalculate("Customer", 5, customerProducts);
		System.out.println("Bill for customer " + customerBill);

		double affiliateBill = discountCalculate("Affiliate", 1, employeeProducts);
		System.out.println("Bill for Affiliate " + affiliateBill);

		/*
		 * Empty cart
		 */

		double emptyCartBill = discountCalculate("Affiliate", 1, null);
		System.out.println("Bill for Affiliate " + affiliateBill);
	}

	static double discountCalculate(String user, int years, List<Product> products) {
		if (products == null || products.isEmpty()) {
			throw new ProductNotFoundException("## Cart is Empty ## ");
		}

		double totalPrice = products.stream().collect(Collectors.summingDouble(Product::getPrice));
		double isGroceryItemPrice = products.stream().filter(p -> p.isGrocery())
				.collect(Collectors.summingDouble(Product::getPrice));
		double discountedprice = totalPrice - isGroceryItemPrice;
		double discountPercentage = 0;
		double moreDiscount = 0;
		switch (user) {
		case "Employee":
			discountPercentage = 0.30;
			break;
		case "Customer":
			if (years > 2) {
				discountPercentage = 0.05;
			}
			break;
		case "Affiliate":
			discountPercentage = 0.10;
			break;
		default:
			break;
		}

		double totalDiscountedAmount = discountedprice * discountPercentage;

		if (totalPrice > 100) {
			moreDiscount = (int) (totalPrice / 100) * 5;
		}

		return totalPrice - totalDiscountedAmount - moreDiscount;

	}
}
