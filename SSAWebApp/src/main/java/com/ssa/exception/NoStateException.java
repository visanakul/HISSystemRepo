package com.ssa.exception;
/**
 * Exception for checking user list in USER_MASTER table
 * @author VISHAL
 *
 */
public class NoStateException extends RuntimeException {
	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default Constructor
	 * @param errMsg
	 */
	public NoStateException(final String errMsg) {
		super(errMsg);
	}
}
