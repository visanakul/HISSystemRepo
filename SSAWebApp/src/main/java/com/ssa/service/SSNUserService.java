package com.ssa.service;

import java.util.List;

import com.ssa.model.SSNUser;
import com.ssa.model.State;

public interface SSNUserService {
	Integer registerUser(SSNUser userModel);
	State getUserState(Integer ssn);
	List<SSNUser> getAllUsers();
}
