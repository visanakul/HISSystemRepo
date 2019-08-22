package com.ssa.state.exception;

public class BatchProcessException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BatchProcessException(String errMsg) {
		super(errMsg);
	}
}
