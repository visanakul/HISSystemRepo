package com.ssa.federal.design_pattern.ed;

import com.ssa.ed.output.PlanInfo;
import com.ssa.ed.process.IEDRules;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=false)
public class MedicaidPlanData {
	protected String employmentIncome;
	protected String propertiesCost;
	protected String otherIncome;
}
