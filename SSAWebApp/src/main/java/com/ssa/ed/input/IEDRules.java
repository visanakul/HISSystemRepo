package com.ssa.ed.input;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

import com.ssa.ed.output.PlanInfo;

public interface IEDRules {
	static PlanInfo executeRules(EligibilityDetermination eligibilityDetermination) {
		KieServices ks = KieServices.Factory.get();
		KieRepository kr = ks.getRepository();
		KieFileSystem kfs = ks.newKieFileSystem();

		String planName=eligibilityDetermination.getCitigenData().getPlanSelected();
		kfs.write(ResourceFactory.newClassPathResource("rules/"+planName+"Rules.drl", IEDRules.class));

		KieBuilder kb = ks.newKieBuilder(kfs);

		kb.buildAll(); // kieModule is automatically deployed to KieRepository if successfully built.
		if (kb.getResults().hasMessages(Message.Level.ERROR)) {
			throw new RuntimeException("Build Errors:\n" + kb.getResults().toString());
		}

		KieContainer kContainer = ks.newKieContainer(kr.getDefaultReleaseId());

		KieSession kSession = kContainer.newKieSession();
		
		IEDRules rules=getExistingPlanObject(eligibilityDetermination);
		
		System.out.println("Inserting "+rules);
		kSession.insert(rules);

		System.out.println("Fire All Rules...");
		kSession.fireAllRules();
		kSession.dispose();
		PlanInfo planInfo=rules.getPlanInfo();
		planInfo.setCaseNum(eligibilityDetermination.getCitigenData().getCaseNum());
		System.out.println("PLANInfo : "+planInfo);
		return planInfo;
	}
	
	static IEDRules getExistingPlanObject(EligibilityDetermination eligibilityDetermination) {
		if(eligibilityDetermination.getPlanDetails().getSnapPlanData()!=null)
		return eligibilityDetermination.getPlanDetails().getSnapPlanData();
		else if(eligibilityDetermination.getPlanDetails().getCcapPlanData()!=null)
			return eligibilityDetermination.getPlanDetails().getCcapPlanData();
		else
			return eligibilityDetermination.getPlanDetails().getMedicaidPlanData();
	}

	PlanInfo getPlanInfo();
}
