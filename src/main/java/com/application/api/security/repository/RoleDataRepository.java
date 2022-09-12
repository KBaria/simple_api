package com.application.api.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.api.security.entity.RoleData;
import com.application.api.security.entity.User;

public interface RoleDataRepository extends JpaRepository<RoleData, Long> {
	
	List<RoleData> findByUser(User user);
	
	void deleteByUser(User user);
	
}
