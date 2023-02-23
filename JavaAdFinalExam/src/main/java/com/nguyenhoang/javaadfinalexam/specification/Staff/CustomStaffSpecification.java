package com.nguyenhoang.javaadfinalexam.specification.Staff;

import com.nguyenhoang.javaadfinalexam.entity.Staff;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


@SuppressWarnings({"serial","rawtypes","unchecked"})
@RequiredArgsConstructor
public class CustomStaffSpecification implements Specification<Staff> {
    @NonNull
    private String field;

    @NonNull
    private Object value;
    @Override
    public Predicate toPredicate(Root<Staff> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        if (field.equalsIgnoreCase("fullName")) {
            return criteriaBuilder.like(root.get("fullName"),"%" + value + "%");
        }

        if(field.equalsIgnoreCase("username")){
            return criteriaBuilder.like(root.get("username"), "%" + value + "%");
        }

        if(field.equalsIgnoreCase("gender")){
            return criteriaBuilder.like(root.get("gender"), value.toString());
        }

        if(field.equalsIgnoreCase("role")){
            return criteriaBuilder.like(root.get("role"), value.toString());
        }
        return null;
    }
}
