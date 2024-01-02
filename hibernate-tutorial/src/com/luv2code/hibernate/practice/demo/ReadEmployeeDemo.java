package com.luv2code.hibernate.practice.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.practice.demo.entity.Employee;

public class ReadEmployeeDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
										.configure("hibernate-practice.cfg.xml")
										.addAnnotatedClass(Employee.class)
										.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// create Date Object
			String dateOfBirthStr = "31/12/1998";
            Date dateOfBirth = DateUtils.parseDate(dateOfBirthStr);
			
			// create an Employee object
			System.out.println("Creating new employee object...");
			Employee employee = new Employee("Joseph", "Devis", "Alpha Testing Inc", dateOfBirth);
			
			// start a transaction
			session.beginTransaction();
			
			// save the employee object
			System.out.println("Saving the employee...");
			System.out.println(employee);
			session.save(employee);
			
			// commit the transaction
			session.getTransaction().commit();
			
			// MY NEW CODE
			
			// find out the employee's id: primary key
			System.out.println("Saved employee. Generated id: " + employee.getId());
			
			// now get a session and start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve employee based on the id: primary key
			System.out.println("\nGetting employee with id: " + employee.getId());
			
			Employee myEmployee = session.get(Employee.class, employee.getId());
			
			System.out.println("Get complete: " + myEmployee);
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} catch(Exception e) {
			
		} finally {
			factory.close();
		}
		
	}

}
