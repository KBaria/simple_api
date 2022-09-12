package com.application.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.application.api.dto.EmployeeDto;
import com.application.api.entity.Employee;
import com.application.api.exception.ResourceNotFoundException;
import com.application.api.mapper.EmployeeMapper;
import com.application.api.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;
	private EmployeeMapper employeeMapper;

	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
		super();
		this.employeeRepository = employeeRepository;
		this.employeeMapper = employeeMapper;
	}
	
	public Employee save(EmployeeDto dto) {
		Employee employee = employeeMapper.dtoToEmployee(dto, new Employee());
		return employeeRepository.save(employee);
	}
	
	public Employee findById(Long id) {
		return employeeRepository.findById(id).orElseThrow(() -> 
			new ResourceNotFoundException(String.format("Employee with id %d not found", id)));
	}
	
	public List<Employee> findALl() {
		return employeeRepository.findAll();
	}
	
	public Employee update(Long id, EmployeeDto dto) {
		Employee employee = findById(id);
		employee = employeeMapper.dtoToEmployee(dto, employee);
		return employeeRepository.save(employee);
	}
	
	public void delete(Long id) {
		try {
			employeeRepository.deleteById(id);
		}catch (IllegalArgumentException | EmptyResultDataAccessException exp) {
			throw new ResourceNotFoundException(String.format("Employee with id %d not found", id));
		}
	}
	
}
