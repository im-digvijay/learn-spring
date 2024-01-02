package com.luv2code.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;

public class AfterFinallyDemoApp {

	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the bean from spring container
		AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		// call methods to find the accounts
		List<Account> accounts = null;
		
		try {
			// add a boolean flag to simulate exceptions
			boolean flag = false;
			accounts = accountDAO.findAccounts(flag);
		} catch(Exception e) {
			System.out.println("\nMain Program: caught expection: " + e);
		}
		
		// display the accounts
		System.out.println("\nMain Program: AfterFinallyDemoApp");
		System.out.println("-----------------------------------");
		
		System.out.println(accounts);
		
		System.out.println("\n");
		
		// close the context
		context.close();
		
	}

}
