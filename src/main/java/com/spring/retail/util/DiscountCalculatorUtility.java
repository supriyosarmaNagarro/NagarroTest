package com.spring.retail.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.retail.pojo.Product;
import com.spring.retail.pojo.ProductCategory;
import com.spring.retail.pojo.User;
import com.spring.retail.pojo.UserCategory;

/**
 * Utility class for carrying out various calculations related to applying discounts.
 */
public class DiscountCalculatorUtility {
	
	private static final Logger logger = LoggerFactory.getLogger(DiscountCalculatorUtility.class);
	private static final String classname = DiscountCalculatorUtility.class.getName();
	
	private static final int YEARS_FOR_DISCOUNT = 2;

    private static final double EMPLOYEE_DISCOUNT = 0.30;
    private static final double AFFILIATE_DISCOUNT = 0.10;
    private static final double CUSTOMER_DISCOUNT = 0.05;

    
    /**
     * method to calculate the total amount for all items
     */
    public BigDecimal calculateTotal(List<Product> items) {
    	
    	String methodName = "calculateTotal";
		logger.info("Inside class - "+classname+", method - "+methodName);
    	
    	BigDecimal amount = new BigDecimal(0);
    	
    	try {
    		
    		amount = items.stream().map(i -> i.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
    	
    	}catch(Exception e) {
    		logger.error(e.getMessage());
    	}
    	return amount;
    }

    /**
     * method to calculate the total amount by using item type
     */
    public BigDecimal calculateTotalByType(List<Product> items, ProductCategory type) {
    	
    	String methodName = "calculateTotalByType";
		logger.info("Inside class - "+classname+", method - "+methodName);
        
    	BigDecimal sum = new BigDecimal(0);

    	try {
    		
            if (type != null) {
            	
                sum = items.stream().filter(item -> item.getProductCategory().equals(type))
                		.map(item -> item.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
            }
            
    	}catch(Exception e) {
    		logger.error(e.getMessage());
    	}

        return sum;
    }
    

    /**
     * method to get the user wise discount percentage
     */
    public BigDecimal getUserDiscount(User user) {
    	
    	String methodName = "getUserDiscount";
		logger.info("Inside class - "+classname+", method - "+methodName);

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
        	logger.error(e.getMessage());
        }
        
        return discount;
    }

    /**
     * method to check whether a customer is older than a given number of years
     */
    public boolean isCustomerSince(LocalDate registeredDate, long years) {
    	
    	String methodName = "isCustomerSince";
		logger.info("Inside class - "+classname+", method - "+methodName);
    	
    	boolean isCustomer = false;
    	
    	try {
    		
            Period period = Period.between(registeredDate, LocalDate.now());
            isCustomer = period.getYears() >= years;
            
    	}catch(Exception e) {
    		logger.error(e.getMessage());
    	}

    	return isCustomer;
    }
    
    /**
     * method to calculate the discount based on a given percentage
     */
    public BigDecimal calculateDiscount(BigDecimal amount, BigDecimal discountPercentage) {
    	
    	String methodName = "calculateDiscount";
		logger.info("Inside class - "+classname+", method - "+methodName);

    	BigDecimal amt = new BigDecimal(0);
    	
    	try {
    		
            BigDecimal discount = amount.multiply(discountPercentage);
            amt = amount.subtract(discount);
            
    	}catch(Exception e) {
    		logger.error(e.getMessage());
    	}
        return amt;
    }
    
    /**
     * method to calculate the bill discount based on total amount
     */
    public BigDecimal calculateBillDiscount(BigDecimal totalAmount, BigDecimal amount, BigDecimal discountAmount) {
    	
    	String methodName = "calculateBillDiscount";
		logger.info("Inside class - "+classname+", method - "+methodName);
    	
    	int roundedvalue = 0;
    	BigDecimal discount = new BigDecimal(0);
    	
    	try {
    		
    		roundedvalue = totalAmount.divide(amount).intValue();
    		discount = discountAmount.multiply(new BigDecimal(roundedvalue));
            
    	}catch(Exception e) {
    		logger.error(e.getMessage());
    	}
        return discount;
    }
    
    /**
     * method to fetch product category for each product
     */
    public List<Product> getUpdatedproductList(List<Product> products){
    	
    	String methodName = "getUpdatedproductList";
		logger.info("Inside class - "+classname+", method - "+methodName);
    	
    	return products.stream().map(p -> {
			ProductCategoryUtility productCategories = new ProductCategoryUtility();
			p.setProductCategory(productCategories.getProductAndCategory().get(p.getProductName()));
			return p;
		}).collect(Collectors.toList());
    }

}
