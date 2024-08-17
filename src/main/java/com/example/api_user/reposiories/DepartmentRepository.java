package com.example.api_user.reposiories;

import com.example.api_user.entities.Department;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
