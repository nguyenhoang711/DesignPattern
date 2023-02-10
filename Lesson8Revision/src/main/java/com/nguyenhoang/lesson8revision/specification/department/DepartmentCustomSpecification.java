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
        //tao cau truy van voi minYear
        if(field.equalsIgnoreCase("minYear")){
            return criteriaBuilder.greaterThanOrEqualTo(
                    criteriaBuilder.function("YEAR",Integer.class, root.get("createdDate")),
                    (Integer) value
            );
        }
        //toa cua truy van voi maxYEar
        if(field.equalsIgnoreCase("maxYear")){
            return criteriaBuilder.lessThanOrEqualTo(
                    criteriaBuilder.function("YEAR",Integer.class, root.get("createdDate")),
                    (Integer) value
            );
        }

        //filter theo tong so luong accounts nho nhat
        if(field.equalsIgnoreCase("minMembers")){
            return criteriaBuilder.greaterThanOrEqualTo(root.get("totalMember"),(Integer) value);
        }

        //filter theo tong so accounts lon nhat
        if(field.equalsIgnoreCase("maxMembers")){
            return criteriaBuilder.lessThanOrEqualTo(root.get("totalMember"),(Integer) value);
        }

        //filter theo tong so luong accounts
        if(field.equalsIgnoreCase("minAccounts")){
            return criteriaBuilder.greaterThanOrEqualTo(
                    criteriaBuilder.count(root.get("accounts")),
                    (Long) value
            );
        }
        return null;
    }
}
