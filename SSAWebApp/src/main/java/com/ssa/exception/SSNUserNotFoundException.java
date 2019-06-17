package com.ssa.exception;

public class SSNUserNotFoundException extends RuntimeException {
	/**
	 * Serial version 1L
	 */
	private static final long serialVersionUID = 1L;

	public SSNUserNotFoundException(String msg) {
		super(msg);
	}
}
