package com.application.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.application.api.dto.DepartmentDto;
import com.application.api.entity.Department;
import com.application.api.exception.ResourceNotFoundException;
import com.application.api.mapper.DepartmentMapper;
import com.application.api.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	private DepartmentRepository departmentRepository;
	private DepartmentMapper departmentMapper;

	@Autowired
	public DepartmentService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
		super();
		this.departmentRepository = departmentRepository;
		this.departmentMapper = departmentMapper;
	}
	
	public Department save(DepartmentDto dto) {
		Department department = departmentMapper.dtoToDepartment(dto, new Department());
		return departmentRepository.save(department);
	}
	
	public Department findById(Long id) {
		return departmentRepository.findById(id).orElseThrow(() -> 
			new ResourceNotFoundException(String.format("Department with id %d not found", id)));
	}
	
	public List<Department> findALl() {
		return departmentRepository.findAll();
	}
	
	public Department update(Long id, DepartmentDto dto) {
		Department department = findById(id);
		department = departmentMapper.dtoToDepartment(dto, department);
		return departmentRepository.save(department);
	}
	
	public void delete(Long id) {
		try {
			departmentRepository.deleteById(id);
		}catch(IllegalArgumentException | EmptyResultDataAccessException exp) {
			throw new ResourceNotFoundException(String.format("Department with id %d not found", id));
		}
	}
}
