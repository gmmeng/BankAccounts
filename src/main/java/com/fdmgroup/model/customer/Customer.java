package com.fdmgroup.model.customer;

import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.model.account.Account;

public abstract class Customer {
	private int customerID;
	private String customerName;
	private String address;
	private int taxID;
	private List<Account> accounts;
	private static int nextID = 2000000;

	public Customer(String customerName, String address, int taxID) {
		super();
		this.customerID = nextID;
		nextID += 7;
		this.customerName = customerName;
		this.address = address;
		this.taxID = taxID;
		accounts = new ArrayList<Account>();
	}

	public int getCustomerID() {
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

	public int getTaxID() {
		return taxID;
	}

	public void setTaxID(int taxID) {
		this.taxID = taxID;
	}

	public List<Account> getAccounts() {
		return accounts;
	}
	
	public Account getAccountByID(int ID){
		for (Account account : this.accounts) {
			if(account.getID() == ID){
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
