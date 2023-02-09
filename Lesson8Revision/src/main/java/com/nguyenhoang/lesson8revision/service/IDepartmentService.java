package com.nguyenhoang.lesson8revision.service;

import com.nguyenhoang.lesson8revision.entity.Department;
import com.nguyenhoang.lesson8revision.form.DepartmentFilterForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface IDepartmentService {
    public Page<Department> getAllDepartmentByName(Pageable pageable, String search);

    public Page<Department> getAllDepartmentByDateRange(Pageable pageable,String search, DepartmentFilterForm filterForm);

    public Page<Department> getAllDepartmentByDate(Pageable pageable, String search, DepartmentFilterForm filterForm);
}
