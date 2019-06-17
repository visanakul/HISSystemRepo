package com.ssa.exception;

public class NoUserException extends RuntimeException {
	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;

	public NoUserException(String errMsg) {
		super(errMsg);
	}
}
