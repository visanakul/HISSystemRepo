package com.ssa.exception;
/**
 * State data for User
 * @author VISHAL
 *
 */
public class StateNotForUserException extends RuntimeException {
	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 * @param errMsg
	 */
	public StateNotForUserException(String errMsg) {
		super(errMsg);
	}
}
