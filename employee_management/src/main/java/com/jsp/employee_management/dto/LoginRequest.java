package com.jsp.employee_management.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class LoginRequest {
	private String email;
	private String pwd;
}
