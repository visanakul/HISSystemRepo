package com.ssa.exception;
/**
 * Exception for checking user list in USER_MASTER table
 * @author VISHAL
 *
 */
public class NoUserException extends RuntimeException {
	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default Constructor
	 * @param errMsg
	 */
	public NoUserException(final String errMsg) {
		super(errMsg);
	}
}
