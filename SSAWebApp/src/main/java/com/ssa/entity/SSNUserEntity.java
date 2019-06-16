package com.ssa.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "USER_MASTER")
public class SSNUserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SSN_SEQ")
    @SequenceGenerator(sequenceName = "ssn_seq", initialValue = 100000000,allocationSize = 1, name = "SSN_SEQ")
	private Integer ssn;
	@NotEmpty
	@Size(min = 3,max = 40)
	private String fname;
	@NotEmpty
	@Size(min = 3,max = 40)
	private String lname;
	@NotNull
	@DateTimeFormat(pattern = "DD-MM-YYYY")
	private Date dob;
	@NotEmpty
	private String gender;
	@Digits(integer = 10,fraction = 0,message = "10 Digit mobile number")
	private Long phone;
	private String state;
//	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date creationDate;
	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	private Date updateDate;
	private byte[] photo;
}
