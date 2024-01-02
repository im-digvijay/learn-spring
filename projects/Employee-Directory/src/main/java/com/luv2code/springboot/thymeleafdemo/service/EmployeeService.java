package com.luv2code.springboot.thymeleafdemo.service;

import java.util.List;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;

public interface EmployeeService {

	public List<Employee> getEmployees();

	public Employee getEmployee(int id);

	public void saveEmployee(Employee employee);

	public void deleteEmployee(int id);
	
	public List<Employee> searchBy(String name);

}
