package com.nguyenhoang.finalexam.service;

import java.util.List;

import com.nguyenhoang.finalexam.entity.Account;
import com.nguyenhoang.finalexam.entity.Department;
import com.nguyenhoang.finalexam.form.account.AccountFilterForm;
import com.nguyenhoang.finalexam.form.account.CreatingAccountForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface IAccountService extends UserDetailsService{

	public Page<Account> getAllAccounts(Pageable pageable, String search, AccountFilterForm filterForm);

	public void createAccount(CreatingAccountForm form);

	public Page<Department> getAllDepartmentsForSearch(Pageable pageable, String search);

	public boolean isAccountExistsByUserName(String username);

	public void deleteAccount(int id);

//	public void deleteAccounts(List<Integer> ids);

	public Account getAccountByUsername(String username);
}
