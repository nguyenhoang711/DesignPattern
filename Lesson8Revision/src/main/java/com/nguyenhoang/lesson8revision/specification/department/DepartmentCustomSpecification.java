package com.nguyenhoang.lesson8revision.specification.department;

import com.nguyenhoang.lesson8revision.entity.Department;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Date;

@SuppressWarnings({"serial","rawtypes","unchecked"})
@RequiredArgsConstructor
public class DepartmentCustomSpecification implements Specification<Department> {
    @NonNull
    private String field;
    @NonNull
    private Object value;
    @Override
    public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if(field.equalsIgnoreCase("name")){
            return criteriaBuilder.like(root.get("name"),"%" + value.toString() + "%");
        }
        if(field.equalsIgnoreCase("userName")){
            //join voi bảng account để lấy ra thuộc tính của chúng
            Join join = root.join("accounts", JoinType.LEFT);
            return criteriaBuilder.like(join.get("userName"), "%" + value.toString() + "%");
        }

        //tạo câu truy vấn cho min createdDate
        if(field.equalsIgnoreCase("minCreatedDate")){
            return criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate").as(java.sql.Date.class),(Date) value);
        }

        //tạo câu truy vấn cho filter theo max createdDate
        if(field.equalsIgnoreCase("maxCreatedDate")){
            return criteriaBuilder.lessThanOrEqualTo(root.get("createdDate").as(java.sql.Date.class),(Date) value);
        }

        //tạo câu truy vấn tìm kiếm theo createdDate
        if(field.equalsIgnoreCase("createdDate")){
            return criteriaBuilder.equal(root.get("createdDate").as(java.sql.Date.class), value);
        }
        return null;
    }
}
