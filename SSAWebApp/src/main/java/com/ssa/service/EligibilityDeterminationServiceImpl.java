package com.ssa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ssa.controller.SSNController;
import com.ssa.ed.input.EligibilityDetermination;
import com.ssa.ed.input.IEDRules;
import com.ssa.ed.output.PlanInfo;

@Service
public class EligibilityDeterminationServiceImpl implements IEligibilityDeterminationService {
	/**
	 * SLF4J Logging
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SSNController.class);

	public EligibilityDeterminationServiceImpl() {
		LOGGER.info("***EligibilityDeterminationServiceImpl***");
	}

	@Override
	public PlanInfo getPlanInfo(EligibilityDetermination eligibilityDetermination) {
		PlanInfo planInfo = null;
		String planName = eligibilityDetermination.getCitigenData().getPlanSelected();
		LOGGER.info("Plan : " + planName);
		try {
			planInfo=IEDRules.executeRules(eligibilityDetermination);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return planInfo;
	}

}
