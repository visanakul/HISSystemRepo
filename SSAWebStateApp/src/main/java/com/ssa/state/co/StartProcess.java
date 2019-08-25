package com.ssa.state.co;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ssa.state.model.COTriggerModel;
import com.ssa.state.service.COTriggerServiceImpl;
import com.ssa.state.service.ICOTriggerService;

import lombok.Data;

@Component
@Data
public class StartProcess implements ICOProcess {

	/**
	 * Slf4j Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(StartProcess.class);

	@Autowired(required = true)
	private ICOTriggerService coTriggerService;

	private ICOProcess nextProcess;
	private List<COTriggerModel> coTriggerModels;
	private Integer bucketSize;
	private Integer bucketNumber;

//	public StartProcess() {
//		this.bucketSize = 5;
//		this.bucketNumber = 0;
//	}
//
//	public StartProcess(int bucketSize, int bucketNumber) {
//		this.bucketSize = bucketSize;
//		this.bucketNumber = bucketNumber;
//		LOGGER.debug("Bucket size : {} and Bucket number : {} ", bucketSize, bucketNumber);
//	}

	@Override
	public void execute() {
		LOGGER.info("StartProcess execute start...");
		LOGGER.debug("Bucket size : {} and Bucket number : {} ", bucketSize, bucketNumber);
		coTriggerModels = coTriggerService.getAllPendingTriggers(bucketSize, bucketNumber);
		LOGGER.info("StartProcess execute end...");
		// next();
	}

	@Override
	public void next() {
		LOGGER.info("StartProcess next start...");
		nextProcess = new Process();
		LOGGER.info("StartProcess next end...");
		nextProcess.execute();
	}
}
