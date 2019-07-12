package com.ssa.ed.input;

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

import com.ssa.controller.SSNController;
import com.ssa.ed.output.PlanInfo;

public interface IEDRules {
	/**
	 * SLF4J Logging
	 */
	Logger LOGGER = LoggerFactory.getLogger(SSNController.class);

	/**
	 * Generalized method to select and execute drl files and returns PlanInfo
	 * @param eligibilityDetermination
	 * @return PlanInfo
	 */
	static PlanInfo executeRules(EligibilityDetermination eligibilityDetermination) {
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
		String planName = eligibilityDetermination.getCitigenData().getPlanSelected();
		String drlFilePath="rules/" + planName + "Rules.drl";
		kfs.write(ResourceFactory.newClassPathResource(drlFilePath, IEDRules.class));

		KieBuilder kb = ks.newKieBuilder(kfs);

		// kieModule is automatically deployed to KieRepository if successfully built.
		kb.buildAll(); 
		//If error display
		if (kb.getResults().hasMessages(Message.Level.ERROR)) {
			LOGGER.error("Build Error from "+drlFilePath+" file");
			throw new RuntimeException("Build Errors:\n" + kb.getResults().toString());
		}

		//Getting container and Session
		KieContainer kContainer = ks.newKieContainer(kr.getDefaultReleaseId());
		KieSession kSession = kContainer.newKieSession();

		//Using reflection to get selected plan object
		IEDRules rules = null;
		try {
			// Reflection for calling eligibilityDetermination.getPlanDetails().get$SelectedPlan$PlanData()
			Method method = EligibilityDetermination.PlanDetails.class.getDeclaredMethod("get" + planName + "PlanData");
			rules = (IEDRules) method.invoke(eligibilityDetermination.getPlanDetails());
			LOGGER.debug("Got Object : "+rules);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		//Inserting selected plan object and firing all rules
		LOGGER.debug("Inserting " + rules);
		kSession.insert(rules);

		LOGGER.debug("Fire All Rules...");
		kSession.fireAllRules();
		kSession.dispose();
		
		//Fetching the PlanInfo set by .drl files
		PlanInfo planInfo = rules.getPlanInfo();
		planInfo.setCaseNum(eligibilityDetermination.getCitigenData().getCaseNum());
		LOGGER.debug("PLANInfo : " + planInfo);
		LOGGER.info("executeRules Logic end");
		return planInfo;
	}

	PlanInfo getPlanInfo();
}