package com.luv2code.springboot.thymeleafdemo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<Employee> getEmployees() {
		return employeeRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public Employee getEmployee(int id) {
		Optional<Employee> result = employeeRepository.findById(id);

		Employee employee = null;
		if (result.isPresent())
			employee = result.get();
		else
			throw new RuntimeException("Did not find employee id - " + id);

		return employee;
	}

	@Override
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployee(int id) {
		employeeRepository.deleteById(id);
	}
	
	@Override
	public List<Employee> searchBy(String name) {
		List<Employee> results = null;
		
		if (name != null && (name.trim().length() > 0))
			results = employeeRepository.findByFirstNameContainsOrLastNameContainsAllIgnoreCase(name, name);
		else
			results = getEmployees();
		
		return results;
	}

}
