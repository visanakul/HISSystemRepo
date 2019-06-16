package com.ssa.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class SSNUser {
	@ApiModelProperty(value="SSN number")
	private Integer ssn;
	@ApiModelProperty(value="First Name")
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
	private String photoString;
}
