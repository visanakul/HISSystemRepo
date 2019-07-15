package com.ssa.state;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;
import com.ssa.state.entity.PlanEntity;
import com.ssa.state.model.PlanModel;
import com.ssa.state.repository.IPlanRepository;
import com.ssa.state.service.IPlanService;

/**
 * Test cases for PLAN_MASTER table operations
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanTest {
	/**
	 * Slf4 Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountTableTest.class);

	/**
	 * Injecting PlanRepository
	 */
	@Autowired
	private IPlanRepository planRepository;
	/**
	 * Injecting PlanService
	 */
	@Autowired
	private IPlanService planService;

	
	@Test
	@Ignore
	public void test_planSaveRepository_success() {
		LOGGER.info("test_planSaveRepository_success start");
		PlanEntity planEntity = new PlanEntity();
		planEntity.setName("Snap");
		planEntity.setDescription("Snap plan description...");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		Date startDate = calendar.getTime();
		LOGGER.debug("Start date : " + startDate);
		planEntity.setStartDate(startDate);
		calendar.add(Calendar.MONTH, 3);
		LOGGER.debug("Calendar+3months: " + calendar);
		Date endDate = calendar.getTime();
		LOGGER.debug("End date : " + endDate);
		planEntity.setEndDate(endDate);
		planEntity = planRepository.save(planEntity);
		assertNotNull(planEntity);
		assertTrue(planEntity.getId() > 0);
		LOGGER.info("test_planSaveRepository_success end");
	}
	/**
	 * Testing start and end date values startDate<endDate
	 */
	@Test(expected = TransactionSystemException.class)
	@Ignore
	public void test_planSaveStartDateLessThanEndRepository_fail() {
		LOGGER.info("test_planSaveStartDateLessThanEndRepository_fail start");
		PlanEntity planEntity = new PlanEntity();
		planEntity.setName("Snap");
		planEntity.setDescription("Snap plan description...");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		Date startDate = calendar.getTime();
		LOGGER.debug("Start date : " + startDate);
		
		calendar.add(Calendar.MONTH, 3);
		LOGGER.debug("Calendar+3months: " + calendar);
		Date endDate = calendar.getTime();
		LOGGER.debug("End date : " + endDate);
		planEntity.setStartDate(endDate);
		planEntity.setEndDate(startDate);
		planEntity = planRepository.save(planEntity);
		assertNull(planEntity);
		LOGGER.info("test_planSaveStartDateLessThanEndRepository_fail end");
	}
	@Test
	public void test_planSaveService_success() {
		LOGGER.info("test_planSaveService_success start");
		PlanModel planModel = new PlanModel();
		planModel.setName("Snap");
		planModel.setDescription("Snap plan description...");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		Date startDate = calendar.getTime();
		LOGGER.debug("Start date : " + startDate);
		planModel.setStartDate(startDate);
		calendar.add(Calendar.MONTH, 3);
		LOGGER.debug("Calendar+3months: " + calendar);
		Date endDate = calendar.getTime();
		LOGGER.debug("End date : " + endDate);
		planModel.setEndDate(endDate);
		boolean flag = planService.savePlan(planModel);
		LOGGER.debug("Save Pan Status : " + flag);
		assertNotNull(planModel);
		assertTrue(flag);
		LOGGER.info("test_planSaveService_success end");
	}
	/**
	 * Testing start and end date values startDate<endDate
	 */
	@Test(expected = TransactionSystemException.class)
	public void test_planSaveStartDateLessThanEndService_fail() {
		LOGGER.info("test_planSaveStartDateLessThanEndService_fail start");
		PlanModel planModel = new PlanModel();
		planModel.setName("Snap");
		planModel.setDescription("Snap plan description...");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		Date startDate = calendar.getTime();
		LOGGER.debug("Start date : " + startDate);
		
		calendar.add(Calendar.MONTH, 3);
		LOGGER.debug("Calendar+3months: " + calendar);
		Date endDate = calendar.getTime();
		LOGGER.debug("End date : " + endDate);
		planModel.setStartDate(endDate);
		planModel.setEndDate(startDate);
		boolean flag = planService.savePlan(planModel);
		LOGGER.debug("Save Pan Status : " + flag);
		assertNull(planModel);
		LOGGER.info("test_planSaveStartDateLessThanEndService_fail end");
	}

}
