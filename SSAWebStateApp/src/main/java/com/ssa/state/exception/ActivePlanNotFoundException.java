package com.ssa.state.exception;

/**
 * Handles Active Plan not found
 * 
 * @author VISHAL
 *
 */
public class ActivePlanNotFoundException extends RuntimeException {
	/**
	 * Default Serial version
	 */
	private static final long serialVersionUID = 1L;

	public ActivePlanNotFoundException(String errMsg) {
		super(errMsg);
	}
}
