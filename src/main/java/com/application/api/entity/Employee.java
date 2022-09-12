package com.application.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Entity
@Table(name = "employee")
@Data
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private Long employeeId;
	
	@NotBlank(message = "first name cannot be empty")
	@Column(name = "first_name")
	private String firstName;
	
	@NotBlank(message = "last name cannot be empty")
	@Column(name = "last_name")
	private String lastName;
	
	@NotBlank(message = "title cannot be empty")
	@Column(name = "title")
	private String title;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id", referencedColumnName = "department_id")
	@NotNull(message = "department id cannot be empty")
	private Department department;
	
	@Email(message = "last name cannot be empty")
	@Column(name = "email")
	private String email;
	
	@Pattern(message = "contact invalid", regexp = "^(\\+?\\d{1,3}[- .]?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$")
	@NotBlank(message = "contact cannot be empty")
	@Column(name = "contact")
	private String contact;
}
