package com.fdmgroup.testCases;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.exception.NotEnoughFundsException;
import com.fdmgroup.model.account.Account;
import com.fdmgroup.model.account.BusinessChecking;
import com.fdmgroup.model.account.BusinessSavings;
import com.fdmgroup.model.account.PersonalChecking;
import com.fdmgroup.model.account.PersonalSavings;
import com.fdmgroup.model.customer.Company;
import com.fdmgroup.model.customer.Person;

public class BankAccountTests {
	private Company company;
	private Person person;
	private Account businessChecking, businessSavings;
	private Account personalChecking, personalSavings;
	private Double companyBalance = 150000.10;
	private String companyName = "FDM";
	private String companyAddress = "Adelaide Street";
	private int companyTaxID = 20;

	private Double personBalance = 10000.50;
	private String personName = "Gabriel";
	private String personAddress = "Bathurst Street";
	private int personTaxID = 10;
	
	@Mock
	private Person mockPerson;
	
	@Mock
	private Account mockAccount;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		company = new Company(companyName, companyAddress, companyTaxID);
		person = new Person(personName, personAddress, personTaxID);
		businessChecking = new BusinessChecking(company, companyBalance);
		businessSavings = new BusinessSavings(company, companyBalance);
		personalChecking = new PersonalChecking(person, personBalance);
		personalSavings = new PersonalSavings(person, personBalance);
	}

	@Test
	public void test_CheckIfNewAccountIsAddedToCustomer() {
		int companyQuantityBefore = company.getAccounts().size();
		int personQuantityBefore = person.getAccounts().size();
		try {
			company.addAccount(businessChecking);
			company.addAccount(businessSavings);
			person.addAccount(personalChecking);
			person.addAccount(personalSavings);
		} catch (InstantiationException e) {
			System.out.println(e.getMessage());
		}

		assertTrue(companyQuantityBefore < company.getAccounts().size());
		assertTrue(personQuantityBefore < person.getAccounts().size());

		assertEquals(2, company.getAccounts().size());
		assertEquals(2, person.getAccounts().size());
	}

	@Test
	public void test_AssertThatPersonCannotHaveBusinessAccount() {
		boolean exceptionThrown = false;
		try {
			person.addAccount(businessChecking);
		} catch (InstantiationException e) {
			exceptionThrown = true;
		}

		assertTrue(exceptionThrown);
	}

	@Test
	public void test_AssertThatCompanyCannotHavePersonalAccount() {
		boolean exceptionThrown = false;
		try {
			company.addAccount(personalChecking);
		} catch (InstantiationException e) {
			exceptionThrown = true;
		}

		assertTrue(exceptionThrown);
	}

	@Test
	public void test_AssertThatValueWasAddedToAllBusinessAccounts() {
		List<Account> accounts = company.getAccounts();
		Double valueToAdd = 10000.00;
		boolean isDifferent = false;

		company.addToAllAccounts(valueToAdd);

		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i) != company.getAccounts().get(i)) {
				isDifferent = true;
				break;
			}
		}

		assertFalse(isDifferent);
	}

	@Test
	public void test_AssertThatAllPersonalAccountsWereResetedWhenCallingResetAllAccountsMethod() {
		boolean isReseted = false;

		try {
			person.addAccount(personalChecking);
			person.addAccount(personalSavings);
		} catch (InstantiationException e) {
			System.out.println(e.getMessage());
		}

		person.resetAllAccounts();

		for (Account account : person.getAccounts()) {
			if (account.checkBalance() != 0.0) {
				isReseted = false;
				break;
			} else {
				isReseted = true;
			}
		}

		assertTrue(isReseted);
	}
	
	@Test
	public void test_CheckIfValueIsCorrectlySubtractedFromCheckingWhenCallingWithdrawMethod() {
		Double amount = 100.00;

		try {
			person.addAccount(personalChecking);
		} catch (InstantiationException e) {
			System.out.println(e.getMessage());
		}

		long ID = person.getAccounts().get(0).getID();
		Double balanceBefore = person.getAccountByID(ID).checkBalance();

		try {
			person.getAccountByID(ID).withdraw(amount);
		} catch (NotEnoughFundsException e) {
			System.out.println(e.getMessage());
		}

		assertEquals(balanceBefore - amount, person.getAccountByID(ID).checkBalance(), 0.01);
	}

	@Test
	public void test_CheckIfApplicationThrowsExceptionWhenOverdraftingSavingsAccount() {
		Double amount;
		boolean exceptionThrown = false;

		try {
			person.addAccount(personalSavings);
		} catch (InstantiationException e) {
			System.out.println(e.getMessage());
		}

		long ID = person.getAccounts().get(0).getID();
		Double balance = person.getAccountByID(ID).checkBalance();
		amount = balance + 100.00;

		try {
			person.getAccountByID(ID).withdraw(amount);
		} catch (NotEnoughFundsException e) {
			exceptionThrown = true;
		}
		
		assertTrue(exceptionThrown);
	}

	@Test
	public void test_CheckIfValueIsCorrectlyAddedWhenCallingDepositMethod() {
		Double amount = 100.00;

		try {
			person.addAccount(personalSavings);
		} catch (InstantiationException e) {
			System.out.println(e.getMessage());
		}

		long ID = person.getAccounts().get(0).getID();
		Double balanceBefore = person.getAccountByID(ID).checkBalance();

		person.getAccountByID(ID).deposit(amount);

		assertEquals(balanceBefore + amount, person.getAccountByID(ID).checkBalance(), 0.01);
	}

}
