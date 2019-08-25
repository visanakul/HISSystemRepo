package com.ssa.state.co;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssa.state.exception.BatchProcessException;
import com.ssa.state.model.COBatchModel;
import com.ssa.state.service.ICOBatchService;

import lombok.Data;

@Data
@Component
public class PostProcess implements ICOProcess {
	/**
	 * Slf4j Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PostProcess.class);

	@Autowired(required = true)
	private ICOBatchService coBatchService;

	private COBatchModel coBatchModel;

	@Override
	public void execute() {
		LOGGER.info("PostProcess start");
		LOGGER.debug("COBatchService : {}  COBatchModel : {} ", coBatchService, coBatchModel);
		if (coBatchService.updateBatchEndData(coBatchModel)) {
			LOGGER.debug("Batch data updated...");
		} else {
			LOGGER.debug("Batch data not updated...");
			throw new BatchProcessException("Batch not updated...");
		}
		// next();
		LOGGER.info("PostProcess end");
	}

	@Override
	public void next() {
		LOGGER.info("End of Process");
	}

}
