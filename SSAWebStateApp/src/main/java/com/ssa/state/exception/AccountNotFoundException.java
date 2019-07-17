package com.ssa.state.exception;

/**
 * Handles Account not found
 * 
 * @author VISHAL
 *
 */
public class AccountNotFoundException extends RuntimeException {
	/**
	 * Default Serial version
	 */
	private static final long serialVersionUID = 1L;

	public AccountNotFoundException(String errMsg) {
		super(errMsg);
	}
}
