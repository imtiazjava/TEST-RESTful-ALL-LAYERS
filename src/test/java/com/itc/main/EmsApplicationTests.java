package com.itc.main;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itc.main.repository.EmployeeRepository;

@SpringBootTest
class EmsApplicationTests {


@Autowired
private EmployeeRepository employeeRepository;
	


	@Test
	public void testNotNull() {
		assertNotNull(employeeRepository);
	}

}
