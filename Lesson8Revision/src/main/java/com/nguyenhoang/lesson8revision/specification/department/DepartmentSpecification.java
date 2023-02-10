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

        //filter by minYear
        if(filterForm != null && filterForm.getMinYear() != null){
            //truyen field va value xuong cho customSpecification
            DepartmentCustomSpecification minYear = new DepartmentCustomSpecification("minYear", filterForm.getMinYear());
            if(where == null) where = Specification.where(minYear);
            else where = where.and(minYear);
        }

        //filter by maxYear
        if(filterForm != null && filterForm.getMaxYear() != null){
            DepartmentCustomSpecification maxYear = new DepartmentCustomSpecification("maxYear",filterForm.getMaxYear());
            if(where != null) where = where.and(maxYear);
            else where = Specification.where(maxYear);
        }
        //filter by min members number
        if(filterForm != null && filterForm.getMinMembers() != null){
            DepartmentCustomSpecification minMembers= new DepartmentCustomSpecification("minMembers",filterForm.getMinMembers());
            if(where != null) where = where.and(minMembers);
            else where = Specification.where(minMembers);
        }

        //filter by max members number
        if(filterForm != null && filterForm.getMaxMembers() != null){
            DepartmentCustomSpecification maxMembers = new DepartmentCustomSpecification("maxMembers",filterForm.getMaxMembers());
            if(where != null) where = where.and(maxMembers);
            else where = Specification.where(maxMembers);
        }

        //filter by min accounts number
        if(filterForm != null && filterForm.getMinAccounts() != null){
            DepartmentCustomSpecification minAccounts = new DepartmentCustomSpecification("minAccounts",filterForm.getMinAccounts());
            if(where != null) where = where.and(minAccounts);
            else where = Specification.where(minAccounts);
        }
        return where;
    }

}
