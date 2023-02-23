package com.nguyenhoang.javaadfinalexam.repository;

import com.nguyenhoang.javaadfinalexam.entity.PhiCong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IPhiCongRepository extends JpaRepository<PhiCong,Integer>, JpaSpecificationExecutor<PhiCong> {
}
