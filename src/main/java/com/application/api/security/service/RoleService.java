package com.application.api.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.application.api.exception.ResourceNotFoundException;
import com.application.api.security.dto.RoleDto;
import com.application.api.security.entity.Role;
import com.application.api.security.mapper.RoleMapper;
import com.application.api.security.repository.RoleRepository;

@Service
public class RoleService {
	
	private RoleRepository roleRepository;
	private RoleMapper roleMapper;
	
	@Autowired
	public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
		super();
		this.roleRepository = roleRepository;
		this.roleMapper = roleMapper;
	}

	public Role save(RoleDto dto) {
		Role role = roleMapper.dtoToRole(dto, new Role());
		return roleRepository.save(role);
	}
	
	public Role findById(Long id) {
		return roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("role with id %d not found", id)));
	}
	
	public List<Role> findAll() {
		return roleRepository.findAll();
	}
	
	public Role update(Long id, RoleDto dto) {
		Role role = findById(id);
		role = roleMapper.dtoToRole(dto, role);
		return roleRepository.save(role);
	}
	
	public void deleteById(Long id) {
		try {
			roleRepository.deleteById(id);
		}catch (IllegalArgumentException | EmptyResultDataAccessException exp) {
			throw new ResourceNotFoundException(String.format("role with id %d not found", id));
		}
	}
	
}
