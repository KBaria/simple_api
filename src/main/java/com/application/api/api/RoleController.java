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

import com.application.api.security.dto.RoleDto;
import com.application.api.security.entity.Role;
import com.application.api.security.service.RoleService;

@RestController("api/role")
@RequestMapping
public class RoleController {
	
	private RoleService roleService;

	@Autowired
	public RoleController(RoleService roleService) {
		super();
		this.roleService = roleService;
	}
	
	@PostMapping()
	public ResponseEntity<Role> save(@RequestBody RoleDto dto) {
		return new ResponseEntity<Role>(roleService.save(dto), HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Role> findById(@PathVariable ("id") Long id) {
		return new ResponseEntity<Role>(roleService.findById(id), HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<List<Role>> findAll() {
		return new ResponseEntity<List<Role>>(roleService.findAll(), HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Role> update(@PathVariable ("id") Long id, @RequestBody RoleDto dto) {
		return new ResponseEntity<Role>(roleService.update(id, dto), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Role> deleteById(@PathVariable ("id") Long id) {
		roleService.deleteById(id);
		return new ResponseEntity<Role>(HttpStatus.NO_CONTENT);
	}
	
}
