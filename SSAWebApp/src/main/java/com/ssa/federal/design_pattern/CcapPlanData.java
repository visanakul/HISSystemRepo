package com.ssa.federal.design_pattern;

import com.ssa.ed.output.PlanInfo;
import com.ssa.ed.process.IEDRules;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CcapPlanData {
	protected String kidsCount;
	protected String kidsAge;
	protected String parentsEmployed;
	protected String familyIncome;
}