package com.nguyenhoang.finalexam.repository;

import com.nguyenhoang.finalexam.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface IDepartmentRepository
		extends JpaRepository<Department, Integer>, JpaSpecificationExecutor<Department> {
}
