package com.fdmgroup.model.customer;

import com.fdmgroup.model.account.Account;
import com.fdmgroup.model.account.BusinessChecking;
import com.fdmgroup.model.account.BusinessSavings;

public class Company extends Customer {

	public Company(String customerName, String address, int taxID) {
		super(customerName, address, taxID);
	}
	
	public void addToAllAccounts(Double amount){
		for (Account account : this.getAccounts()) {
			account.deposit(amount);
		}
	}

	@Override
	public void addAccount(Account account) throws InstantiationException {
		if(account instanceof BusinessChecking || account instanceof BusinessSavings){
			this.getAccounts().add(account);
		}
		
		else{
			throw new InstantiationException("Not an instance of Business Account");
		}
	}



}
