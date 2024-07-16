package com.sdd.discount;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sdd.entity.Product;
import com.sdd.exception.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Main class to calculate discount
 */
@SpringBootApplication
public class DiscountCalculatorApplication {

	private static final String CUSTOMER = "Customer";
	private static final String EMPLOYEE = "Employee";
	private static final String AFFILIATE = "Affiliate";
	private final static Logger LOGGER = LoggerFactory.getLogger(DiscountCalculatorApplication.class);

	public static void main(String[] args) {

		List<Product> employeeProducts = Arrays.asList(new Product("TV", 10000, false),
				new Product("WheatFlour", 120, true), new Product("Fridge", 9000, false));

		double employeeBill = discountCalculate(EMPLOYEE, 0, employeeProducts);
		LOGGER.info("Bill for employee " + employeeBill);

		List<Product> customerProducts = Arrays.asList(new Product("TV", 10000, false),
				new Product("KitchenUtensils", 1200, true), new Product("AC", 8000, false));

		double customerBill = discountCalculate(CUSTOMER, 5, customerProducts);
		LOGGER.info("Bill for customer " + customerBill);

		double affiliateBill = discountCalculate(AFFILIATE, 1, employeeProducts);
		LOGGER.info("Bill for Affiliate " + affiliateBill);

		/*
		 * If Cart is Empty
		 */

		double emptyCartBill = discountCalculate(AFFILIATE, 1, null);
		LOGGER.info("Bill for emptyCart " + emptyCartBill);
	}

	static double discountCalculate(String user, int years, List<Product> products) {
		if (products == null || products.isEmpty()) {
			throw new ProductNotFoundException("## Cart is Empty ## ");
		}

		double totalPrice = products.stream().collect(Collectors.summingDouble(Product::getPrice));
		double isGroceryItemPrice = products.stream().filter(p -> p.isGroceryItem())
				.collect(Collectors.summingDouble(Product::getPrice));
		/*
		 * Discount should be given on Non Grocery product items
		 */
		double discountedprice = totalPrice - isGroceryItemPrice;
		double discountPercentage = 0;
		double moreDiscount = 0;
		switch (user) {
		case EMPLOYEE:
			discountPercentage = 0.30;
			break;
		case CUSTOMER:
			if (years > 2) {
				discountPercentage = 0.05;
			}
			break;
		case AFFILIATE:
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
