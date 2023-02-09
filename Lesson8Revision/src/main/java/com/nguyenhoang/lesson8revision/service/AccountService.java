package com.nguyenhoang.lesson8revision.service;

import com.nguyenhoang.lesson8revision.entity.Account;
import com.nguyenhoang.lesson8revision.repository.IAccountRepository;
import com.nguyenhoang.lesson8revision.specification.account.AccountSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService{
    @Autowired
    private IAccountRepository repository;

    @Override
    public Page<Account> getAllAccountsByName(Pageable pageable, String search) {
        Specification<Account> where = AccountSpecification.buildWhere(search);
        return repository.findAll(where,pageable);
    }

    @Override
    public Account getAccountByID(int id) {
        return repository.findById(id).get();
    }
}
