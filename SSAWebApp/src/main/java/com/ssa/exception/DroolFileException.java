package com.ssa.exception;

public class DroolFileException extends RuntimeException {
	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;

	public DroolFileException(String errMsg) {
		super(errMsg);
	}
}
