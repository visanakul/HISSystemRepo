package com.ssa.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * Entity to work with USER_MASTER table Data
 * @author VISHAL
 *
 */
@Data
@Entity
@Table(name = "USER_MASTER")
public class SSNUserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SSN_SEQ")
    @SequenceGenerator(sequenceName = "ssn_seq", initialValue = 100000000,allocationSize = 1, name = "SSN_SEQ")
	/**
	 * Store SSN
	 */
	private Integer ssn;
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
	@NotNull
	@DateTimeFormat(pattern = "DD-MM-YYYY")
	/**
	 * Store Date of birth
	 */
	private Date dob;
	@NotEmpty
	/**
	 * Store Gender
	 */
	private String gender;
	@Digits(integer = 10,fraction = 0,message = "10 Digit mobile number")
	/**
	 * Store Phone number
	 */
	private Long phone;
	/**
	 * Store state
	 */
	private String state;
//	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "creationDate",updatable = false)
	/**
	 * Store creation Date
	 */
	private Date creationDate;
	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	/**
	 * Store update Date
	 */
	private Date updateDate;
	@Column(columnDefinition = "LONG")
	@Basic(fetch=FetchType.LAZY)
	/**
	 * Store Image in Base64 String format
	 */
	private String photo;
}
