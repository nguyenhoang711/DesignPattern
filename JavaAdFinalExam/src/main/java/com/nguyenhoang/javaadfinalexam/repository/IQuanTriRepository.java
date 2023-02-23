package com.nguyenhoang.javaadfinalexam.repository;

import com.nguyenhoang.javaadfinalexam.entity.QuanTri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IQuanTriRepository extends JpaRepository<QuanTri,Integer>, JpaSpecificationExecutor<QuanTri> {

}
