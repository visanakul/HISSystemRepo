package com.ssa.state.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.ssa.state.seq_generators.CaseNumberGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "CITIZEN_PLAN_MASTER")
public class CitizenPlanEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "case_seq")
	@GenericGenerator(name = "case_seq", strategy = "com.ssa.state.seq_generators.CaseNumberGenerator", parameters = {
			@Parameter(name = CaseNumberGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CaseNumberGenerator.VALUE_PREFIX_PARAMETER, value = "CASE_"),
			@Parameter(name = CaseNumberGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })

	private String caseNo;
	@NotNull
	private Integer appNo;
	private Integer planId;
	private boolean eligible;
}
