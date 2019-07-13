package com.ssa.state.exception;

/**
 * Checks if no account exists
 * @author VISHAL
 *
 */
public class EmptyAccountException extends RuntimeException {
	

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;

	public EmptyAccountException(String errMsg) {
		super(errMsg);
	}
}
