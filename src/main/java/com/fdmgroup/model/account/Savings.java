package com.fdmgroup.model.account;

import com.fdmgroup.exception.NotEnoughFundsException;

public class Savings extends Account {
	private Double interestRate;
	
	public Savings(Double balance) {
		super(balance);
		this.interestRate = 0.0; 
	}
	
	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}
	
	public Double getInterestRate() {
		return interestRate;
	}

	@Override
	public void withdraw(Double amount) throws NotEnoughFundsException {
		if(this.balance - amount < 0){
			throw new NotEnoughFundsException("Not enough funds in savings account");
		}
		
		this.balance -= amount;
	}

}
