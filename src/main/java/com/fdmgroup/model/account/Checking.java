package com.fdmgroup.model.account;

public abstract class Checking extends Account {
	private int nextCheck;
	
	public Checking(Double balance) {
		super(balance);
		nextCheck++;
	}

	public void requestCheck(){
		nextCheck++;
	}
	
	public int getNextCheck(){
		return nextCheck;
	}
	
	@Override
	public void withdraw(Double amount) {
		this.balance -= amount;
	}

}
