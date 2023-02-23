package com.nguyenhoang.javaadfinalexam.specification.PhiCong;


import com.nguyenhoang.javaadfinalexam.entity.PhiCong;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@SuppressWarnings({"serial","rawtypes","unchecked"})
@RequiredArgsConstructor
public class CustomPilotSpecification implements Specification<PhiCong> {

    @NonNull
    private String field;

    @NonNull
    private Object value;
    @Override
    public Predicate toPredicate(Root<PhiCong> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if(field.equalsIgnoreCase("fullName")){
            return criteriaBuilder.like(root.get("fullName"),"%" + value.toString() + "%");
        }

        //filter by gender
        if(field.equalsIgnoreCase("gender")){
            return criteriaBuilder.like(root.get("gender"),value.toString());
        }

        //filter by min Giobay
        if(field.equalsIgnoreCase("minSoGioBay")){
            return criteriaBuilder.greaterThanOrEqualTo(root.get("soGioBay"),(Integer) value);
        }

        //filter by max Gio Bay
        if(field.equalsIgnoreCase("maxSoGiobay")){
            return criteriaBuilder.lessThanOrEqualTo(root.get("soGioBay"), (Integer) value);
        }
        return null;
    }
}
