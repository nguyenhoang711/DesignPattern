package com.nguyenhoang.lesson8revision.specification.account;

import com.nguyenhoang.lesson8revision.entity.Account;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class AccountSpecification {

    @SuppressWarnings("deprecation")
    public static Specification<Account> buildWhere(String search){
        Specification<Account> where = null;
        if(!StringUtils.isEmpty(search)) {
            search = search.trim();
            AccountCustomSpecification name = new AccountCustomSpecification("userName", search);
            //nested attribute: department name
            AccountCustomSpecification departmentName = new AccountCustomSpecification("departmentName",search);
            where = Specification.where(name).or(departmentName);
        }
        return where;
    }
}
