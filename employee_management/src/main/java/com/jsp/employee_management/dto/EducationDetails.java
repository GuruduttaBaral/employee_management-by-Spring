package com.jsp.employee_management.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
@Component
public class EducationDetails {
	@Id
	private	int edId;
	private String degree;
	private String stream;
	private String yop;
	private int percentage;
	private String collegeName;
}
