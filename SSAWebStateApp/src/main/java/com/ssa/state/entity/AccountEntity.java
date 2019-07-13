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
 * Mapping with ACCOUNT_MASTER table
 * @author VISHAL
 *
 */
@Entity
@Table(name = "ACCOUNT_MASTER")
@Data
public class AccountEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACC_SEQ")
    @SequenceGenerator(sequenceName = "acc_seq", initialValue = 1,allocationSize = 1, name = "ACC_SEQ")
	/**
	 * Auto generate account numbers
	 */
	private Integer accNo;
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
	@NotEmpty
	@Size(min = 6,max = 40)
	/**
	 * Store password
	 */
	private String password;
	@NotNull
	@DateTimeFormat(pattern = "DD-MM-YYYY")
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
	@NotEmpty
	/**
	 * Stores role
	 */
	private String role;
	/**
	 * Soft delete status
	 */
	private boolean active;
}
