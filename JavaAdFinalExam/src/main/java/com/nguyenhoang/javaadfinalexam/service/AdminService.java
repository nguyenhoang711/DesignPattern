package com.nguyenhoang.javaadfinalexam.service;

import com.nguyenhoang.javaadfinalexam.entity.QuanTri;
import com.nguyenhoang.javaadfinalexam.entity.Staff;
import com.nguyenhoang.javaadfinalexam.form.QuanTri.CreatingManagerForm;
import com.nguyenhoang.javaadfinalexam.form.QuanTri.FilteringManagerForm;
import com.nguyenhoang.javaadfinalexam.form.QuanTri.UpdatingManagerForm;
import com.nguyenhoang.javaadfinalexam.repository.IQuanTriRepository;
import com.nguyenhoang.javaadfinalexam.specification.QuanLy.AdminSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IAdminService{

    @Autowired
    private IQuanTriRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<QuanTri> getAllAdmins(Pageable pageable, String search, FilteringManagerForm form) {
        Specification<QuanTri> where = AdminSpecification.buildWhere(search,form);
        return repository.findAll(where,pageable);
    }

    @Override
    public void createAdmin(CreatingManagerForm form) {
        // convert form to entity
        QuanTri manager = modelMapper.map(form, QuanTri.class);

        repository.save(manager);

    }

    @Override
    public void updateAdmin(UpdatingManagerForm form) {
        //convert to entity
        QuanTri manager = modelMapper.map(form,QuanTri.class);
        repository.save(manager);
    }
}
