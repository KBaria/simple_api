package com.application.api.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.application.api.exception.ResourceNotFoundException;
import com.application.api.security.dto.RoleDataDto;
import com.application.api.security.entity.RoleData;
import com.application.api.security.entity.User;
import com.application.api.security.mapper.RoleDataMapper;
import com.application.api.security.repository.RoleDataRepository;
import com.application.api.security.repository.UserRepository;

@Service
public class RoleDataService {
	
	private RoleDataRepository roleDataRepository;
	private RoleDataMapper roleDataMapper;
	private UserRepository userRepository;
	
	@Autowired
	public RoleDataService(RoleDataRepository roleDataRepository, RoleDataMapper roleDataMapper, UserRepository userRepository) {
		super();
		this.roleDataRepository = roleDataRepository;
		this.roleDataMapper = roleDataMapper;
		this.userRepository = userRepository;
	}
	
	public RoleData save(RoleData roleData) {
		return roleDataRepository.save(roleData);
	}
	
	public RoleData save(RoleDataDto dto) {
		RoleData roleData = roleDataMapper.dtoToRoleData(dto, new RoleData());
		return roleDataRepository.save(roleData);
	}
	
	public RoleData findById(Long id) {
		return roleDataRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException(String.format("role data with id %d not found", id)));
	}
	
	public List<RoleData> findAll() {
		return roleDataRepository.findAll();
	}
	
	public List<RoleData> findByUser(User user) {
		return roleDataRepository.findByUser(user);
	}
	
	public List<RoleData> findByUserId(Long id) {
		User user = findUserById(id);
		return findByUser(user);
	}
	
	public RoleData update(Long id, RoleDataDto dto) {
		RoleData roleData = findById(id);
		roleData = roleDataMapper.dtoToRoleData(dto, roleData);
		return roleDataRepository.save(roleData);
	}
	
	public void deleteByid(Long id) {
		try {
			roleDataRepository.deleteById(id);
		}catch(IllegalArgumentException | EmptyResultDataAccessException exp) {
			throw new ResourceNotFoundException(String.format("role data with id %d not found", id));
		}
	}
	
	public void deleteByUser(User user) {
		try {
			roleDataRepository.deleteByUser(user);
		}catch(IllegalArgumentException | EmptyResultDataAccessException exp) {
			throw new ResourceNotFoundException(String.format("role data with user id %d not found", user.getUserId()));
		}
	}
	
	public void deleteByUserId(Long id) {
		User user = findUserById(id);
		deleteByUser(user);
	}
	
	public User findUserById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("user with id %d not found", id)));
	}
	
}
