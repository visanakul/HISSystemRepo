package com.ssa.state.model;

import lombok.Data;

@Data
public class CitizenPlanModel {
	private String caseNo;
	private Integer appNo;
	private Integer planId;
	private boolean eligible;
}
