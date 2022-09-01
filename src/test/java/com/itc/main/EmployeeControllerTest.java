package com.itc.main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.awt.PageAttributes.MediaType;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itc.main.controller.EmployeeController;
import com.itc.main.entity.Employee;
import com.itc.main.service.EmployeeService;

@WebMvcTest(value = EmployeeController.class)
class EmployeeControllerTest {

	@Autowired
	private MockMvc mvc;
	 @MockBean
	 private EmployeeService employeeService;

	 @Test
	 public void testNotNull() {
		 assertNotNull(employeeService);
		 assertNotNull(mvc);
	 }
	 
	 @Test
	 public void saveEmployee() throws Exception {
		 Employee emp=new Employee();
		 emp.setId(1);
		 emp.setName("RAJU");
		 emp.setEmail("RAJU@GMAIL.COM");
		 emp.setSalary(3000.00);
		 
		 when(this.employeeService.saveEmployee(Mockito.any(Employee.class)))
		 .thenReturn(emp);
		 
		 String uri="/api/save";
		 String expected=this.mapToJson(emp);
		
		 RequestBuilder rb= MockMvcRequestBuilders
		 .post(uri)
		  .accept(org.springframework.http.MediaType.APPLICATION_JSON)
		  .content(expected)
		  .contentType(org.springframework.http.MediaType.APPLICATION_JSON);
		 
		MvcResult mv=mvc.perform(rb).andReturn();
		String outputJson = mv.getResponse().getContentAsString();
		
		assertThat(outputJson).isEqualTo(expected);
		
		 
		 
		 
	 }

	 @Test
	 public void testGetEmployeeById() throws Exception {
		 Employee emp=new Employee();
		 emp.setId(1);
		 emp.setName("RAJU");
		 emp.setEmail("RAJU@GMAIL.COM");
		 emp.setSalary(30000.00);
		 
		 when(this.employeeService.getEmployeeById(Mockito.anyInt()))
		 .thenReturn(emp);
		 
		 String URI="/api/employee/1";
		 
		 RequestBuilder rb= MockMvcRequestBuilders
				 .get(URI)
				 .accept(org.springframework.http.MediaType.APPLICATION_JSON);
		 MvcResult mr=mvc.perform(rb).andReturn();
		 
		 String expected=this.mapToJson(emp);
		 String actual=mr.getResponse().getContentAsString();
		 assertThat(actual).isEqualTo(expected);
		 
		 
		 
	 }
	private String mapToJson(Employee emp) throws JsonProcessingException {
		 ObjectMapper om=new ObjectMapper();
		 String s = om.writeValueAsString(emp);
		 
		return s;
	}
	
	
	
}
