package com.ssa.state.service;

import java.util.List;

import com.ssa.state.model.AccountModel;

public interface IAccountService {
	boolean addAccount(AccountModel accountModel);
	List<AccountModel> getAllAccounts();
	boolean checkEmail(String email);
}
