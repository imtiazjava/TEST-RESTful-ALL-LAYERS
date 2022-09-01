package com.itc.main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.itc.main.entity.Employee;
import com.itc.main.repository.EmployeeRepository;
import com.itc.main.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {

	@InjectMocks
	private EmployeeService employeeService;
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	/*
	@BeforeEach
	private void setup() {
		MockitoAnnotations.initMocks(this);
	}*/
	
	@Test
	public void getNotNullTest() {
		assertThat(employeeService).isNotNull();
		assertThat(employeeRepository).isNotNull();
	}
	
	
	@Test
	public void testSaveEmployee() {
		//Get the Object
		 Employee emp= new Employee();
		  emp.setId(1);
		  emp.setName("RAJU");
		  emp.setEmail("RAJU@GMAIL.COM");
		  emp.setSalary(30000.00);
		  
		//when , then
		  
		 when(this.employeeRepository.save(emp))
		  .thenReturn(emp);
		
		 Employee e=this.employeeService.saveEmployee(emp);
		 assertEquals(e.getId(), emp.getId());
		 System.out.println("--SUCCESS SAVE EMPLOYEE--");
	}
 
	
	@Test
	public void testEmployeeByEmail() {
		 //Get the Object
		  Employee emp= new Employee();
		  emp.setId(1);
		  emp.setName("RAJU");
		  emp.setEmail("RAJU@GMAIL.COM");
		  emp.setSalary(30000.00);
		  
		  when(this.employeeRepository.findByEmail(anyString()))
		  .thenReturn(emp);
		  
		  Employee e=this.employeeService.findEmployee("RAJU@GMAIL.COM");
		  
		  assertEquals(e.getEmail(), emp.getEmail());
		  System.out.println("--SUCCESS TEST Employee By Email--");
		
	}
	
	
	@Test
	public void testAllEmployees() {
		List<Employee> list=new ArrayList();
		list.add(new Employee(1,"RAJU","RAJU@GMAIL.COM",30000.00));
		
		when(this.employeeRepository.findAll())
		.thenReturn(list);
		
		List<Employee> list1=this.employeeService.getAllEmployees();
		
		assertEquals(list1.size(), list.size());
		System.out.println("--SUCCESS TEST GET ALL EMPLOYEES--");
		
		
		
	}
	
	@Test
	public void testEmployeeById() {
		Employee emp=new Employee();
		emp.setId(1);
		emp.setName("RAJU");
		emp.setEmail("RAJU@GMAIL.COM");
		emp.setSalary(30000.00);
		
		when(this.employeeRepository.getById(1))
		.thenReturn(emp);
		
		Employee e=this.employeeService.getEmployeeById(1);
		assertEquals(e.getId(), emp.getId());
		System.out.println("--SUCCESS TEST GET BY ID--");
		
	}

}
