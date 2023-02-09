package com.nguyenhoang.lesson8revision.repository;

import com.nguyenhoang.lesson8revision.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

//JpaSpecificationExecutor: thêm mapping cho page
public interface IAccountRepository extends JpaRepository<Account,Integer>, JpaSpecificationExecutor<Account> {
}
