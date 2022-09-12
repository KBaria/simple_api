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

import com.application.api.security.dto.RoleDataDto;
import com.application.api.security.entity.RoleData;
import com.application.api.security.service.RoleDataService;

@RestController
@RequestMapping("api/role-data")
public class RoleDataController {
	
	private RoleDataService roleDataService;
	
	@Autowired
	public RoleDataController(RoleDataService roleDataService) {
		super();
		this.roleDataService = roleDataService;
	}

	@PostMapping()
	public ResponseEntity<RoleData> save(@RequestBody RoleDataDto dto) {
		return new ResponseEntity<RoleData>(roleDataService.save(dto), HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<RoleData> findById(@PathVariable ("id") Long id) {
		return new ResponseEntity<RoleData>(roleDataService.findById(id), HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<List<RoleData>> findAll() {
		return new ResponseEntity<List<RoleData>>(roleDataService.findAll(), HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<RoleData> update(@PathVariable ("id") Long id, @RequestBody RoleDataDto dto) {
		return new ResponseEntity<RoleData>(roleDataService.update(id, dto), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<RoleData> deleteById(@PathVariable ("id") Long id) {
		roleDataService.deleteByid(id);
		return new ResponseEntity<RoleData>(HttpStatus.NO_CONTENT);
	}
	
}
