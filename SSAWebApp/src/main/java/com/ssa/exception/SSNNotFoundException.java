package com.ssa.exception;

public class SSNNotFoundException extends RuntimeException {
	/**
	 * Serial version 1L
	 */
	private static final long serialVersionUID = 1L;

	public SSNNotFoundException(String msg) {
		super(msg);
	}
}
