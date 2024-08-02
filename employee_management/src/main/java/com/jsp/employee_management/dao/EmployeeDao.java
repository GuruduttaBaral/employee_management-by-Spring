package com.jsp.employee_management.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.employee_management.dto.Employee;
import com.jsp.employee_management.repo.EmployeeRepo;

@Repository
public class EmployeeDao {
	@Autowired
	EmployeeRepo repo;
	public Employee saveEmployee(Employee e) {
		Employee e1 = repo.save(e);
		return e1;
		
	}

}
