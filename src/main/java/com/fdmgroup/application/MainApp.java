package com.fdmgroup.application;

import com.fdmgroup.model.account.Account;
import com.fdmgroup.model.account.BusinessChecking;
import com.fdmgroup.model.account.BusinessSavings;
import com.fdmgroup.model.account.PersonalChecking;
import com.fdmgroup.model.account.PersonalSavings;
import com.fdmgroup.model.bank.Bank;
import com.fdmgroup.model.customer.Company;
import com.fdmgroup.model.customer.Person;

public class MainApp {
	public static void main(String[] args) {
		Company company;
		Person person;
		Account businessChecking, businessSavings;
		Account personalChecking, personalSavings;
		
		Double companyBalance = 150000.10;
		String companyName = "FDM";
		String companyAddress = "Adelaide Street";
		int companyTaxID = 20;

		Double personBalance = 1000.50;
		String personName = "Gabriel";
		String personAddress = "Bathurst Street";
		int personTaxID = 10;
		
		company = new Company(companyName, companyAddress, companyTaxID);
		person = new Person(personName, personAddress, personTaxID);
		businessChecking = new BusinessChecking(company, companyBalance);
		businessSavings = new BusinessSavings(company, companyBalance);
		personalChecking = new PersonalChecking(person, personBalance);
		personalSavings = new PersonalSavings(person, personBalance);
		
		Bank bank = new Bank();
		
		bank.addCustomer(company);
		bank.addAccount(businessSavings);
		bank.addAccount(businessChecking);
		bank.addAccount(bank.getAccountByID(1005));
		
		bank.addAccount(personalSavings);
		bank.addAccount(personalChecking);
		bank.addCustomer(person);
		
		System.out.println(bank.toString());
		
		bank.removeAccountByID(1005);
		bank.removeCustomerByID(2000007);
		
		System.out.println();
		System.out.println(bank.toString());
	}
}
