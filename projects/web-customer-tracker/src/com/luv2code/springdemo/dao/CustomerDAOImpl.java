package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.util.SortUtils;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers(int sortField) {
		
		// get the current session
		Session session = sessionFactory.getCurrentSession();
		
		// determine sort field
		String fieldName = null;
		
		switch(sortField) {
			case SortUtils.FIRST_NAME:
				fieldName = "firstName";
				break;
			case SortUtils.LAST_NAME:
				fieldName = "lastName";
				break;
			case SortUtils.EMAIL:
				fieldName = "email";
				break;
			default:
				// if nothing matches the default to sort by lastName
				fieldName = "lastName";
		}
		
		// create a query
		String queryString = "from Customer order by " + fieldName;
		Query<Customer> query = session.createQuery(queryString, Customer.class);
		
		// execute query and get result list
		List<Customer> customers = query.getResultList();
		
		// return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		// get the current session
		Session session = sessionFactory.getCurrentSession();
		
		// save the customer
		session.saveOrUpdate(customer);
		
	}

	@Override
	public Customer getCustomer(int id) {
		
		// get the current session
		Session session = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		Customer customer = session.get(Customer.class, id);
		
		// return the result
		return customer;
	}

	@Override
	public void deleteCustomer(int id) {
		
		// get the current session
		Session session = sessionFactory.getCurrentSession();
		
		// delete customer with primary key
		Customer customer = session.get(Customer.class, id);
		session.delete(customer);
		
		// another approach
		// Query<?> query = session.createQuery("delete from Customer where id=:customerId");
		// query.setParameter("customerId", id);
		// query.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomer(String searchName) {
		
		// get the current session
		Session session = sessionFactory.getCurrentSession();
		
		Query<Customer> query = null;
		
		// only search by name if searchName is not empty
		if (searchName != null && searchName.trim().length() > 0) {
			// search for firstName or lastName... case insensitive
			query = session.createQuery("from Customer where lower(firstName) like :name or lower(lastName) like :name", Customer.class);
			query.setParameter("name", "%" + searchName.toLowerCase() + "%");
		} else {
			// searchName is empty... so just get all the customers
			query = session.createQuery("from Customer order by lastName", Customer.class);
		}
		
		// execute query and get result list
		List<Customer> customers = query.getResultList();
		
		return customers;
	}

}
