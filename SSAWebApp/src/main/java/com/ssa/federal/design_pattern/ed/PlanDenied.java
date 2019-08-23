package com.ssa.federal.design_pattern.ed;

import lombok.Data;

@Data
public class PlanDenied extends PlanResult{
	protected String denialReason;
}
