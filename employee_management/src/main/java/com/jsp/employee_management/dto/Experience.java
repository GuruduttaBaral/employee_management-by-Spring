package com.jsp.employee_management.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
@Component
public class Experience {
	@Id
	private int exprId;
	private String companyName;
	private int yearOfExper;
	private int monthOfExper;
	private String desg;
}
