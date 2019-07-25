package com.ssa.state.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * Map entity with CITIZEN_MASTER table
 * @author VISHAL
 *
 */
@Entity
@Table(name = "CITIZEN_MASTER")
@Data
public class CitizenEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "app_no_seq")
	@SequenceGenerator(sequenceName = "app_no_seq",allocationSize = 1,initialValue = 1,name = "app_no_seq")
	/**
	 * Auto generate case numbers
	 */
	private Integer appNo;
	@NotEmpty
	@Size(min = 3,max = 40)
	/**
	 * Store First name
	 */
	private String fname;
	@NotEmpty
	@Size(min = 3,max = 40)
	/**
	 * Store Last name
	 */
	private String lname;
	@NotEmpty
	/**
	 * Store Gender
	 */
	private String gender;
	@NotEmpty
	@Email
	/**
	 * Store Email
	 */
	private String email;
	
	@NotNull
	@DateTimeFormat(pattern = "dd-MMM-yyyy")
	/**
	 * Store Date of birth
	 */
	private Date dob;
	@NotNull
	private Integer ssn;
	@Digits(integer = 10,fraction = 0,message = "10 Digit mobile number")
	/**
	 * Store mobile number
	 */
	private String mobile;
	/**
	 * Soft delete status
	 */
	private boolean active;

}
