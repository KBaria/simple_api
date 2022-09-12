package com.application.api.security.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.application.api.exception.ResourceNotFoundException;
import com.application.api.security.dto.UserDto;
import com.application.api.security.entity.RoleData;
import com.application.api.security.entity.User;
import com.application.api.security.mapper.UserMapper;
import com.application.api.security.repository.RoleDataRepository;
import com.application.api.security.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	private UserMapper userMapper;
	private RoleDataRepository roleDataRepository;
	
	@Autowired
	public UserService(UserRepository userRepository, UserMapper userMapper, RoleDataRepository roleDataRepository) {
		super();
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.roleDataRepository = roleDataRepository;
	}
	
	public User save(UserDto dto) {
		User user = userMapper.dtoToUser(dto, new User());
		User savedUser = userRepository.save(user);
		savedUser.setRoles(user.getRoles());
		savedUser.setRoleData(saveUserRoleData(savedUser));
		return savedUser;
	}
	
	public User findById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("user with id %d not found", id)))
				.processRoles();
	}
	
	public List<User> findAll() {
		return userRepository.findAll()
				.stream()
				.map(User::processRoles)
				.toList();
	}
	
	public List<RoleData> saveUserRoleData(User savedUser) {
		savedUser.processRoleData();
		return roleDataRepository.saveAll(savedUser.getRoleData());
	}
	
	public User update(Long id, UserDto dto) {
		User user = findById(id);
		user = userMapper.dtoToUser(dto, user);
		User updatedUser = userRepository.save(user);
		updatedUser.setRoles(user.getRoles());
		updatedUser.setRoleData(saveUserRoleData(updatedUser));
		return updatedUser;
	}
	
	@Transactional
	public void deleteById(Long id) {
		try {
			roleDataRepository.deleteByUser(findById(id));
			userRepository.deleteById(id);
		}catch (IllegalArgumentException | EmptyResultDataAccessException exp) {
			throw new ResourceNotFoundException(String.format("user with id %d not found", id));
		}
	}
	
}
