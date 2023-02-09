package com.nguyenhoang.lesson8revision.service;


import com.nguyenhoang.lesson8revision.entity.Department;
import com.nguyenhoang.lesson8revision.form.DepartmentFilterForm;
import com.nguyenhoang.lesson8revision.repository.IDepartmentRepository;
import com.nguyenhoang.lesson8revision.specification.department.DepartmentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DepartmentService implements IDepartmentService{

    @Autowired
    private IDepartmentRepository repository;
    @Override
    public Page<Department> getAllDepartmentByName(Pageable pageable, String search) {
        Specification<Department> where = DepartmentSpecification.buildWhere1Arg(search);
        return repository.findAll(where,pageable);
    }

    @Override
    public Page<Department> getAllDepartmentByDateRange(Pageable pageable,String search, DepartmentFilterForm filterForm) {
        Specification<Department> where = DepartmentSpecification.buildWhereMoreArg(search,filterForm);
        return repository.findAll(where,pageable);
    }

    @Override
    public Page<Department> getAllDepartmentByDate(Pageable pageable, String search,DepartmentFilterForm filterForm) {
        Specification<Department> where = DepartmentSpecification.buildWhereMoreArg(search,filterForm);
        return repository.findAll(where,pageable);
    }
}
