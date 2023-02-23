package com.vti.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.entity.Account;
import com.vti.form.account.AccountFilterForm;
import com.vti.form.account.CreatingAccountForm;

public interface IAccountService {

	public Page<Account> getAllAccounts(Pageable pageable, String search, AccountFilterForm filterForm);

	public Account getAccountByID(int id);
	
	public void createAccount(CreatingAccountForm form);
	
	public boolean isAccountExistsByUsername(String username);
}
