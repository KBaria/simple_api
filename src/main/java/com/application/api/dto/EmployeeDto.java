package com.application.api.dto;

import lombok.Data;

@Data
public class EmployeeDto {
	private Long employeeId;
	private String firstName;
	private String lastName;
	private String title;
	private Long departmentId;
	private String email;
	private String contact;
}
