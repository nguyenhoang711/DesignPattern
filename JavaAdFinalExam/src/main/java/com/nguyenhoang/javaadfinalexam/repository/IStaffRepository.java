package com.nguyenhoang.javaadfinalexam.repository;

import com.nguyenhoang.javaadfinalexam.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IStaffRepository extends JpaRepository<Staff,Integer>, JpaSpecificationExecutor<Staff> {

    Staff findByUsername(String userName);

    @Modifying
    @Transactional
    @Query("DELETE FROM Staff WHERE id IN (:ids)")
    void deleteStaffs(@Param("ids") List<Integer> ids);

    boolean existsByUsername(String userName);
}
