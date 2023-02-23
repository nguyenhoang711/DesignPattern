package com.nguyenhoang.javaadfinalexam.repository;

import com.nguyenhoang.javaadfinalexam.entity.ChuyenBay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IChuyenBayRepository extends JpaRepository<ChuyenBay,Integer>, JpaSpecificationExecutor<ChuyenBay> {


    @Modifying
    @Transactional
    @Query("DELETE FROM ChuyenBay WHERE id IN (:ids)")
    //localhost:8080/api/final/v1/flights?ids=5,2,8
    void deleteChuyenBays(@Param("ids")List<Integer> ids);
}
