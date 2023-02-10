package com.nguyenhoang.lesson8revision.specification.account;

import com.nguyenhoang.lesson8revision.entity.Account;
import com.nguyenhoang.lesson8revision.form.AccountFilterForm;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class AccountSpecification {

    @SuppressWarnings("deprecation")
    public static Specification<Account> buildWhere(String search, AccountFilterForm filterForm){
        Specification<Account> where = null;
        if(!StringUtils.isEmpty(search)) {
            search = search.trim();
            AccountCustomSpecification name = new AccountCustomSpecification("userName", search);
            //nested attribute: department name
            AccountCustomSpecification departmentName = new AccountCustomSpecification("departmentName",search);
            where = Specification.where(name).or(departmentName);
        }
        if(filterForm != null && filterForm.getDepartmentType() != null){
            AccountCustomSpecification depType = new AccountCustomSpecification("departmentType",filterForm.getDepartmentType());
            if(where == null) where = Specification.where(depType);
            else where = where.and(depType);
        }
        return where;
    }
}
