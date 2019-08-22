package com.ssa.state.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssa.state.entity.COBatchEntity;
import com.ssa.state.exception.BatchProcessException;
import com.ssa.state.model.COBatchModel;
import com.ssa.state.repository.ICOBatchRepository;
@Service
public class COBatchServiceImpl implements ICOBatchService {
	/**
	 * Slf4 Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(COBatchServiceImpl.class);

	/**
	 * Injecting COBatch Repository
	 */
	@Autowired(required = true)
	private ICOBatchRepository coBatchRepository;

	@Override
	public boolean saveBatchStartData(COBatchModel coBatchModel) {
		LOGGER.info("saveBatchStartData service start");
		try {
			LOGGER.debug("coBatchModel : " + coBatchModel);
			COBatchEntity coBatchEntity = new COBatchEntity();
			BeanUtils.copyProperties(coBatchModel, coBatchEntity);
			LOGGER.debug("Copied coBatchEntity : " + coBatchEntity);
			coBatchEntity = coBatchRepository.save(coBatchEntity);
			LOGGER.debug("After save coBatchEntity : " + coBatchEntity);
			return coBatchEntity.getBatchId() > 0;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new BatchProcessException(e.getMessage());
		} finally {
			LOGGER.info("saveBatchStartData service end");
		}

	}

	@Override
	public boolean updateBatchEndData(COBatchModel coBatchModel) {
		LOGGER.info("updateBatchEndData service start");
		try {
			LOGGER.debug("coBatchModel : " + coBatchModel);
			COBatchEntity coBatchEntity = new COBatchEntity();
			BeanUtils.copyProperties(coBatchModel, coBatchEntity);
			LOGGER.debug("Copied coBatchEntity : " + coBatchEntity);
			coBatchEntity = coBatchRepository.save(coBatchEntity);
			LOGGER.debug("After update coBatchEntity : " + coBatchEntity);
			return coBatchEntity.getBatchId() > 0;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new BatchProcessException(e.getMessage());
		} finally {
			LOGGER.info("updateBatchEndData service end");
		}
	}

}
