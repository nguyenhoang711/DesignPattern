package com.nguyenhoang.javaadfinalexam.specification.TuyenBay;


import com.nguyenhoang.javaadfinalexam.entity.TuyenBay;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@SuppressWarnings("serial")
@RequiredArgsConstructor
public class CustomTuyenBaySpecification implements Specification<TuyenBay> {

    @NonNull
    private String field;

    @NonNull
    private Object value;

    @Override
    public Predicate toPredicate(Root<TuyenBay> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if(field.equalsIgnoreCase("fromCity")){
            return criteriaBuilder.like(root.get("fromCity"),"%" + value.toString() + "%");
        }

        if(field.equalsIgnoreCase("toCity")){
            return criteriaBuilder.like(root.get("toCity"), "%" + value.toString() + "%");
        }

        //filter by min price
        if(field.equalsIgnoreCase("minPrice")){
            return criteriaBuilder.greaterThanOrEqualTo(root.get("price"),(Double) value);
        }

        //filter by max price
        if(field.equalsIgnoreCase("maxPrice")){
            return criteriaBuilder.lessThanOrEqualTo(root.get("price"),(Double) value);
        }
        return null;
    }
}
