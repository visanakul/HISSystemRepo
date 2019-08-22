package com.ssa.federal.design_pattern;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class SnapPlanData extends PlanData {
	protected String familyIncome;
	protected String isEmployed;
}
