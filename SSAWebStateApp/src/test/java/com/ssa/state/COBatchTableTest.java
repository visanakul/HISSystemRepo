package com.ssa.state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssa.state.entity.COBatchEntity;
import com.ssa.state.model.COBatchModel;
import com.ssa.state.repository.ICOBatchRepository;
import com.ssa.state.service.ICOBatchService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class COBatchTableTest {
	/**
	 * Slf4 Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(COBatchTableTest.class);

	/**
	 * Injecting COBatch Repository
	 */
	@Autowired(required = true)
	private ICOBatchRepository coBatchRepository;

	/**
	 * Injecting COBatch Service
	 */
	@Autowired(required = true)
	private ICOBatchService coBatchService;

	@Test
	public void test1_co_batch_insert_repo() {
		COBatchEntity coBatchEntity = new COBatchEntity();
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String batchName = dateFormat.format(today);
		LOGGER.debug("Date : " + batchName);
		coBatchEntity.setBatchName("Batch " + batchName);
		coBatchEntity.setBacthStatus("S");

		coBatchEntity = coBatchRepository.save(coBatchEntity);
		assertTrue(coBatchEntity.getBatchId() > 0);
	}

	@Test
	public void test2_co_batch_update_repo() {
		COBatchEntity coBatchEntity = new COBatchEntity();
		coBatchEntity.setBatchId(10);
		coBatchEntity.setBacthStatus("E");

		coBatchEntity = coBatchRepository.save(coBatchEntity);
		assertNotNull(coBatchEntity);
		assertTrue(coBatchEntity.getBatchId().intValue()>10);
	}

	@Test
	public void test3_co_batch_insert_service() {
		COBatchModel coBatchModel = new COBatchModel();
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String batchName = dateFormat.format(today);
		LOGGER.debug("Date : " + batchName);
		coBatchModel.setBatchName("Batch " + batchName);
		coBatchModel.setBacthStatus("S");

		boolean flag = coBatchService.saveBatchStartData(coBatchModel);
		assertTrue(flag == true);
	}

	@Test
	public void test4_co_batch_update_service() {
		COBatchModel coBatchModel = new COBatchModel();
		coBatchModel.setBatchId(11);
		coBatchModel.setBacthStatus("E");

		boolean flag = coBatchService.updateBatchEndData(coBatchModel);
		assertTrue(flag == true);
	}
}
