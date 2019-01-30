package com.fdmgroup.model.account;

import com.fdmgroup.model.customer.Company;

public class BusinessChecking extends Checking {
	private Company company;
	
	public BusinessChecking(Company company, Double balance) {
		super(balance);
		this.company = company;
	}
	
	public Company getCompany(){
		return company;
	}

}
