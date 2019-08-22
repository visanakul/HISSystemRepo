package com.ssa.federal.design_pattern;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssa.ed.input.EligibilityDetermination;
import com.ssa.ed.input.EligibilityDetermination.CitigenData;
import com.ssa.ed.input.EligibilityDetermination.PlanDetails;
import com.ssa.ed.output.PlanInfo;
import com.ssa.exception.DroolFileException;

import lombok.Data;

@Data
public abstract class PlanData {
	private PlanInfo planInfo;
	/**
	 * SLF4J Logging
	 */
	static Logger LOGGER = LoggerFactory.getLogger(PlanData.class);

	public PlanInfo executeRules(EligibilityDetermination eligibilityDetermination)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException {
		LOGGER.info("executeRules Logic start");
		/**
		 * Getting Selected plan name
		 */
		String planName = null, drlFilePath = null;

		CitigenData citigenData = eligibilityDetermination.getCitigenData();
		LOGGER.debug("Citizen data : " + citigenData);
		planName = citigenData.getPlanSelected();
		LOGGER.debug("Plan Selected : " + planName);
		drlFilePath = "rules/" + planName + "Rules.drl";

		// Using reflection to get selected plan object
		PlanData planData = loadPlanData(planName, eligibilityDetermination.getPlanDetails());
		
		// Can read from yml for changing
		String drlSystemName = "com.ssa.federal.design_pattern.MyKieSystem";

		executeRulesTemplate(drlFilePath, planData, drlSystemName);

		// Fetching the PlanInfo set by .drl files
		PlanInfo planInfo = loadPlanInfo(planData, citigenData);
		LOGGER.debug("PLANInfo : " + planInfo);
		LOGGER.info("executeRules Logic end");
		return planInfo;
	}

	private PlanInfo loadPlanInfo(PlanData planData, CitigenData citigenData) {
		PlanInfo planInfo = planData.getPlanInfo();
		planInfo.setCaseNum(citigenData.getCaseNum());
		return planInfo;
	}

	private void executeRulesTemplate(String drlFilePath, PlanData planData, String drlSystemName)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException {
		Class<?> cl = Class.forName(drlFilePath);
		Constructor<?> cons = cl.getConstructor(String.class, PlanData.class);
		RuleExecutorTemplate template = (RuleExecutorTemplate) cons.newInstance(drlFilePath, planData);
		template.execute();
	}

	private PlanData loadPlanData(String planName, PlanDetails planDetails) {
		PlanData planData = null;
		try {
			// return value=method.invoke(instance,param1,param2,...,paramn);
			// Reflection for calling
			// eligibilityDetermination.getPlanDetails().get$SelectedPlan$PlanData()
			Method method = EligibilityDetermination.PlanDetails.class.getDeclaredMethod("get" + planName + "PlanData");
			planData = (PlanData) method.invoke(planInfo);
			LOGGER.debug("Got Object : " + planData);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return planData;
	}
}
