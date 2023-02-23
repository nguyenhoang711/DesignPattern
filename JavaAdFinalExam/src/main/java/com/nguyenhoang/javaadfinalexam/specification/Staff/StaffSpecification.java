package com.nguyenhoang.javaadfinalexam.specification.Staff;


import com.nguyenhoang.javaadfinalexam.entity.Staff;
import com.nguyenhoang.javaadfinalexam.form.Staff.FilteringStaffForm;
import com.nguyenhoang.javaadfinalexam.utils.Utils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class StaffSpecification {
    @SuppressWarnings("deprecation")
    public static Specification<Staff> buildWhere(String search, FilteringStaffForm form){
        Specification<Staff> where = null;

        //search by fullName and userName
        if(!StringUtils.isEmpty(search)){
            search = Utils.formatSearch(search);

            CustomStaffSpecification fullName = new CustomStaffSpecification("fullName",search);
            CustomStaffSpecification userName = new CustomStaffSpecification("username", search);
            where =Specification.where(fullName).or(userName);
        }

        //filter by role
        if(form != null && form.getRole() != null){
            CustomStaffSpecification role = new CustomStaffSpecification("role", form.getRole());

            if(where != null) where = where.and(role);
            else where = Specification.where(role);
        }

        //filter by gender
        if(form != null && form.getGender() != null){
            CustomStaffSpecification gender = new CustomStaffSpecification("gender", form.getGender());

            if(where != null) where = where.and(gender);
            else where = Specification.where(gender);
        }
        return where;
    }
}
