package com.spring.retail.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.spring.retail.pojo.Bill;
import com.spring.retail.pojo.User;

/**
 * Service interface for declaring the methods related to calculation of discount logic.
 */
@Service
public interface DiscountService {
	
	public BigDecimal getPayableAmount(User user, Bill bill);

}
