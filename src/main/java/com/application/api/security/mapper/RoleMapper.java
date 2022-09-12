package com.application.api.security.mapper;

import org.springframework.stereotype.Component;

import com.application.api.security.dto.RoleDto;
import com.application.api.security.entity.Role;

@Component
public class RoleMapper {
	
	public Role dtoToRole(RoleDto dto, Role role) {
		
		if(dto.getRoleId() != null) {
			role.setRoleId(dto.getRoleId());
		}
		
		if(dto.getRole() != null && dto.getRole().startsWith("role.")) {
			role.setRole(dto.getRole());
		} 
		
		return role;
	}
	
}
