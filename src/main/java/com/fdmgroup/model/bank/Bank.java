package com.fdmgroup.model.bank;

import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.model.account.Account;
import com.fdmgroup.model.account.BusinessChecking;
import com.fdmgroup.model.account.BusinessSavings;
import com.fdmgroup.model.account.PersonalChecking;
import com.fdmgroup.model.account.PersonalSavings;
import com.fdmgroup.model.customer.Customer;

/**
 * 
 * Bank class used to manage customers and accounts
 *
 */
public class Bank {
	private List<Account> accounts;
	private List<Customer> customers;

	public Bank() {
		super();
		this.accounts = new ArrayList<Account>();
		this.customers = new ArrayList<Customer>();
	}

	/**
	 * Adds customer to bank's customer list and all of its accounts to bank's
	 * account list if they are not added yet.
	 * 
	 * @param customer
	 */
	public void addCustomer(Customer customer) {

		if (getCustomerByID(customer.getCustomerID()) == null) {
			customers.add(customer);
		}

		for (Account account : customer.getAccounts()) {
			if (getAccountByID(account.getID()) == null) {
				accounts.add(account);
			} else {
				System.out.println("Account already in the list.");
			}
		}

		for (Account account : accounts) {
			if (getCustomerByAccount(account).equals(customer)) {
				try {
					customer.addAccount(account);
				} catch (InstantiationException e) {
					System.out.println(e.getMessage());
				}
			}
			;
		}
	}

	private Object getCustomerByID(int customerID) {
		for (Customer customer : this.customers) {
			if (customer.getCustomerID() == customerID) {
				return customer;
			} else {
				System.out.println("Customer already in the list.");
			}
		}

		return null;
	}

	public Account getAccountByID(int id) {
		for (Account account : this.accounts) {
			if (account.getID() == id) {
				return account;
			}
		}

		return null;
	}

	public void addAccount(Account account) {
		Customer customer = getCustomerByAccount(account);

		if (getAccountByID(account.getID()) == null) {
			accounts.add(account);
		}

		for (Customer entry : customers) {
			if (entry.getCustomerID() == customer.getCustomerID()) {
				try {
					entry.addAccount(account);
				} catch (InstantiationException e) {
					System.out.println(e.getMessage());
					;
				}
			}
		}
	}

	public void removeCustomerByID(int id) {
		int index = -1;

		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getCustomerID() == id) {

				for (Account account : customers.get(i).getAccounts()) {
					accounts.remove(account);
				}

				index = i;
			}
		}

		if (index == -1) {
			System.out.println("ID not found");
			return;
		}

		customers.remove(index);
	}

	public void removeAccountByID(int id) {
		Customer foundCustomer = null;
		Account foundAccount = null;
		int index = -1;

		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getID() == id) {
				foundAccount = accounts.get(i);
				index = i;
				break;
			}
		}

		if (index == -1) {
			System.out.println("ID not found");
			return;
		}

		accounts.remove(index);

		foundCustomer = getCustomerByAccount(foundAccount);

		foundCustomer.getAccounts().remove(foundAccount);
	}

	private Customer getCustomerByAccount(Account account) {
		if (account instanceof BusinessChecking) {
			return ((BusinessChecking) account).getCompany();
		} else if (account instanceof BusinessSavings) {
			return ((BusinessSavings) account).getCompany();
		} else if (account instanceof PersonalSavings) {
			return ((PersonalSavings) account).getPerson();
		} else if (account instanceof PersonalChecking) {
			return ((PersonalChecking) account).getPerson();
		} else {
			return null;
		}
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Bank Database:");
		sb.append("\n");
		sb.append("--------------------------");
		sb.append("\n");
		sb.append("Accounts:");
		sb.append("\n");
		for (Account account : accounts) {
			sb.append(account.toString());
			sb.append("\n");
		}
		sb.append("--------------------------");
		sb.append("\n");
		sb.append("Customers:");
		sb.append("\n");
		for (Customer customer : customers) {
			sb.append(customer.toString());
			sb.append("\n");
		}

		return sb.toString();
	}
}
