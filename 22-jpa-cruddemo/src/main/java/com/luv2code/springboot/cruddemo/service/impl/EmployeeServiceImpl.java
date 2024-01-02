package com.luv2code.springboot.cruddemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDAO;

	@Autowired
	public EmployeeServiceImpl(@Qualifier("employeeDAOJpaImpl") EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	@Override
	@Transactional
	public List<Employee> getEmployees() {
		return employeeDAO.findAll();
	}

	@Override
	@Transactional
	public Employee getEmployee(int id) {
		return employeeDAO.findById(id);
	}

	@Override
	@Transactional
	public void saveEmployee(Employee employee) {
		employeeDAO.save(employee);
	}

	@Override
	@Transactional
	public void deleteEmployee(int id) {
		employeeDAO.deleteById(id);
	}

}
