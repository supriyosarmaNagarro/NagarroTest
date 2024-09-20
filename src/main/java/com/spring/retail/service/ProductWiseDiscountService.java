package com.spring.retail.service;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.spring.retail.controller.CalculatePaymentController;
import com.spring.retail.pojo.Bill;
import com.spring.retail.pojo.Product;
import com.spring.retail.pojo.ProductCategory;
import com.spring.retail.pojo.User;
import com.spring.retail.util.DiscountCalculatorUtility;


@Service
public class ProductWiseDiscountService implements DiscountService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductWiseDiscountService.class);
	private static final String classname = ProductWiseDiscountService.class.getName();

	@Override
	public BigDecimal getPayableAmount(User user, Bill bill) {
		
		String methodName = "getPayableAmount";
		logger.info("Inside class - "+classname+", method - "+methodName);
		
			DiscountCalculatorUtility utility = new DiscountCalculatorUtility();
			
			BigDecimal finalAmount = new BigDecimal(0);
			BigDecimal totalAmount = new BigDecimal(0);
			BigDecimal groceryAmount = new BigDecimal(0);
			BigDecimal nonGroceryAmount = new BigDecimal(0);
			BigDecimal userBasedDiscount = new BigDecimal(0);
			BigDecimal billBasedDiscount = new BigDecimal(0);
			BigDecimal nonGroceryFinalAmount = new BigDecimal(0);
			
			
			try {
				
				List<Product> updatedProducts = utility.getUpdatedproductList(bill.getProducts()); //get updated product list including product category
				
		        totalAmount = utility.calculateTotal(updatedProducts);   //calculate total of all items
		        
		        groceryAmount = utility.calculateTotalByType(updatedProducts, 
		        		ProductCategory.GROCERY);                                      //calculate total of grocery items
		        
		        nonGroceryAmount = totalAmount.subtract(groceryAmount);  // calculate total of non grocery items
		        
		        userBasedDiscount = utility.getUserDiscount(user);            // get user category wise discount percentage
		        
		        if (nonGroceryAmount.compareTo(BigDecimal.ZERO) > 0) {
		        	nonGroceryFinalAmount = utility.calculateDiscount(nonGroceryAmount, userBasedDiscount); //calculate non grocery final amount by subtracting user based discount
				}
		        
		        billBasedDiscount = utility.calculateBillDiscount(totalAmount, 
		        		new BigDecimal(100), new BigDecimal(5));                    // calculate bill based discount

		        finalAmount = (groceryAmount.add(nonGroceryFinalAmount).subtract(billBasedDiscount)); //add grocery, non grocery and subtract bill based discount
				
			}catch(Exception e) {
				
				logger.error(e.getMessage());
			}
			return finalAmount;
	}

}
