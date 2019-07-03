package com.ssa.service;

import java.util.List;

import com.ssa.model.SSNUser;
import com.ssa.model.State;
/**
 * User Service for Accessing USER_MASTER
 * @author VISHAL
 *
 */
public interface SSNUserService {
	/**
	 * Stores user data
	 * @param userModel
	 * @return
	 */
	Integer registerUser(SSNUser userModel);

	/**
	 * Get all users
	 * @return
	 */
	List<SSNUser> getAllUsers();
	/**
	 * State name as per SSN
	 * @param ssn
	 * @return
	 */
	String getUserStateName(Integer ssn);
}
