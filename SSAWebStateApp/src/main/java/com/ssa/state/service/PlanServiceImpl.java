package com.ssa.state.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssa.state.entity.PlanEntity;
import com.ssa.state.exception.PlanSaveException;
import com.ssa.state.model.PlanModel;
import com.ssa.state.repository.IPlanRepository;
/**
 * PlanService implementation for PLAN_MASTER table
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
		PlanEntity planEntity=new PlanEntity();
		BeanUtils.copyProperties(planModel, planEntity);
		planEntity=planRepository.save(planEntity);
		LOGGER.debug("Plan entity got :{} ",planEntity);
		if(planEntity==null) {
			throw new PlanSaveException("Plan not saved");
		}
		LOGGER.info("savePlan service end");
		return planEntity.getId()>0;
	}

}
