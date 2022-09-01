package com.itc.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itc.main.entity.Employee;
import com.itc.main.service.EmployeeService;

@RestController
@RequestMapping("/api/")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("save")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		Employee e = this.employeeService.saveEmployee(employee);
		return new ResponseEntity(e, HttpStatus.CREATED);

	}

	@GetMapping("employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) {

		Employee emp = this.employeeService.getEmployeeById(id);
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);

	}
}
