package com.spring.retail.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.spring.retail.pojo.Bill;
import com.spring.retail.pojo.User;

@Service
public interface DiscountService {
	
	public BigDecimal getPayableAmount(User user, Bill bill);

}
