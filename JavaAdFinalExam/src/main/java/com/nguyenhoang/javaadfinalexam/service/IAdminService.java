package com.nguyenhoang.javaadfinalexam.service;

import com.nguyenhoang.javaadfinalexam.entity.QuanTri;
import com.nguyenhoang.javaadfinalexam.form.QuanTri.CreatingManagerForm;
import com.nguyenhoang.javaadfinalexam.form.QuanTri.FilteringManagerForm;
import com.nguyenhoang.javaadfinalexam.form.QuanTri.UpdatingManagerForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAdminService{
    Page<QuanTri> getAllAdmins(Pageable pageable, String search, FilteringManagerForm form);

    void createAdmin(CreatingManagerForm form);

    void updateAdmin(UpdatingManagerForm form);

}
