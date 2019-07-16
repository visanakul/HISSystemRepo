package com.ssa.state.service;

import com.ssa.state.model.AccountModel;

public interface ISendMailService {
	boolean sendMail(AccountModel accountModel);
}
