package com.ssa.state;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssa.state.entity.CitizenPlanEntity;
import com.ssa.state.model.CitizenPlanModel;
import com.ssa.state.repository.ICitizenPlanRepository;
import com.ssa.state.service.ICitizenPlanService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CitizenPlanTableTest {
	/**
	 * Slf4 Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountTableTest.class);

	/**
	 * Injecting CitizenPlan Repository
	 */
	@Autowired
	private ICitizenPlanRepository citizenPlanRepository;
	
	/**
	 * Injecting CitizenPlan Service
	 */
	@Autowired
	private ICitizenPlanService citizenPlanService;

	@Test
	@Ignore
	public void test_saveCitizenPlanRepository_success() {
		LOGGER.info("test_saveCitizenPlanRepository_success start");
		CitizenPlanEntity citizenPlanEntity = new CitizenPlanEntity();
		citizenPlanEntity.setAppNo(1);
		citizenPlanEntity = citizenPlanRepository.save(citizenPlanEntity);
		LOGGER.debug("CitizenPlanEntity : " + citizenPlanEntity);
		assertNotNull(citizenPlanEntity);
		assertTrue(citizenPlanEntity.getCaseNo().startsWith("CASE_"));
		LOGGER.info("test_saveCitizenPlanRepository_success end");

	}

	@Test
	@Ignore
	public void test_updateCitizenPlanRepository_success() {
		LOGGER.info("test_updateCitizenPlanRepository_success start");

		CitizenPlanEntity citizenPlanEntity = new CitizenPlanEntity();
		citizenPlanEntity.setAppNo(1);
		citizenPlanEntity.setCaseNo("CASE_00001");
		citizenPlanEntity.setPlanId(22);
		citizenPlanEntity.setEligible(true);
		citizenPlanEntity = citizenPlanRepository.save(citizenPlanEntity);
		LOGGER.debug("CitizenPlanEntity : " + citizenPlanEntity);
		assertNotNull(citizenPlanEntity);
		LOGGER.info("test_updateCitizenPlanRepository_success end");

	}
	
	@Test
	@Ignore
	public void test_saveCitizenPlanService_success() {
		LOGGER.info("test_saveCitizenPlanService_success start");
		CitizenPlanModel citizenPlanModel = new CitizenPlanModel();
		citizenPlanModel.setAppNo(1);
		citizenPlanModel = citizenPlanService.saveOrUpdateCitizenPlanInfo(citizenPlanModel);
		LOGGER.debug("CitizenPlanModel : " + citizenPlanModel);
		assertNotNull(citizenPlanModel);
		assertTrue(citizenPlanModel.getCaseNo().startsWith("CASE_"));
		LOGGER.info("test_saveCitizenPlanService_success end");
	}

	@Test
	public void test_updateCitizenPlanService_success() {
		LOGGER.info("test_updateCitizenPlanService_success start");

		CitizenPlanModel citizenPlanModel = new CitizenPlanModel();
		citizenPlanModel.setAppNo(1);
		citizenPlanModel.setCaseNo("CASE_00001");
		citizenPlanModel.setPlanId(22);
		citizenPlanModel.setEligible(true);
		citizenPlanModel = citizenPlanService.saveOrUpdateCitizenPlanInfo(citizenPlanModel);
		LOGGER.debug("CitizenPlanModel : " + citizenPlanModel);
		assertNotNull(citizenPlanModel);
		LOGGER.info("test_updateCitizenPlanService_success end");

	}
}
