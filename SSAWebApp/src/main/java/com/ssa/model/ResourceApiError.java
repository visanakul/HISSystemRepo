package com.ssa.model;

import java.util.Date;

import lombok.Data;

@Data
public class ResourceApiError {
	private Integer statusCode;
	private String errMsg;
	private Date date;
}
