package com.ssa.state.co;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssa.state.exception.BatchProcessException;
import com.ssa.state.model.COBatchModel;
import com.ssa.state.service.ICOBatchService;

import lombok.Data;

@Component
@Data
public class PreProcess implements ICOProcess {
	/**
	 * Slf4j Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PreProcess.class);

	@Autowired(required = true)
	private ICOBatchService coBatchService;

	private COBatchModel coBatchModel;

	private ICOProcess nextProcess;

	@Override
	public void execute() {
		LOGGER.info("Preprocessing start");
		if (coBatchService.saveBatchStartData(coBatchModel)) {
			LOGGER.debug("Batch data saved...");
		} else {
			LOGGER.debug("Batch data not updated...");
			throw new BatchProcessException("Batch not saved...");
		}
		// next();
		LOGGER.info("Preprocessing end");
	}

	@Override
	public void next() {
		System.out.println("Next Process is StartProcess...");
		// nextProcess=new StartProcess();
		nextProcess.execute();
	}

}
