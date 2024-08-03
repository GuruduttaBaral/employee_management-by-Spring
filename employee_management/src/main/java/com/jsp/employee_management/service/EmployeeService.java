package com.jsp.employee_management.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.jsp.employee_management.dao.EmployeeDao;
import com.jsp.employee_management.dto.Employee;
import com.jsp.employee_management.repo.EmployeeRepo;
import com.jsp.employee_management.util.ResponseStructure;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmployeeService {
	@Autowired
	JavaMailSender mailSender;
	@Autowired
	EmployeeDao dao;
	@Autowired
	EmployeeRepo repo;
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(Employee e) throws MessagingException {
		ResponseStructure<Employee> rs=new ResponseStructure<Employee>();
		rs.setStatuscode(HttpStatus.CREATED.value());
		rs.setData(dao.saveEmployee(e));
		rs.setMsg("Employee save sucefully");
	    MimeMessage message = mailSender.createMimeMessage();

	    message.setFrom(new InternetAddress("guruduttabaral2001@gmail.com"));
	    message.setRecipients(MimeMessage.RecipientType.TO, e.getEmail());
	    message.setSubject("Login Sucessful");

	    String htmlContent = "<h1>Hii,</h1>" +
	                         "<p>Sir?Madam Your RegiStration Sucessful.</p>";
	    message.setContent(htmlContent, "text/html; charset=utf-8");

	    mailSender.send(message);
		return new ResponseEntity<ResponseStructure<Employee>>(rs,HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<Employee>> findByEmail(String email) {
		ResponseStructure<Employee> rs=new ResponseStructure<Employee>();
		rs.setStatuscode(HttpStatus.CREATED.value());
		rs.setData(repo.findByEmail(email));
		rs.setMsg("Employee save sucefully");
		return new ResponseEntity<ResponseStructure<Employee>>(rs,HttpStatus.CREATED);
	}
	public boolean authenticate(String email,String pwd) {
		Employee e = repo.findByEmail(email);
		if(e!=null) {
			return pwd.equals(e.getPwd());
		}
		return false;
	}
	public List<Employee> findAllEmployee(){
		return repo.findAll();
		
	}
	public Employee updateEmpImage(int id,byte[] image) {
		Employee e = repo.findById(id).orElse(null);
		if(e!=null) {
			e.setImage(image);
			repo.save(e);
		}
		return e;
	}
}
