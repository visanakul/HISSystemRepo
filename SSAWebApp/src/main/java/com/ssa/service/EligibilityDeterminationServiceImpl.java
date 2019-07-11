package com.ssa.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ssa.controller.SSNController;
import com.ssa.ed.input.EligibilityDetermination;
import com.ssa.ed.input.EligibilityDetermination.PlanDetails;
import com.ssa.ed.input.EligibilityDetermination.PlanDetails.SnapPlanData;
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
			String className = "com.ssa.ed.input.EligibilityDetermination$PlanDetails$" + planName + "PlanData";
			Class clz=Class.forName(className);
			IEDRules rules = (IEDRules) clz.newInstance();
			LOGGER.info("Object created : " + rules.toString());
			Method method=clz.getDeclaredMethod("executeRules", EligibilityDetermination.class);
			LOGGER.info("Method created : " + method.toString());
			planInfo=(PlanInfo) method.invoke(rules, eligibilityDetermination);
			LOGGER.info("Method Invoked : " + planInfo.toString());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return planInfo;
	}

}
