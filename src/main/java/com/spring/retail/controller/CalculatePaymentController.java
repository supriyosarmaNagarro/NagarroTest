package com.spring.retail.controller;

import java.math.BigDecimal;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.retail.dto.CalculatePaymentRequest;
import com.spring.retail.service.DiscountService;


@RestController
@RequestMapping("api/v1/payments")
public class CalculatePaymentController {
	
	private DiscountService discountService;
	
	
	public CalculatePaymentController(DiscountService discountService) {
		super();
		this.discountService = discountService;
	}
	
	@PostMapping("/discountedPayment")
	public BigDecimal fetchPayableAmount(@RequestBody CalculatePaymentRequest request) {
		
		BigDecimal amount = new BigDecimal(0);
				
		try {
			
			amount = discountService.getPayableAmount(request.getUser(), request.getBill());

		}catch(Exception e) {
			e.printStackTrace();
		}
		return amount;
	}


}
