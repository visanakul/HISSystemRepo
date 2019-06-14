package com.ssa.service;

import com.ssa.model.SSNUser;

public interface SSNUserService {
	Integer registerUser(SSNUser userModel);
	SSNUser getUser(Integer ssn);
}
