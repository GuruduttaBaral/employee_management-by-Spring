package com.jsp.employee_management.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
@Component
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String fname;
	private String lname;
	private int age;
	private String gender;
	private long phone;
	private String email;
	private	String pwd;
	private	String dob;
	@Lob
	@Column(columnDefinition = "LONGBLOB",length=999)
	private	byte[] image;
	@OneToMany(cascade = CascadeType.ALL)
	private	 List<Experience> l;
	@OneToMany(cascade = CascadeType.ALL)
	private List<EducationDetails> e;
}
