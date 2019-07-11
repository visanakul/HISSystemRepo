package com.ssa.service;

import com.ssa.ed.input.EligibilityDetermination;
import com.ssa.ed.output.PlanInfo;

public interface IEligibilityDeterminationService {
	PlanInfo getPlanInfo(EligibilityDetermination eligibilityDetermination);
}
