package com.ssa.exception;
/**
 * Class for User registration exception
 * @author VISHAL
 *
 */

public class RegisterException extends RuntimeException {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 * @param errMsg
	 */
	public RegisterException(String errMsg) {
		super(errMsg);
	}
}
