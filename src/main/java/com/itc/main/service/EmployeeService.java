package com.itc.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itc.main.entity.Employee;
import com.itc.main.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;


	
	public Employee saveEmployee(Employee employee) {
		return this.employeeRepository.save(employee);
	}
	
	public Employee getEmployeeById(int id) {
		 Employee emp=this.employeeRepository.findById(id).get();
		 return emp;
		 
		
	}
	
	public List<Employee> getAllEmployees(){
		List<Employee> list=this.employeeRepository.findAll();
		return list;
	}
	
	
	public Employee findEmployee(String email)
	{
		return this.employeeRepository.findByEmail(email);
		
	}
	
	
	
	

}
