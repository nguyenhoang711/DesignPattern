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
    @Override
    public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if(field.equalsIgnoreCase("userName")){
            return criteriaBuilder.like(root.get("userName"),"%" + value.toString() + "%");
        }
        //tạo câu truy vấn với nested attribute
        if(field.equalsIgnoreCase("departmentName")){
            return criteriaBuilder.like(root.get("department").get("name"),"%" + value.toString() + "%");
        }
        return null;
    }
}
