package com.ssa.state.exception;

public class EmptyApplicationException extends RuntimeException{

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;

	public EmptyApplicationException(String errMsg) {
		super(errMsg);
	}
}

