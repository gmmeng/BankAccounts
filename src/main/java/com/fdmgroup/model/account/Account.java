package com.fdmgroup.model.account;

import com.fdmgroup.exception.NotEnoughFundsException;

public abstract class Account {
	protected final int idNumber;
	protected Double balance;
	private static int nextID = 1000;
	
	public Account(Double balance) {
		super();
		this.idNumber = nextID;
		nextID += 5;
		this.balance = balance;
	}


	public void deposit(Double amount){
		balance += amount;
	}
	
	public abstract void withdraw(Double amount) throws NotEnoughFundsException;
	
	public void correction(Double amount){
		balance = amount;
	}
	
	public Double checkBalance(){
		return balance;
	}
	
	public int getID(){
		return idNumber;
	}
	
	@Override
	public String toString() {
		return "Account [type=" + getAccountType() + ", " + "idNumber=" + idNumber + ", balance=" + balance + "]";
	}

	private String getAccountType() {
		return this.getClass().toString().split("\\.")[4];
	}

}
