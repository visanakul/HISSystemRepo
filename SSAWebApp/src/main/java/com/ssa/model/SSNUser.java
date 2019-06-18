package com.ssa.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
/**
 * User Model
 * @author VISHAL
 *
 */
public class SSNUser {
	@ApiModelProperty("SSN number")
	/**
	 * Stores ssn number
	 */
	private Integer ssn;
	@ApiModelProperty("First Name")
	/**
	 * Stores first name
	 */
	private String fname;
	/**
	 * Stores last number
	 */
	private String lname;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	/**
	 * Stores date of birth
	 */
	private Date dob;
	/**
	 * Stores gender
	 */
	private String gender;
	/**
	 * Stores phone number
	 */
	private Long phone;
	/**
	 * Stores state
	 */
	private String state;
	/**
	 * Stores create date
	 */
	private Date creationDate;
	/**
	 * Stores update date
	 */
	private Date updateDate;
	/**
	 * Stores Image in Base64 String
	 */
	private String photo;
}
