package com.itc.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itc.main.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>
{
    Employee findByEmail(String email);
    
}
