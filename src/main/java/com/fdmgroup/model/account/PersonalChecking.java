package com.fdmgroup.model.account;

import com.fdmgroup.model.account.Checking;
import com.fdmgroup.model.customer.Person;

public class PersonalChecking extends Checking {
	private Person person;
	
	public PersonalChecking(Person person, Double balance) {
		super(balance);
		this.person = person;
	}
	
	public Person getPerson() {
		return person;
	}
}
