package com.application.api.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.api.security.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
