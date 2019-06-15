package com.ssa.model;

import java.util.Date;

import javax.persistence.Table;

import lombok.Data;

@Data
public class SSNUser {
	private Integer ssn;
	private String fname;
	private String lname;
	private Date dob;
	private String gender;
	private String phone;
	private String state;
	private Date creationDate;
	private Date updateDate;
	private byte[] photo;
}
