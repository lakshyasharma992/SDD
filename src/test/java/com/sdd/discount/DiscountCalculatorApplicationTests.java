package com.sdd.discount;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.sdd.entity.Product;

import org.junit.Assert;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class DiscountCalculatorApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testEmployeeDiscount() {

		DiscountCalculatorApplication dis = new DiscountCalculatorApplication();

		List<Product> employeeProducts = Arrays.asList(new Product("TV", 10000, false),
				new Product("WheatFlour", 120, true), new Product("Fridge", 9000, false));
		double finalBill = dis.discountCalculate("Employee", 1, employeeProducts);
		Assert.assertEquals(12465.0, finalBill, 0.1);

	}

	@Test
	public void testCustomerDiscount() {
		DiscountCalculatorApplication dis = new DiscountCalculatorApplication();

		List<Product> customerProducts = Arrays.asList(new Product("TV", 10000, false),
				new Product("KitchenUtensils", 1200, true), new Product("AC", 8000, false));

		double customerBill = dis.discountCalculate("Customer", 5, customerProducts);
		Assert.assertEquals(17340.0, customerBill, 0.1);
	}
}
