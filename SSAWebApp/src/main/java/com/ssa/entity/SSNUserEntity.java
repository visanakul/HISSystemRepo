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

import lombok.Data;

@Data
@Entity
@Table(name = "USER_MASTER")
public class SSNUserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SSN_SEQ")
    @SequenceGenerator(sequenceName = "ssn_seq", initialValue = 100000000,allocationSize = 1, name = "SSN_SEQ")
	private Integer ssn;
	private String fname;
	private String lname;
	private Date dob;
	private String gender;
	private String phone;
	private String state;
//	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
	private Date creationDate;
	private Date updateDate;
	private byte[] photo;
}
