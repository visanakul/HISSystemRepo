package com.ssa.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
public class SSNUser {
	private Integer ssn;
	private String fname;
	private String lname;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dob;
	private String gender;
	private Long phone;
	private String state;
	private Date creationDate;
	private Date updateDate;
	private byte[] photo;
	private String photoFile;
}
