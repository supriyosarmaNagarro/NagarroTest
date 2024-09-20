package com.spring.retail;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.spring.retail.pojo.Bill;
import com.spring.retail.pojo.Product;
import com.spring.retail.pojo.ProductCategory;
import com.spring.retail.pojo.User;
import com.spring.retail.pojo.UserCategory;
import com.spring.retail.service.DiscountService;
import com.spring.retail.service.ProductWiseDiscountService;
import com.spring.retail.util.DiscountCalculatorUtility;

/**
 * JUnit test class consisting of unit tests for testing the retail discount calculator.
 */
public class PaymentCalculationTest {
	
	/**
	 * Check total payable amount case 1 - true case.
	 */
	@Test
	public void testCalculate_PayableAmount_1() {


		LocalDate date = LocalDate.now();
		User user = new User("2", "Ravi Sharma", UserCategory.CUSTOMER, date);

		List<Product> items = new ArrayList<Product>();

		items.add(new Product(null, new BigDecimal(100.0),"Biscuits"));
		items.add(new Product(null, new BigDecimal(100.0),"Toothpaste"));
		items.add(new Product(null, new BigDecimal(100.0),"Jeans"));
		items.add(new Product(null, new BigDecimal(100.0),"Mobile"));
		items.add(new Product(null, new BigDecimal(100.0),"Pen"));

		Bill bill = new Bill();
		bill.setProducts(items);

		DiscountService discountService = new ProductWiseDiscountService();
		BigDecimal amount = discountService.getPayableAmount(user, bill);

		assertEquals(475, amount.doubleValue(), 0);
	}


	/**
	 * Check total payable amount case 2 - false case.
	 */
	@Test
	public void testCalculate_PayableAmount_2() {


		LocalDate date = LocalDate.now();
		User user = new User("2", "Ravi Sharma", UserCategory.CUSTOMER, date);

		List<Product> items = new ArrayList<Product>();

		items.add(new Product(null, new BigDecimal(100.0),"Biscuits"));
		items.add(new Product(null, new BigDecimal(100.0),"Toothpaste"));
		items.add(new Product(null, new BigDecimal(100.0),"Jeans"));
		items.add(new Product(null, new BigDecimal(100.0),"Mobile"));
		items.add(new Product(null, new BigDecimal(100.0),"Pen"));

		Bill bill = new Bill();
		bill.setProducts(items);

		DiscountService discountService = new ProductWiseDiscountService();
		BigDecimal amount = discountService.getPayableAmount(user, bill);

		assertNotEquals(25, amount.doubleValue(), 0);
	}
	
	/**
	 * Check total payable amount case 3 - true case.
	 */
	@Test
	public void testCalculate_PayableAmount_3() {


		LocalDate date = LocalDate.now();
		User user = new User("1", "Supriyo Sarma", UserCategory.EMPLOYEE, date);

		List<Product> items = new ArrayList<Product>();

		items.add(new Product(null, new BigDecimal(100.0),"Biscuits"));
		items.add(new Product(null, new BigDecimal(100.0),"Toothpaste"));
		items.add(new Product(null, new BigDecimal(100.0),"Jeans"));
		items.add(new Product(null, new BigDecimal(100.0),"Mobile"));
		items.add(new Product(null, new BigDecimal(100.0),"Pen"));

		Bill bill = new Bill();
		bill.setProducts(items);

		DiscountService discountService = new ProductWiseDiscountService();
		BigDecimal amount = discountService.getPayableAmount(user, bill);

		assertEquals(385, amount.doubleValue(), 0);
	}

	/**
	 * Check utility method all total amount.
	 */
	@Test
	public void testCalculate_AllTotal() {

		List<Product> items = new ArrayList<Product>();
		
		items.add(new Product(ProductCategory.GROCERY, new BigDecimal(100.0),"Biscuits"));
		items.add(new Product(ProductCategory.GROCERY, new BigDecimal(100.0),"Toothpaste"));
		items.add(new Product(ProductCategory.APPARELS, new BigDecimal(100.0),"Jeans"));
		items.add(new Product(ProductCategory.ELECTRONICS, new BigDecimal(100.0),"Mobile"));
		items.add(new Product(ProductCategory.STATIONERY, new BigDecimal(100.0),"Pen"));

		DiscountCalculatorUtility helper = new DiscountCalculatorUtility();
		BigDecimal total = helper.calculateTotal(items);

		assertEquals(500.00, total.doubleValue(), 0);
	}

	/**
	 * Check utility method all total amount - false case.
	 */
	@Test
	public void testCalculate_AllTotalNotEquals() {

		List<Product> items = new ArrayList<Product>();

		items.add(new Product(ProductCategory.GROCERY, new BigDecimal(100.0),"Biscuits"));
		items.add(new Product(ProductCategory.GROCERY, new BigDecimal(100.0),"Toothpaste"));
		items.add(new Product(ProductCategory.APPARELS, new BigDecimal(100.0),"Jeans"));
		items.add(new Product(ProductCategory.ELECTRONICS, new BigDecimal(100.0),"Mobile"));
		items.add(new Product(ProductCategory.STATIONERY, new BigDecimal(100.0),"Pen"));

		DiscountCalculatorUtility helper = new DiscountCalculatorUtility();
		BigDecimal total = helper.calculateTotal(items);

		assertNotEquals(300.00, total.doubleValue(), 0);
	}

