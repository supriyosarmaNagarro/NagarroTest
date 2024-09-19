package com.spring.retail.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.spring.retail.pojo.Bill;
import com.spring.retail.pojo.Product;
import com.spring.retail.pojo.ProductCategory;
import com.spring.retail.pojo.User;
import com.spring.retail.util.DiscountCalculatorUtility;
import com.spring.retail.util.ProductCategoryWise;

@Service
public class ProductWiseDiscountService implements DiscountService {

	@Override
	public BigDecimal getPayableAmount(User user, Bill bill) {
		
			DiscountCalculatorUtility utility = new DiscountCalculatorUtility();
			
			BigDecimal finalAmount = new BigDecimal(0);
			BigDecimal totalAmount = new BigDecimal(0);
			BigDecimal groceryAmount = new BigDecimal(0);
			BigDecimal nonGroceryAmount = new BigDecimal(0);
			BigDecimal userBasedDiscount = new BigDecimal(0);
			BigDecimal billBasedDiscount = new BigDecimal(0);
			BigDecimal nonGroceryFinalAmount = new BigDecimal(0);
			
			
			try {
				
				List<Product> products = bill.getProducts();
				
				List<Product> updatedProducts = products.stream().map(p -> {
					ProductCategoryWise productCategories = new ProductCategoryWise();
					p.setProductCategory(productCategories.getProductAndCategory().get(p.getProductName()));
					return p;
				}).collect(Collectors.toList());
				
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
				
				e.printStackTrace();
			}
			return finalAmount;
	}

}
