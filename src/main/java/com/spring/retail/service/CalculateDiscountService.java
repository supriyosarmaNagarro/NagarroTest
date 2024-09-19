package com.spring.retail.service;

import java.math.BigDecimal;

import com.spring.retail.pojo.Bill;
import com.spring.retail.pojo.User;

public interface CalculateDiscountService {
	
	public BigDecimal getDiscount(User user, Bill bill);

}
