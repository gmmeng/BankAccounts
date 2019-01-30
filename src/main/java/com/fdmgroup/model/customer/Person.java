package com.fdmgroup.model.customer;

import com.fdmgroup.model.account.Account;
import com.fdmgroup.model.account.PersonalChecking;
import com.fdmgroup.model.account.PersonalSavings;

public class Person extends Customer {

	public Person(String customerName, String address, int taxID) {
		super(customerName, address, taxID);
	}
	
	public void resetAllAccounts(){
		for (Account account : this.getAccounts()) {
			account.correction(0.0);
		}
	}
	
	@Override
	public void addAccount(Account account) throws InstantiationException {
		if(account instanceof PersonalChecking || account instanceof PersonalSavings){
			this.getAccounts().add(account);
		}
		
		else{
			throw new InstantiationException("Not an instance of Personal Account");
		}
	}

}
