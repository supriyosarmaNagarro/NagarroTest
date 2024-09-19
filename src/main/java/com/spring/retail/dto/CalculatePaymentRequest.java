package com.spring.retail.dto;

import com.spring.retail.pojo.Bill;
import com.spring.retail.pojo.User;

public class CalculatePaymentRequest {
	
	private User user;
	
	private Bill bill;

	public CalculatePaymentRequest() {
		super();
	}

	public CalculatePaymentRequest(User user, Bill bill) {
		super();
		this.user = user;
		this.bill = bill;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	@Override
	public String toString() {
		return "CalculatePaymentRequest [user=" + user + ", bill=" + bill + "]";
	}

}
