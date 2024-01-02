package com.luv2code.springdemo.service;

import java.util.List;

import com.luv2code.springdemo.model.Customer;

public interface CustomerService {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);

	public Customer getCustomer(int id);

	public void deleteCustomer(int customer);

}
