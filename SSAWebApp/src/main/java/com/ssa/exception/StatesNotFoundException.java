package com.ssa.exception;

public class StatesNotFoundException extends RuntimeException {
	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;

	public StatesNotFoundException(String errMsg) {
		super(errMsg);
	}
}
