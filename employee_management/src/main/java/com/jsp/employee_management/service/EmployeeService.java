package com.jsp.employee_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.jsp.employee_management.dao.EmployeeDao;
import com.jsp.employee_management.dto.Employee;
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
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(Employee e) throws MessagingException {
		ResponseStructure<Employee> rs=new ResponseStructure<Employee>();
		rs.setStatuscode(HttpStatus.CREATED.value());
		rs.setData(dao.saveEmployee(e));
		rs.setMsg("Employee save sucefully");
	    MimeMessage message = mailSender.createMimeMessage();

	    message.setFrom(new InternetAddress("guruduttabaral2001@gmail.com"));
	    message.setRecipients(MimeMessage.RecipientType.TO, "guruduttabaral2001@gmail.com");
	    message.setSubject("Login Sucessful");

	    String htmlContent = "<h1>Hii,</h1>" +
	                         "<p>Sir?Madam Your Login Sucessful.</p>";
	    message.setContent(htmlContent, "text/html; charset=utf-8");

	    mailSender.send(message);
		return new ResponseEntity<ResponseStructure<Employee>>(rs,HttpStatus.CREATED);
	}
}
