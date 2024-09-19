package com.spring.retail;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.spring.retail.pojo.Item;
import com.spring.retail.pojo.ItemCategory;
import com.spring.retail.pojo.User;
import com.spring.retail.pojo.UserCategory;
import com.spring.retail.util.DiscountUtility;

public class PaymentCalculationTest {
	
	@Test
	public void testCalculate_AllTotal() {
		
		List<Item> items = new ArrayList<Item>();

		items.add(new Item(ItemCategory.GROCERY, new BigDecimal(100.0)));
		items.add(new Item(ItemCategory.GROCERY, new BigDecimal(100.0)));
		items.add(new Item(ItemCategory.APPARELS, new BigDecimal(100.0)));
		items.add(new Item(ItemCategory.ELECTRONICS, new BigDecimal(100.0)));
		items.add(new Item(ItemCategory.STATIONERY, new BigDecimal(100.0)));

		DiscountUtility helper = new DiscountUtility();
		BigDecimal total = helper.calculateTotal(items);
		
		assertEquals(500.00, total.doubleValue(), 0);
	}
	
	@Test
	public void testCalculate_AllTotalNotEquals() {
		
		List<Item> items = new ArrayList<Item>();

		items.add(new Item(ItemCategory.GROCERY, new BigDecimal(100.0)));
		items.add(new Item(ItemCategory.GROCERY, new BigDecimal(100.0)));
		items.add(new Item(ItemCategory.APPARELS, new BigDecimal(100.0)));
		items.add(new Item(ItemCategory.ELECTRONICS, new BigDecimal(100.0)));
		items.add(new Item(ItemCategory.STATIONERY, new BigDecimal(100.0)));

		DiscountUtility helper = new DiscountUtility();
		BigDecimal total = helper.calculateTotal(items);
		
		assertNotEquals(300.00, total.doubleValue(), 0);
	}

	@Test
	public void testCalculate_GroceriesTotal() {

		List<Item> items = new ArrayList<Item>();

		items.add(new Item(ItemCategory.GROCERY, new BigDecimal(100.0)));
		items.add(new Item(ItemCategory.GROCERY, new BigDecimal(100.0)));
		items.add(new Item(ItemCategory.APPARELS, new BigDecimal(100.0)));
		items.add(new Item(ItemCategory.ELECTRONICS, new BigDecimal(100.0)));
		items.add(new Item(ItemCategory.STATIONERY, new BigDecimal(100.0)));

		DiscountUtility helper = new DiscountUtility();
		BigDecimal total = helper.calculateTotalByType(items, ItemCategory.GROCERY);

		assertEquals(200.00, total.doubleValue(), 0);
	}
	
	@Test
	public void testCalculate_CalculateNonGrocerySampleTotal() {

		List<Item> items = new ArrayList<Item>();

		items.add(new Item(ItemCategory.GROCERY, new BigDecimal(100.0)));
		items.add(new Item(ItemCategory.GROCERY, new BigDecimal(100.0)));
		items.add(new Item(ItemCategory.APPARELS, new BigDecimal(100.0)));
		items.add(new Item(ItemCategory.ELECTRONICS, new BigDecimal(100.0)));
		items.add(new Item(ItemCategory.STATIONERY, new BigDecimal(100.0)));

		DiscountUtility helper = new DiscountUtility();
		BigDecimal total = helper.calculateTotalByType(items, ItemCategory.APPARELS);

		assertEquals(100.00, total.doubleValue(), 0);
	}
	
	@Test
	public void testCalculate_CalculateNonGroceryNotEquals() {

		List<Item> items = new ArrayList<Item>();

		items.add(new Item(ItemCategory.GROCERY, new BigDecimal(100.0)));
		items.add(new Item(ItemCategory.GROCERY, new BigDecimal(100.0)));
		items.add(new Item(ItemCategory.APPARELS, new BigDecimal(100.0)));
		items.add(new Item(ItemCategory.ELECTRONICS, new BigDecimal(100.0)));
		items.add(new Item(ItemCategory.STATIONERY, new BigDecimal(100.0)));

		DiscountUtility helper = new DiscountUtility();
		BigDecimal total = helper.calculateTotalByType(items, ItemCategory.APPARELS);

		assertNotEquals(200.00, total.doubleValue(), 0);
	}
	
	@Test
    public void testGetUserDiscount_Employee() {
		
        User user = new User("1", "Supriyo Sarma",UserCategory.EMPLOYEE, LocalDate.now());
        
        DiscountUtility helper = new DiscountUtility();
        BigDecimal discount = helper.getUserDiscount(user);
        
        assertEquals(0.3, discount.doubleValue(), 0);
    }
	
	@Test
    public void testGetUserDiscount_Affiliate() {
		
        User user = new User("1", "Supriyo Sarma",UserCategory.AFFILIATE, LocalDate.now());
        
        DiscountUtility helper = new DiscountUtility(); 
        BigDecimal discount = helper.getUserDiscount(user);
        
        assertEquals(0.1, discount.doubleValue(), 0);
    }
	
	
	  @Test
	    public void testGetUserSpecificDiscount_applicableCustomer() {
		  
	        LocalDate joinDate = LocalDate.of(2021, 2, 23);
	        
	        User user = new User("2", "Ravi Sharma", UserCategory.CUSTOMER, joinDate);
	        DiscountUtility helper = new DiscountUtility();
	        BigDecimal discount = helper.getUserDiscount(user);
	        
	        assertEquals(0.05, discount.doubleValue(), 0);
	    }

	    @Test
	    public void testGetUserSpecificDiscount_NonApplicableCustomer() {
	    	
	    	LocalDate date = LocalDate.now();
	    	
	        User user = new User("2", "Suresh Gopal", UserCategory.CUSTOMER, date);
	        DiscountUtility helper = new DiscountUtility();
	        BigDecimal discount = helper.getUserDiscount(user);
	        
	        assertEquals(0.0, discount.doubleValue(), 0);
	    }


}
