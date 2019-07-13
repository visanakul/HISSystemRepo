package com.ssa.state.model;

import java.util.Date;

import lombok.Data;

@Data
public class AccountModel {
	private Integer accNo;
	private String fname;
	private String lname;
	private String gender;
	private String email;
	private String password;
	private Date dob;
	private Integer ssn;
	private String mobile;
	private String role;
	private boolean active;
}
