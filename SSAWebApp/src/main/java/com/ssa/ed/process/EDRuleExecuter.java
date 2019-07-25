package com.ssa.ed.process;

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
import com.ssa.ed.output.PlanInfo;
import com.ssa.exception.DroolFileException;

public class EDRuleExecuter {
	/**
	 * SLF4J Logging
	 */
	static Logger LOGGER = LoggerFactory.getLogger(EDRuleExecuter.class);

	public static PlanInfo executeRules(EligibilityDetermination eligibilityDetermination) {
		LOGGER.info("executeRules Logic start");
		/**
		 * KeySystem loading
		 */

		KieServices ks = KieServices.Factory.get();
		KieRepository kr = ks.getRepository();
		KieFileSystem kfs = ks.newKieFileSystem();

		/**
		 * Getting Selected plan name
		 */
		String planName = null, drlFilePath = null;
		try {
			CitigenData citigenData = eligibilityDetermination.getCitigenData();
			LOGGER.debug("Citizen data : " + citigenData);
			planName = citigenData.getPlanSelected();
			LOGGER.debug("Plan Selected : " + planName);
			drlFilePath = "rules/" + planName + "Rules.drl";
			kfs.write(ResourceFactory.newClassPathResource(drlFilePath, IEDRules.class));
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			ex.printStackTrace();
			throw new DroolFileException(ex.toString());
		}
		KieBuilder kb = ks.newKieBuilder(kfs);

		// kieModule is automatically deployed to KieRepository if successfully built.
		kb.buildAll();

		// If error display
		if (kb.getResults().hasMessages(Message.Level.ERROR)) {
			LOGGER.error("Build Error from " + drlFilePath + " file");
			throw new DroolFileException("Build Errors:\n" + kb.getResults().toString());
		}

		// Getting container and Session
		KieContainer kContainer = ks.newKieContainer(kr.getDefaultReleaseId());
		KieSession kSession = kContainer.newKieSession();

		// Using reflection to get selected plan object
		IEDRules rules = null;
		try {
			// Reflection for calling
			// eligibilityDetermination.getPlanDetails().get$SelectedPlan$PlanData()
			Method method = EligibilityDetermination.PlanDetails.class.getDeclaredMethod("get" + planName + "PlanData");
			rules = (IEDRules) method.invoke(eligibilityDetermination.getPlanDetails());
			LOGGER.debug("Got Object : " + rules);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		// Inserting selected plan object and firing all rules
		LOGGER.debug("Inserting " + rules);
		kSession.insert(rules);

		LOGGER.debug("Fire All Rules...");
		kSession.fireAllRules();
		kSession.dispose();

		// Fetching the PlanInfo set by .drl files
		PlanInfo planInfo = rules.getPlanInfo();
		planInfo.setCaseNum(eligibilityDetermination.getCitigenData().getCaseNum());
		LOGGER.debug("PLANInfo : " + planInfo);
		LOGGER.info("executeRules Logic end");
		return planInfo;

	}
}
