package com.ssa.state.service;

import java.util.List;

import com.ssa.state.model.AccountModel;
import com.ssa.state.model.ForgotPassword;
import com.ssa.state.model.Login;

public interface IAccountService {
	boolean addOrUpdateAccount(AccountModel accountModel);
	List<AccountModel> getAllAccounts();
	boolean checkEmail(String email);
	boolean accountDeactivateOrActivate(boolean active,Integer accNo);
	AccountModel getAccountByAccNo(Integer accNo);
	Login doesUserExist(String email, String password);
	ForgotPassword getDataByEmail(String email);
}
