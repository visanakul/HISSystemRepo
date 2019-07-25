package com.ssa.state.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ssa.ed.input.EligibilityDetermination;
import com.ssa.ed.input.EligibilityDetermination.CitigenData;
import com.ssa.ed.input.EligibilityDetermination.PlanDetails;
import com.ssa.ed.input.EligibilityDetermination.PlanDetails.SnapPlanData;
import com.ssa.ed.output.PlanInfo;
import com.ssa.state.entity.PlanEntity;
import com.ssa.state.exception.ActivePlanNotFoundException;
import com.ssa.state.exception.PlanSaveException;
import com.ssa.state.model.PlanModel;
import com.ssa.state.repository.IPlanRepository;

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
		try {
			/**
			 * Converting PlanModel to PlanEntity
			 */
			PlanEntity planEntity = new PlanEntity();
			BeanUtils.copyProperties(planModel, planEntity);
			planEntity = planRepository.save(planEntity);
			LOGGER.debug("Plan entity got :{} ", planEntity);
			if (planEntity == null) {
				return false;
			}
			return planEntity.getId() > 0;
		} catch (Exception exception) {
			LOGGER.error("Exception : "+exception.getMessage());
			throw new PlanSaveException(exception.getMessage());

		} finally {
			LOGGER.info("savePlan service end");
		}
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
		try {
			LOGGER.debug("Plan active: {},id: {} received : ", active, id);
			Integer status = planRepository.softDeleteOrActiveById(active, id);
			LOGGER.debug("Activation Status : " + status);
			LOGGER.info("planDeactivateOrActivate service end");
			return status > 0;
		} catch (Exception exception) {
			LOGGER.error("addOrUpdatePlan error : " + exception.getMessage());
			exception.printStackTrace();
			throw new RuntimeException(exception.getMessage());
		} finally {
			LOGGER.info("planDeactivateOrActivate service end");
		}
	}

	@Override
	public boolean addOrUpdatePlan(PlanModel planModel) {
		LOGGER.info("addOrUpdatePlan service start");
		try {
			LOGGER.debug("Plan model received : " + planModel);

			planModel.setActive(true);

			PlanEntity planEntity = new PlanEntity();
			/**
			 * Converting PlanModel to PlanEntity
			 */
			BeanUtils.copyProperties(planModel, planEntity);
			/**
			 * Saving record
			 */
			planEntity = planRepository.save(planEntity);
			LOGGER.debug("Plan entity after save or update : " + planEntity);
			return planEntity.getId() > 0;
		} catch (Exception exception) {
			LOGGER.error("addOrUpdatePlan error : " + exception.getMessage());
			exception.printStackTrace();
			throw new RuntimeException(exception.getMessage());
		} finally {
			LOGGER.info("addOrUpdatePlan service end");
		}

	}

	@Override
	public PlanModel getPlanById(Integer id) {
		LOGGER.info("getPlanById service start");
		try {
			Optional<PlanEntity> planEntityOptional = planRepository.findById(id);
			if (!planEntityOptional.isPresent()) {
				LOGGER.info("No plan found");
				return null;
			}
			PlanModel planModel = new PlanModel();
			LOGGER.debug("PlanEntity : " + planEntityOptional.get());
			PlanEntity planEntity = planEntityOptional.get();
			BeanUtils.copyProperties(planEntity, planModel);
			LOGGER.debug("PlanModel : " + planModel);

			return planModel;
		} catch (Exception exception) {
			LOGGER.error("planDeactivateOrActivate error : " + exception.getMessage());
			exception.printStackTrace();
			throw new RuntimeException(exception.getMessage());
		} finally {
			LOGGER.info("getPlanById service end");
		}
	}

	@Override
	public List<String> getAllActivePlans() {
		LOGGER.info("getAllActivePlans service start");
		try {
			List<String> planNames = planRepository.findActivePlans();
			if (planNames == null || planNames.size() == 0) {
				LOGGER.warn("No active plan available");
				throw new ActivePlanNotFoundException("No active plan available");
			}
			LOGGER.debug("Plan name list : " + planNames);

			return planNames;

		} catch (Exception exception) {
			exception.printStackTrace();
			LOGGER.error("Exception : " + exception.getMessage());
			throw new RuntimeException(exception.getMessage());
		} finally {
			LOGGER.info("getAllActivePlans service end");
		}
	}

	@Override
	public Integer getPlanIdByName(String planName) {
		LOGGER.info("getPlanIdByName service start");
		try {
		Integer planId=planRepository.findIdByName(planName);
		LOGGER.debug("Plan Id : "+planId);
		return planId;
		}
		catch(Exception exception) {
			exception.printStackTrace();
			LOGGER.error("Exception : " + exception.getMessage());
			throw new RuntimeException(exception.getMessage());
		}
		finally {
			LOGGER.info("getPlanIdByName service end");
		}
	}

	@Override
	public PlanInfo findEligibility(EligibilityDetermination eligibilityDetermination) {
		String endPointUrl = "http://localhost:8081/SSAWebApp/checkEligibility";
		HttpHeaders headers = new HttpHeaders();
		
		List<MediaType> list = new ArrayList<>();
		list.add(MediaType.APPLICATION_XML);
		headers.setAccept(list);
		
		headers.setContentType(MediaType.APPLICATION_XML);
		
		HttpEntity<EligibilityDetermination> requestEntity = new HttpEntity<>(eligibilityDetermination, headers);
		System.out.println("Request : " + requestEntity);

		RestTemplate template = new RestTemplate();
		ResponseEntity<PlanInfo> response = template.postForEntity(endPointUrl, requestEntity, PlanInfo.class);
		PlanInfo planInfo = response.getBody();
		return planInfo;
	}

}
