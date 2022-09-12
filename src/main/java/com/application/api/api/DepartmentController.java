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

import com.application.api.dto.DepartmentDto;
import com.application.api.entity.Department;
import com.application.api.service.DepartmentService;

@RestController
@RequestMapping("api/department")
public class DepartmentController {
	
	private DepartmentService departmentService;
	
	@Autowired
	public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	};
	
	@PostMapping()
	public ResponseEntity<Department> save(@RequestBody DepartmentDto dto) {
		return new ResponseEntity<Department>(departmentService.save(dto), HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Department> findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(departmentService.findById(id));
	}
	
	@GetMapping()
	public ResponseEntity<List<Department>> findAll() {
		return ResponseEntity.ok(departmentService.findALl());
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Department> update(@PathVariable Long id, @RequestBody DepartmentDto dto) {
		return ResponseEntity.ok(departmentService.update(id, dto));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Department> deleteById(@PathVariable Long id) {
		departmentService.delete(id);
		return new ResponseEntity<Department>(HttpStatus.NO_CONTENT);
	}
	
}
