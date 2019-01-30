package com.fdmgroup.model.account;

import com.fdmgroup.model.customer.Company;

public class BusinessSavings extends Savings {
	private Company company;

	public BusinessSavings(Company company, Double balance) {
		super(balance);
		this.company = company; 
	}
	
	public Company getCompany() {
		return company;
	}
}
