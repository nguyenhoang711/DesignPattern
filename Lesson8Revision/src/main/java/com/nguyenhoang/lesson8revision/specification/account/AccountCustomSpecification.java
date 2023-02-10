package com.nguyenhoang.lesson8revision.specification.account;

import com.nguyenhoang.lesson8revision.entity.Account;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@SuppressWarnings("serial")
@RequiredArgsConstructor
public class AccountCustomSpecification implements Specification<Account> {
    @NonNull
    private String field;
    @NonNull
    private Object value;

    @SuppressWarnings({"rawtypes","unchecked"})
    @Override
    public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if(field.equalsIgnoreCase("userName")){
            return criteriaBuilder.like(root.get("userName"),"%" + value + "%");
        }
        //tạo câu truy vấn với nested attribute
        if(field.equalsIgnoreCase("departmentName")){
            return criteriaBuilder.like(root.get("department").get("name"),"%" + value + "%");
        }

        //filter theo department type
        if(field.equalsIgnoreCase("departmentType")){
            return criteriaBuilder.equal(root.get("department").get("type"),value);
        }
        return null;
    }
}
