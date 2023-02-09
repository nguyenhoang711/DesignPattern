package com.nguyenhoang.lesson8revision.repository;

import com.nguyenhoang.lesson8revision.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

//jpaSpecificationExecutor: mapping mà có paging
public interface IDepartmentRepository extends JpaRepository<Department,Integer>, JpaSpecificationExecutor<Department> {
}
