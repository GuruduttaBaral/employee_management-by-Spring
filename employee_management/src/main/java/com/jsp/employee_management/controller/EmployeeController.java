package com.jsp.employee_management.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.employee_management.dao.EmployeeDao;
import com.jsp.employee_management.dto.Employee;
import com.jsp.employee_management.dto.LoginRequest;
import com.jsp.employee_management.service.EmployeeService;
import com.jsp.employee_management.util.ResponseStructure;

import jakarta.mail.MessagingException;

@RestController
public class EmployeeController {
	@Autowired
	EmployeeService service;
	@Autowired
	EmployeeDao dao;
	@PostMapping("/saveEmployee")
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(@RequestBody Employee e) throws MessagingException{
		return service.saveEmployee(e);
	}
	@PostMapping("/loginEmployee")
	public String login(@RequestBody LoginRequest req) {
		boolean isAuthinticate = service.authenticate(req.getEmail(), req.getPwd());
		if(isAuthinticate) {
			return "Login sucessful";
		}
		else {
			return "invalid email or pwd";
		}
	}
	@GetMapping("/fetchAll")
	public List<Employee> getAllUsers(){
		return service.findAllEmployee();
	}
	@DeleteMapping("/deleteEmployee")
	public void deleteEmployeeById(@RequestParam int id) {
		dao.deleteById(id);
	}
	@PostMapping("/updateImage")
	public Employee updateImage(@RequestParam int id,@RequestParam("image") MultipartFile image) {
		byte[] imageBytes;
		try {
			imageBytes = image.getBytes();
			return service.updateEmpImage(id, imageBytes);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
