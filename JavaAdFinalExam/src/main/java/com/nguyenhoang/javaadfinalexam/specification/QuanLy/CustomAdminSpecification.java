package com.nguyenhoang.javaadfinalexam.specification.QuanLy;


import com.nguyenhoang.javaadfinalexam.entity.QuanTri;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@SuppressWarnings({"serial","rawtypes","unchecked"})
@RequiredArgsConstructor
public class CustomAdminSpecification implements Specification<QuanTri> {
    @NonNull
    private String field;

    @NonNull
    private Object value;


    @Override
    public Predicate toPredicate(Root<QuanTri> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if(field.equalsIgnoreCase("fullName")){
            return criteriaBuilder.like(root.get("fullName"),"%" +value.toString() + "%");
        }

        //filter by gender
        if(field.equalsIgnoreCase("gender")){
            return criteriaBuilder.like(root.get("gender"), value.toString());
        }

        //filter by min nam KN
        if(field.equalsIgnoreCase("minKN")){
            return criteriaBuilder.greaterThanOrEqualTo(root.get("namKN"),(Integer) value);
        }

        //filter by max nam KN
        if(field.equalsIgnoreCase("maxKN")){
            return criteriaBuilder.lessThanOrEqualTo(root.get("namKN"),(Integer) value);
        }
        return null;
    }
}
