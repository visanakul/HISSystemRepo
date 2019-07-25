package com.ssa.state.model;

import java.util.Date;

import lombok.Data;

@Data
public class CitizenModel {
	private Integer appNo;
	private String fname;
	private String lname;
	private Integer ssn;
	private String email;
	private String gender;
	private Date dob;
	private String mobile;
	private boolean active;
}
