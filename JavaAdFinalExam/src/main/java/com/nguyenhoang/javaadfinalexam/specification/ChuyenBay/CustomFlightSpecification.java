package com.nguyenhoang.javaadfinalexam.specification.ChuyenBay;

import com.nguyenhoang.javaadfinalexam.entity.ChuyenBay;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

import java.util.Date;

@SuppressWarnings({"serial","rawtypes","unchecked"})
@RequiredArgsConstructor
public class CustomFlightSpecification implements Specification<ChuyenBay> {

    @NonNull
    private String field;

    @NonNull
    private Object value;

    @Override
    public Predicate toPredicate(Root<ChuyenBay> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        //tim kiem theo ten phi cong
        if(field.equalsIgnoreCase("coTruong")){
            Join join = root.join("phiCong", JoinType.LEFT);
            return criteriaBuilder.like(join.get("fullName"),"%" + value + "%");
        }


        //filter theo min start Time
        if(field.equalsIgnoreCase("minStartTime")){
            return criteriaBuilder.greaterThanOrEqualTo(root.get("startTime").as(java.sql.Date.class),(Date) value);
        }

        //filter theo max startTime
        if(field.equalsIgnoreCase("maxStartTime")){
            return criteriaBuilder.lessThanOrEqualTo(root.get("startTime").as(java.sql.Date.class), (Date) value);
        }
        return null;
    }
}
