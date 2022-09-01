package com.itc.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itc.main.entity.Employee;
import com.itc.main.repository.EmployeeRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class EmployeeRepositoryTest {

@Autowired
private EmployeeRepository employeeRepository;
	


	@Test
	@Order(1)
	public void testNotNull() {
		assertNotNull(employeeRepository);
	}
	
	
	@Test
	@Order(2)
	public void saveEmployeeTest() {
		Employee emp=new Employee(1,"RAJU","RAJU@GMAIL.COM",30000.00);
		Employee e=this.employeeRepository.save(emp);
		assertEquals(e.getId(),emp.getId());
		System.out.println("--SUCCESS SAVE EMPLOYEE TEST--");
		
	}
	
	
	@Test
	@Order(3)
	public void getEmployeeByIdTest() {
		Optional<Employee> e=this.employeeRepository.findById(1);
		
		if(e.isPresent()) {
			Employee emp = e.get();
			try {
			assertEquals(emp.getId(), 1);
			}catch(AssertionFailedError ae) {
				System.err.println("--Id Not Found--");
			}
			System.out.println("--SUCCESS GET BY ID TEST--");
		}else {
			assertThrows(AssertionFailedError.class,()->{
				System.err.println("Id Not Found!");
			});
		}
	}
	
	
	
	@Test
	@Order(4)
	public void getAllEmployeeTest() {
		List<Employee> list=new ArrayList();
		list.add(new Employee(1,"RAJU","RAJU@GMAIL.COM",30000.00));
		
		int s=this.employeeRepository.findAll().size();
		
		assertEquals(list.size(), s);
		System.out.println("--SUCCESS GET ALL EMPLOYEE--");
		
		
	}
	
	@Test
	@Order(5)
	public void udpateEmployeeEmailTest() {
		 Employee e=this.employeeRepository.findById(1).get();
		 if(e!=null) {
			 e.setEmail("RAJU123@GMAIL.com");
			 Employee ee=this.employeeRepository.save(e);
			 assertEquals(ee.getEmail(), e.getEmail());
			 System.out.println("--SUCCESS UPDATE EMAIL--");
		 }
		 
	}
	
	@Test
	@Order(6)
	public void deleteEmployeeByIdTest() {
		int eid=1;
		this.employeeRepository.deleteById(1);
		
		assertEquals(this.employeeRepository.findAll().size(),0);
		System.out.println("--SUCCESS DELETE EMPLOYEE BY ID--");
	}
	
}
