package com.ssa.state.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssa.state.entity.COTriggerEntity;
import com.ssa.state.exception.COTriggerException;
import com.ssa.state.model.COTriggerModel;
import com.ssa.state.repository.ICOTriggerRepository;

@Service
public class COTriggerServiceImpl implements ICOTriggerService {
	/**
	 * Slf4j Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(COTriggerServiceImpl.class);

	/**
	 * Injecting COTriggerRepository
	 */
	@Autowired(required = true)
	private ICOTriggerRepository coTriggerRepository;

	/**
	 * Default constructor
	 */
	public COTriggerServiceImpl() {
		LOGGER.info("***COTriggerServiceImpl***");
	}

	@Override
	public List<COTriggerModel> getAllPendingTriggers(Integer totalBucket, Integer bucketNumber) {
		LOGGER.info("getAllPendingTriggers service start");
		try {
			List<COTriggerEntity> coTriggerEntities = coTriggerRepository.findAllPendingTriggers(totalBucket,
					bucketNumber);
			if (coTriggerEntities == null || coTriggerEntities.size() == 0) {
				throw new COTriggerException("No Triggers in db");
			}
			LOGGER.debug("Total coTriggerEntities : " + coTriggerEntities.size());

			List<COTriggerModel> coTriggerModels = new ArrayList<>(coTriggerEntities.size());

			for (COTriggerEntity coTriggerEntity : coTriggerEntities) {
				COTriggerModel coTriggerModel = new COTriggerModel();
				BeanUtils.copyProperties(coTriggerEntity, coTriggerModel);
				coTriggerModels.add(coTriggerModel);
			}

			LOGGER.debug("Total coTriggerModels : " + coTriggerEntities.size());
			return coTriggerModels;

		} catch (Exception ex) {

			LOGGER.error("getAllPendingTriggers exception : " + ex.getMessage());
			ex.printStackTrace();
			throw new COTriggerException(ex.getMessage());

		} finally {
			LOGGER.info("getAllPendingTriggers service end");
		}
	}

	public long countTotalPendingTriggers() {
		return coTriggerRepository.count();
	}

	@Override
	public int updateTriggerStatusCompleted(int trgId) {
		LOGGER.info("updateTriggerStatusCompleted service start");
		try {
			int updateCount = coTriggerRepository.setTriggerStatusCompleted(trgId);
			LOGGER.debug("Update count : " + updateCount);
			return updateCount;
		} catch (Exception e) {
			LOGGER.error("updateTriggerStatusCompleted service error : " + e.getMessage());
			throw new COTriggerException(e.getMessage());
		} finally {
			LOGGER.info("updateTriggerStatusCompleted service end");
		}

	}

}
