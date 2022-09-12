package com.application.api.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.api.security.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
