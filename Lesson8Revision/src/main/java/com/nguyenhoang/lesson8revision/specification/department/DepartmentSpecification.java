package com.nguyenhoang.lesson8revision.specification.department;

import com.nguyenhoang.lesson8revision.entity.Department;
import com.nguyenhoang.lesson8revision.form.DepartmentFilterForm;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class DepartmentSpecification {
    @SuppressWarnings("deprecation")
    public static Specification<Department> buildWhere1Arg(String search){
        Specification<Department> where = null;
        if(!StringUtils.isEmpty(search)){
            search =search.trim();
            DepartmentCustomSpecification name = new DepartmentCustomSpecification("name",search);
            DepartmentCustomSpecification userName = new DepartmentCustomSpecification("userName",search);
            where = Specification.where(name).or(userName);
        }
        return where;
    }

    public static Specification<Department> buildWhereMoreArg(String search, DepartmentFilterForm filterForm){
        Specification<Department> where = null;
        if(!StringUtils.isEmpty(search)){
            search = search.trim();
            DepartmentCustomSpecification name = new DepartmentCustomSpecification("name",search);
            where = Specification.where(name);
        }
        //get by createdDate
        if(filterForm != null && filterForm.getCreatedDate() != null){
            //truyen field va value xuong cho customSpecification
            DepartmentCustomSpecification createdDate = new DepartmentCustomSpecification("createdDate",filterForm.getCreatedDate());
            if(where != null){
                where = where.and(createdDate);
            }else where = Specification.where(createdDate);
        }
        //filter by minDate
        if(filterForm != null && filterForm.getMinCreatedDate() != null){
            //truyền field và value xuống cho customSpecification
            DepartmentCustomSpecification minDate = new DepartmentCustomSpecification("minCreatedDate",filterForm.getMinCreatedDate());
            if(where != null){
                where = where.and(minDate);
            }else where = Specification.where(minDate);
        }
        //filter by maxDate
        if(filterForm != null && filterForm.getMaxCreatedDate() != null){
            //truyền field và value xuống cho customSpecification
            DepartmentCustomSpecification maxDate = new DepartmentCustomSpecification("maxCreatedDate",filterForm.getMaxCreatedDate());
            if(where != null){
                where = where.and(maxDate);
            }else where = Specification.where(maxDate);
        }
        return where;
    }

}
