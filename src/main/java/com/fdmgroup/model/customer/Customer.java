package com.fdmgroup.model.customer;

import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.model.account.Account;

public abstract class Customer {
	private long customerID;
	private String customerName;
	private String address;
	private long taxID;
	private List<Account> accounts;
	private static long nextID = 2000000;

	public Customer(String customerName, String address, long taxID) {
		super();
		this.customerID = nextID;
		nextID += 7;
		this.customerName = customerName;
		this.address = address;
		this.taxID = taxID;
		accounts = new ArrayList<Account>();
	}

	public long getCustomerID() {
		return customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getTaxID() {
		return taxID;
	}

	public void setTaxID(int taxID) {
		this.taxID = taxID;
	}

	public List<Account> getAccounts() {
		return accounts;
	}
	
	public Account getAccountByID(long iD){
		for (Account account : this.accounts) {
			if(account.getID() == iD){
				return account;
			}
		}
		
		return null;
	}
	
	public abstract void addAccount(Account account) throws InstantiationException;

	@Override
	public String toString() {
		return "Customer [type=" + getCustomerType() + ", " + "customerID=" + customerID + ", customerName=" + customerName + ", address=" + address
				+ ", taxID=" + taxID + ", accounts=" + accounts + "]";
	}

	private String getCustomerType(){
		return this.getClass().toString().split("\\.")[4];
	}
}
