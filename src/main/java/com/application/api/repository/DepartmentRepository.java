package com.application.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.api.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
