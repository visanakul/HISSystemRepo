package com.ssa.exception;

/**
 * Checks User available or not as per SSN in USER_MASTER
 * @author VISHAL
 *
 */
public class SSNUserNotFoundException extends RuntimeException {
	/**
	 * Serial version 1L
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default Constructor
	 * @param msg
	 */
	public SSNUserNotFoundException(final String msg) {
		super(msg);
	}
}
