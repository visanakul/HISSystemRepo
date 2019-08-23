package com.ssa.federal.design_pattern.ed;

import lombok.Data;
@Data
public class PlanApproved extends PlanResult{
	protected String planStartDate;
	protected String planEndDate;
	protected String benefitAmt;
}
