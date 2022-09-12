package com.application.api.mapper;

import org.springframework.stereotype.Component;

import com.application.api.dto.DepartmentDto;
import com.application.api.entity.Department;

@Component
public class DepartmentMapper {
	
	public Department dtoToDepartment(DepartmentDto dto, Department department) {
		
		if(dto.getDepartmentId() != null) {
			department.setDepartmentId(dto.getDepartmentId());
		}
		
		if(dto.getDepartmentName() != null) {
			department.setDepartmentName(dto.getDepartmentName());
		}
		
		return department;
		
	}
	
}
