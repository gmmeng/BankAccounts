package com.fdmgroup.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//import com.fdmgroup.model.account.Account;
//import com.fdmgroup.model.account.BusinessChecking;
//import com.fdmgroup.model.account.BusinessSavings;
//import com.fdmgroup.model.account.PersonalChecking;
//import com.fdmgroup.model.account.PersonalSavings;
//import com.fdmgroup.model.bank.Bank;
//import com.fdmgroup.model.customer.Company;
//import com.fdmgroup.model.customer.Person;

public class MainApp {
	private static final String USERNAME = "trainee1";
	private static final String PASSWORD = "!QAZSE4";
	private static final String URL = "jdbc:oracle:thin:@devopsdb.c7iesyjfe3ry.us-east-1.rds.amazonaws.com:1521:ISSUEDB";
//	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	public static void main(String[] args) {
		
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		createStatement();
		
//		Company company;
//		Person person;
//		Account businessChecking, businessSavings;
//		Account personalChecking, personalSavings;
//		
//		Double companyBalance = 150000.10;
//		String companyName = "FDM";
//		String companyAddress = "Adelaide Street";
//		long companyTaxID = 20;
//
//		Double personBalance = 1000.50;
//		String personName = "Gabriel";
//		String personAddress = "Bathurst Street";
//		long personTaxID = 10;
//		
//		company = new Company(companyName, companyAddress, companyTaxID);
//		person = new Person(personName, personAddress, personTaxID);
//		businessChecking = new BusinessChecking(company, companyBalance);
//		businessSavings = new BusinessSavings(company, companyBalance);
//		personalChecking = new PersonalChecking(person, personBalance);
//		personalSavings = new PersonalSavings(person, personBalance);
//		
//		Bank bank = new Bank();
//		
//		bank.addCustomer(company);
//		bank.addAccount(businessSavings);
//		bank.addAccount(businessChecking);
//		bank.addAccount(bank.getAccountByID(1005));
//		
//		bank.addAccount(personalSavings);
//		bank.addAccount(personalChecking);
//		bank.addCustomer(person);
//		
//		System.out.println(bank.toString());
//		
//		bank.removeAccountByID(1005);
//		bank.removeCustomerByID(2000007);
//		
//		System.out.println();
//		System.out.println(bank.toString());
	}
	
	private static void createStatement() {
		try(	Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement st = conn.createStatement()){
			String query = "CREATE TABLE TBL_TEST(user_id NUMBER(6) PRIMARY KEY, username VARCHAR2(50), pw VARCHAR2(30), "
					+ "firstname VARCHAR2(30), lastname VARCHAR2(30))";
			st.execute(query);
			System.out.println("TBL_USER created successfully..");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
