package com.ssa.state.model;

import java.util.Date;

import lombok.Data;

@Data

public class ResourceResponse {
	/**
	 * Store status code
	 */
	private Integer statusCode;
	/**
	 * Store error message
	 */
	private String msg;
	/**
	 * Store date
	 */
	private Date date;
}
