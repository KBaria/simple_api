package com.application.api.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.api.dto.EmployeeDto;
import com.application.api.entity.Employee;
import com.application.api.service.EmployeeService;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	};
	
	@PostMapping()
	public ResponseEntity<Employee> save(@RequestBody EmployeeDto dto) {
		return new ResponseEntity<Employee>(employeeService.save(dto), HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Employee> findById(@PathVariable Long id) {
		employeeService.findById(id);
		return ResponseEntity.ok(employeeService.findById(id));
	}
	
	@GetMapping()
	public ResponseEntity<List<Employee>> findAll() {
		return ResponseEntity.ok(employeeService.findALl());
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody EmployeeDto dto) {
		return ResponseEntity.ok(employeeService.update(id, dto));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Employee> deleteById(@PathVariable Long id) {
		employeeService.delete(id);
		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}
}
