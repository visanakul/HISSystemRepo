package com.ssa.state.service;

import java.util.List;

import com.ssa.state.model.PlanModel;

/**
 * Service interface for PLAN_MASTER table manipulation
 * @author VISHAL
 *
 */
public interface IPlanService {
	boolean savePlan(PlanModel planModel);
	List<PlanModel> getAllPlans();
	boolean planDeactivateOrActivate(boolean active,Integer id);
}
