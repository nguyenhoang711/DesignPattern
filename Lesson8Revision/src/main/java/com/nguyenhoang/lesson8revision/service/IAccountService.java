package com.nguyenhoang.lesson8revision.service;


import com.nguyenhoang.lesson8revision.dto.AccountDTO;
import com.nguyenhoang.lesson8revision.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IAccountService {
    public Page<Account> getAllAccountsByName(Pageable pageable, String search);

    public Account getAccountByID(int id);

}
