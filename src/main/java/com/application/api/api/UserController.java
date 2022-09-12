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

import com.application.api.security.dto.UserDto;
import com.application.api.security.entity.User;
import com.application.api.security.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {
	
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping()
	public ResponseEntity<User> save(@RequestBody UserDto dto) {
		return new ResponseEntity<User>(userService.save(dto), HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<List<User>> findAll() {
		return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<User> findById(@PathVariable ("id") Long id) {
		return new ResponseEntity<User>(userService.findById(id), HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<User> findById(@PathVariable ("id") Long id, @RequestBody UserDto dto) {
		return new ResponseEntity<User>(userService.update(id, dto), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<User> deleteById(@PathVariable("id") Long id) {
		userService.deleteById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
}
