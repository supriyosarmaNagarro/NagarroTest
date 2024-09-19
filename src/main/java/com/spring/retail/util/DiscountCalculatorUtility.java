package com.spring.retail.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import com.spring.retail.pojo.Product;
import com.spring.retail.pojo.ProductCategory;
import com.spring.retail.pojo.User;
import com.spring.retail.pojo.UserCategory;


public class DiscountCalculatorUtility {
	
	private static final int YEARS_FOR_DISCOUNT = 2;

    private static final double EMPLOYEE_DISCOUNT = 0.30;
    private static final double AFFILIATE_DISCOUNT = 0.10;
    private static final double CUSTOMER_DISCOUNT = 0.05;

    
    /**
     * method to calculate the total amount for all items
     */
    public BigDecimal calculateTotal(List<Product> items) {
    	
    	BigDecimal amount = new BigDecimal(0);
    	
    	try {
    		
    		amount = items.stream().map(i -> i.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
    	
    	}catch(Exception e) {
    		
    		e.printStackTrace();
    	}
    	return amount;
    }

    /**
     * method to calculate the total amount by using item type
     */
    public BigDecimal calculateTotalByType(List<Product> items, ProductCategory type) {
        
    	BigDecimal sum = new BigDecimal(0);

    	try {
    		
            if (type != null) {
            	
                sum = items.stream().filter(item -> item.getProductCategory().equals(type))
                		.map(item -> item.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
            }
            
    	}catch(Exception e) {
    		e.printStackTrace();
    	}

        return sum;
    }
    

    /**
     * method to get the user wise discount percentage
     */
    public BigDecimal getUserDiscount(User user) {

        BigDecimal discount = new BigDecimal(0);
        
        try {
        	
        	UserCategory type = user.getUserType();

        	if(type.equals(UserCategory.EMPLOYEE)) {
        		
        		discount = new BigDecimal(EMPLOYEE_DISCOUNT).setScale(2, RoundingMode.HALF_EVEN);
        	}
        	else if(type.equals(UserCategory.AFFILIATE)) {
        		
                discount = new BigDecimal(AFFILIATE_DISCOUNT).setScale(2, RoundingMode.HALF_EVEN);
        	}
        	else if(type.equals(UserCategory.CUSTOMER)) {
        		
                if (isCustomerSince(user.getUserRegistrationDate(), YEARS_FOR_DISCOUNT)) {
                    discount = new BigDecimal(CUSTOMER_DISCOUNT).setScale(2, RoundingMode.HALF_EVEN);
                }
        	}
        		
        }catch(Exception e) {
        	
        	e.printStackTrace();
        }
        
        return discount;
    }

    public boolean isCustomerSince(LocalDate registeredDate, long years) {
    	
    	boolean isCustomer = false;
    	
    	try {
    		
            Period period = Period.between(registeredDate, LocalDate.now());
            isCustomer = period.getYears() >= years;
            
    	}catch(Exception e) {
    		e.printStackTrace();
    	}

    	return isCustomer;
    }
    
    
    public BigDecimal calculateDiscount(BigDecimal amount, BigDecimal discountPercentage) {

    	BigDecimal amt = new BigDecimal(0);
    	
    	try {
    		
            BigDecimal discount = amount.multiply(discountPercentage);
            amt = amount.subtract(discount);
            
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
        return amt;
    }
    

    public BigDecimal calculateBillDiscount(BigDecimal totalAmount, BigDecimal amount, BigDecimal discountAmount) {
    	
    	int roundedvalue = 0;
    	BigDecimal discount = new BigDecimal(0);
    	
    	try {
    		
    		roundedvalue = totalAmount.divide(amount).intValue();
    		discount = discountAmount.multiply(new BigDecimal(roundedvalue));
            
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
        return discount;
    }

}