	/**
	 * Check utility method for category wise total, Grocery used here.
	 */
	@Test
	public void testCalculate_GroceriesTotal() {

		List<Product> items = new ArrayList<Product>();

		items.add(new Product(ProductCategory.GROCERY, new BigDecimal(100.0),"Biscuits"));
		items.add(new Product(ProductCategory.GROCERY, new BigDecimal(100.0),"Toothpaste"));
		items.add(new Product(ProductCategory.APPARELS, new BigDecimal(100.0),"Jeans"));
		items.add(new Product(ProductCategory.ELECTRONICS, new BigDecimal(100.0),"Mobile"));
		items.add(new Product(ProductCategory.STATIONERY, new BigDecimal(100.0),"Pen"));

		DiscountCalculatorUtility helper = new DiscountCalculatorUtility();
		BigDecimal total = helper.calculateTotalByType(items, ProductCategory.GROCERY);

		assertEquals(200.00, total.doubleValue(), 0);
	}

	
	/**
	 * Check utility method for category wise total, Apparels used here.
	 */
	@Test
	public void testCalculate_CalculateNonGroceryItemWise() {

		List<Product> items = new ArrayList<Product>();

		items.add(new Product(ProductCategory.GROCERY, new BigDecimal(100.0),"Biscuits"));
		items.add(new Product(ProductCategory.GROCERY, new BigDecimal(100.0),"Toothpaste"));
		items.add(new Product(ProductCategory.APPARELS, new BigDecimal(100.0),"Jeans"));
		items.add(new Product(ProductCategory.ELECTRONICS, new BigDecimal(100.0),"Mobile"));
		items.add(new Product(ProductCategory.STATIONERY, new BigDecimal(100.0),"Pen"));

		DiscountCalculatorUtility helper = new DiscountCalculatorUtility();
		BigDecimal total = helper.calculateTotalByType(items, ProductCategory.APPARELS);

		assertEquals(100.00, total.doubleValue(), 0);
	}

	/**
	 * Check utility method for category wise total, false case for Apparels.
	 */
	@Test
	public void testCalculate_CalculateNonGroceryNotEquals() {

		List<Product> items = new ArrayList<Product>();

		items.add(new Product(ProductCategory.GROCERY, new BigDecimal(100.0),"Biscuits"));
		items.add(new Product(ProductCategory.GROCERY, new BigDecimal(100.0),"Toothpaste"));
		items.add(new Product(ProductCategory.APPARELS, new BigDecimal(100.0),"Jeans"));
		items.add(new Product(ProductCategory.ELECTRONICS, new BigDecimal(100.0),"Mobile"));
		items.add(new Product(ProductCategory.STATIONERY, new BigDecimal(100.0),"Pen"));

		DiscountCalculatorUtility helper = new DiscountCalculatorUtility();
		BigDecimal total = helper.calculateTotalByType(items, ProductCategory.APPARELS);

		assertNotEquals(200.00, total.doubleValue(), 0);
	}

	/**
	 * Check utility method for fetching user based discount percentage case 1.
	 */
	@Test
	public void testGetUserDiscount_Employee() {

		User user = new User("1", "Supriyo Sarma",UserCategory.EMPLOYEE, LocalDate.now());

		DiscountCalculatorUtility helper = new DiscountCalculatorUtility();
		BigDecimal discount = helper.getUserDiscount(user);

		assertEquals(0.3, discount.doubleValue(), 0);
	}

	/**
	 * Check utility method for fetching user based discount percentage case 2.
	 */
	@Test
	public void testGetUserDiscount_Affiliate() {

		User user = new User("1", "Supriyo Sarma",UserCategory.AFFILIATE, LocalDate.now());

		DiscountCalculatorUtility helper = new DiscountCalculatorUtility(); 
		BigDecimal discount = helper.getUserDiscount(user);

		assertEquals(0.1, discount.doubleValue(), 0);
	}

	/**
	 * Check utility method for fetching user based discount percentage case 3.
	 */
	@Test
	public void testGetUserSpecificDiscount_applicableCustomer() {

		LocalDate joinDate = LocalDate.of(2021, 2, 23);

		User user = new User("2", "Ravi Sharma", UserCategory.CUSTOMER, joinDate);
		DiscountCalculatorUtility helper = new DiscountCalculatorUtility();
		BigDecimal discount = helper.getUserDiscount(user);

		assertEquals(0.05, discount.doubleValue(), 0);
	}

	/**
	 * Check utility method for fetching user based discount percentage case 4.
	 */
	@Test
	public void testGetUserSpecificDiscount_NonApplicableCustomer() {

		LocalDate date = LocalDate.now();

		User user = new User("2", "Suresh Gopal", UserCategory.CUSTOMER, date);
		DiscountCalculatorUtility helper = new DiscountCalculatorUtility();
		BigDecimal discount = helper.getUserDiscount(user);

		assertEquals(0.0, discount.doubleValue(), 0);
	}

	/**
	 * Check utility method for fetching if an user is a customer since a particular period.
	 */
	@Test
	public void testIsCustomerSince() {

		DiscountCalculatorUtility helper = new DiscountCalculatorUtility();

		LocalDate joinDate = LocalDate.now();
		boolean isTwoYearsJoined = helper.isCustomerSince(joinDate, 2);

		assertFalse(isTwoYearsJoined);
	}

	/**
	 * Check utility method for calculating bill based discount case 1.
	 */
	@Test
	public void testCalculateBillsDiscount_1() {

		DiscountCalculatorUtility helper = new DiscountCalculatorUtility();

		BigDecimal amount = helper.calculateBillDiscount( new BigDecimal(5000), new BigDecimal(100), new BigDecimal(5));

		assertEquals(250, amount.doubleValue(), 0);
	}

	/**
	 * Check utility method for calculating bill based discount case 2.
	 */
	@Test
	public void testCalculateBillsDiscount_2() {

		DiscountCalculatorUtility helper = new DiscountCalculatorUtility();

		BigDecimal amount = helper.calculateBillDiscount( new BigDecimal(5000), new BigDecimal(100), new BigDecimal(5));

		assertNotEquals(350, amount.doubleValue(), 0);
	}

}
