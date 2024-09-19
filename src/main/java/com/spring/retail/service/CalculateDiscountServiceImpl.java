package com.spring.retail.service;

import java.math.BigDecimal;

import com.spring.retail.pojo.Bill;
import com.spring.retail.pojo.ItemCategory;
import com.spring.retail.pojo.User;
import com.spring.retail.util.DiscountUtility;

public class CalculateDiscountServiceImpl implements CalculateDiscountService {

	@Override
	public BigDecimal getDiscount(User user, Bill bill) {
		
			DiscountUtility utility = new DiscountUtility();
			BigDecimal finalAmount = new BigDecimal(0);
			BigDecimal totalAmount = new BigDecimal(0);
			BigDecimal groceryAmount = new BigDecimal(0);
			BigDecimal nonGroceryAmount = new BigDecimal(0);
			BigDecimal userBasedDiscount = new BigDecimal(0);
			BigDecimal billBasedDiscount = new BigDecimal(0);
			BigDecimal nonGroceryFinalAmount = new BigDecimal(0);
			
			try {
				
		        totalAmount = utility.calculateTotal(bill.getItems());   //calculate total of all items
		        
		        groceryAmount = utility.calculateTotalByType(bill.getItems(), 
		        		ItemCategory.GROCERY);                                      //calculate total of grocery items
		        
		        nonGroceryAmount = totalAmount.subtract(groceryAmount);  // calculate total of non grocery items
		        
		        userBasedDiscount = utility.getUserDiscount(user);            // get user category wise discount percentage
		        
		        billBasedDiscount = utility.calculateBillDiscount(totalAmount, 
		        		new BigDecimal(100), new BigDecimal(5));                    // calculate bill based discount
				
		        if (nonGroceryAmount.compareTo(BigDecimal.ZERO) > 0) {
		        	nonGroceryFinalAmount = utility.calculateDiscount(nonGroceryAmount, userBasedDiscount); //calculate non grocery final amount by subtracting user based discount
				}

		        finalAmount = (groceryAmount.add(nonGroceryFinalAmount).subtract(billBasedDiscount)); //add grocery, non grocery and subtract bill based discount
				
			}catch(Exception e) {
				
				e.printStackTrace();
			}
			return finalAmount;
	}

}
