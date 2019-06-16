package com.ssa.exception;

import lombok.Data;

@Data
public class UserStateException {
	private Integer ssn;
	private String msg;
}
