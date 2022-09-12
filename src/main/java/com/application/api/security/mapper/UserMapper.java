package com.application.api.security.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.application.api.security.dto.UserDto;
import com.application.api.security.entity.Role;
import com.application.api.security.entity.User;
import com.application.api.security.repository.RoleRepository;

@Component
public class UserMapper {
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserMapper(RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		super();
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User dtoToUser(UserDto dto, User user) {
		if(dto.getUserId() != null) {
			user.setUserId(dto.getUserId());
		}
		
		if(dto.getUsername() != null) {
			user.setUsername(dto.getUsername());
		}
		
		if(dto.getPassword() != null) {
			user.setPassword(passwordEncoder.encode(dto.getPassword()));
		}
		
		if(dto.getUserNonExpired() != null) {
			user.setUserNonExpired(dto.getUserNonExpired());
		}
		
		if(dto.getUserNonLocked() != null) {
			user.setUserNonLocked(dto.getUserNonLocked());
		}
		
		if(dto.getUserCredentialsNonExpired() != null) {
			user.setUserCredentialsNonExpired(dto.getUserCredentialsNonExpired());
		}
		
		if(dto.getUserEnabled() != null) {
			user.setUserEnabled(dto.getUserEnabled());
		}
		
		if(dto.getAuthorities().size() > 0) {
			List<Role> roles = roleRepository.findAll().stream()
					.filter(role -> dto.getAuthorities().contains(role.getRole()))
					.toList();

			user.setRoles(roles);
		}
		
		return user;
	}
}
