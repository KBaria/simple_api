package com.application.api.security.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.application.api.exception.ResourceNotFoundException;
import com.application.api.security.dto.RoleDataDto;
import com.application.api.security.entity.Role;
import com.application.api.security.entity.RoleData;
import com.application.api.security.entity.User;
import com.application.api.security.repository.RoleRepository;
import com.application.api.security.repository.UserRepository;

@Component
public class RoleDataMapper {
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	
	@Autowired
	public RoleDataMapper(UserRepository userRepository, RoleRepository roleRepository) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	public RoleData dtoToRoleData(RoleDataDto dto, RoleData roleData) {
		
		if(dto.getRoleDataId() != null) {
			roleData.setRoleDataId(dto.getRoleDataId());
		}
		
		if(dto.getUserId() != null) {
			User user = userRepository.findById(dto.getUserId())
					.orElseThrow(() -> new ResourceNotFoundException(String.format("user with id %d not found", dto.getUserId())));
			roleData.setUser(user);
		}
		
		if(dto.getRoleId() != null) {
			Role role = roleRepository.findById(dto.getRoleId())
					.orElseThrow(() -> new ResourceNotFoundException(String.format("role with id %d not found", dto.getRoleId())));
			roleData.setRole(role);
		}
		
		return roleData;
	}
}
