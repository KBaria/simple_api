package com.application.api.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.application.api.dto.EmployeeDto;
import com.application.api.entity.Department;
import com.application.api.entity.Employee;
import com.application.api.exception.ResourceNotFoundException;
import com.application.api.repository.DepartmentRepository;

@Component
public class EmployeeMapper {
	
	private DepartmentRepository departmentRepository;
	
	@Autowired
	public EmployeeMapper(DepartmentRepository departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;
	}

	public Employee dtoToEmployee(EmployeeDto dto, Employee employee) {
		
		if(dto.getEmployeeId() != null) {
			employee.setEmployeeId(dto.getEmployeeId());
		}
		
		if(dto.getFirstName() != null) {
			employee.setFirstName(dto.getFirstName());
		}
		
		if(dto.getLastName() != null) {
			employee.setLastName(dto.getLastName());
		}
		
		if(dto.getTitle() != null) {
			employee.setTitle(dto.getTitle());
		}
		
		if(dto.getDepartmentId() != null) {
			Department department = departmentRepository.findById(dto.getDepartmentId()).orElseThrow(() -> 
				new ResourceNotFoundException(String.format("Department with id %d not found", dto.getDepartmentId())));
			employee.setDepartment(department);
		}
		
		if(dto.getEmail() != null) {
			employee.setEmail(dto.getEmail());
		}
		
		if(dto.getContact() != null) {
			employee.setContact(dto.getContact());
		}
		
		return employee;
		
	}
	
}
