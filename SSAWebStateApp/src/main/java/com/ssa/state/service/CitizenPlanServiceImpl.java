package com.ssa.state.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssa.state.entity.CitizenPlanEntity;
import com.ssa.state.exception.CitizenPlanUpdateException;
import com.ssa.state.exception.PlanSaveException;
import com.ssa.state.model.CitizenPlanModel;
import com.ssa.state.repository.ICitizenPlanRepository;

@Service
public class CitizenPlanServiceImpl implements ICitizenPlanService {
	/**
	 * Slf4j Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CitizenPlanServiceImpl.class);

	/**
	 * Injecting CitizenPlanRepository
	 */
	public CitizenPlanServiceImpl() {
		LOGGER.info("***CitizenPlanService***");
	}

	@Autowired(required = true)
	private ICitizenPlanRepository citizenRepository;

	@Override
	public CitizenPlanModel saveOrUpdateCitizenPlanInfo(CitizenPlanModel citizenPlanModel) {
		LOGGER.info("saveOrUpdateCitizenPlanInfo service start");
		try {
			LOGGER.debug("CitizenPlanModel : " + citizenPlanModel);
			if(citizenPlanModel.getCaseNo()==null) {
				LOGGER.debug("Save request...");
			}
			else {
				LOGGER.debug("Update request...");
			}
			CitizenPlanEntity citizenPlanEntity = new CitizenPlanEntity();
			BeanUtils.copyProperties(citizenPlanModel, citizenPlanEntity);
			LOGGER.debug("Copied CitizenPlanEntity : " + citizenPlanEntity);

			citizenPlanEntity = citizenRepository.save(citizenPlanEntity);

			if (citizenPlanEntity == null) {
				return null;
			}
			BeanUtils.copyProperties(citizenPlanEntity, citizenPlanModel);
			LOGGER.debug("Copied CitizenPlanModel : " + citizenPlanModel);

			return citizenPlanModel;
		} catch (Exception exception) {
			LOGGER.error("Exception : " + exception.getMessage());
			throw new RuntimeException(exception.getMessage());

		} finally {
			LOGGER.info("saveOrUpdateCitizenPlanInfo service end");
		}
	}

}
