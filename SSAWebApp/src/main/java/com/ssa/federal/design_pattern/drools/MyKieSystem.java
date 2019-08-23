package com.ssa.federal.design_pattern.drools;

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

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MyKieSystem extends RuleExecutorTemplate {
	private KieServices ks;
	private KieRepository kr;
	private KieFileSystem kfs;
	private KieBuilder kb;
	private KieContainer kContainer;
	private KieSession kSession;

	static Logger LOGGER = LoggerFactory.getLogger(MyKieSystem.class);

	public MyKieSystem(String drlFilePath, PlanData planData) {
		super(drlFilePath, planData);
	}

	public void init() {
		ks = KieServices.Factory.get();
		kr = ks.getRepository();
		kfs = ks.newKieFileSystem();
		kfs.write(ResourceFactory.newClassPathResource(drlFilePath, MyKieSystem.class));
		kb = ks.newKieBuilder(kfs);
		kb.buildAll();
	}

	public boolean isValid() {
		// If error display
		boolean flag = kb.getResults().hasMessages(Message.Level.ERROR);
		if (flag) {
			LOGGER.error("Build Error from " + drlFilePath + " file");
			errorMsg = kb.getResults().toString();
		}
		return !flag;
	}

	public void openSession() {
		// Getting container and Session
		kContainer = ks.newKieContainer(kr.getDefaultReleaseId());
		kSession = kContainer.newKieSession();
	}

	public void executeRules() {
		kSession.insert(planData);
		LOGGER.debug("Fire All Rules...");
		kSession.fireAllRules();
	}

	public void closeSession() {
		kSession.dispose();
	}
}
