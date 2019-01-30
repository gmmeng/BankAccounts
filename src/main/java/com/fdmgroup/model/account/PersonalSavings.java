package com.fdmgroup.model.account;

import com.fdmgroup.model.customer.Person;

public class PersonalSavings extends Savings {
	private Person person;
	
	public PersonalSavings(Person person, Double balance) {
		super(balance);
		this.person = person;
	}

	public Person getPerson() {
		return person;
	}
}
