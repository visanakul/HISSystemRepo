package com.ssa.state.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssa.state.entity.PlanEntity;
import com.ssa.state.exception.PlanSaveException;
import com.ssa.state.model.PlanModel;
import com.ssa.state.repository.IPlanRepository;

import oracle.net.aso.e;

/**
 * PlanService implementation for PLAN_MASTER table
 * 
 * @author VISHAL
 *
 */
@Service
public class PlanServiceImpl implements IPlanService {

	/**
	 * Slf4j Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

	/**
	 * Injecting AccountRepository
	 */
	@Autowired
	private IPlanRepository planRepository;

	public PlanServiceImpl() {
		LOGGER.info("***PlanServiceImpl***");
	}

	@Override
	public boolean savePlan(PlanModel planModel) {
		LOGGER.info("savePlan service start");
		/**
		 * Converting PlanModel to PlanEntity
		 */
		PlanEntity planEntity = new PlanEntity();
		BeanUtils.copyProperties(planModel, planEntity);
		planEntity = planRepository.save(planEntity);
		LOGGER.debug("Plan entity got :{} ", planEntity);
		if (planEntity == null) {
			throw new PlanSaveException("Plan not saved");
		}
		LOGGER.info("savePlan service end");
		return planEntity.getId() > 0;
	}

	@Override
	public List<PlanModel> getAllPlans() {
		LOGGER.info("getAllPlans service start");
		try {
			List<PlanEntity> planEntities = planRepository.findAll();
			LOGGER.debug("Plan Entities : " + planEntities);

			if (planEntities == null) {
				LOGGER.warn("No plan data");
				return null;
			}
			List<PlanModel> planModels = new ArrayList<PlanModel>(planEntities.size());
			for (PlanEntity planEntity : planEntities) {
				PlanModel planModel = new PlanModel();
				BeanUtils.copyProperties(planEntity, planModel);
				planModels.add(planModel);
			}
			LOGGER.debug("Plan models : " + planModels);
			return planModels;
		} catch (Exception exception) {
			LOGGER.error("Exception : " + exception);
			exception.printStackTrace();
			throw new RuntimeException(exception.getMessage());
		} finally {
			LOGGER.info("getAllPlans service end");
		}

	}

	@Override
	public boolean planDeactivateOrActivate(boolean active, Integer id) {
		LOGGER.info("planDeactivateOrActivate service start");
		Integer status = planRepository.softDeleteOrActiveById(active, id);
		LOGGER.debug("Activation Status : " + status);
		LOGGER.info("planDeactivateOrActivate service end");
		return status > 0;
	}

}
