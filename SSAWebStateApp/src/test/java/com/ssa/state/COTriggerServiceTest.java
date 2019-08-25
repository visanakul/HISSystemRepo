package com.ssa.state;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssa.state.service.ICOTriggerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class COTriggerServiceTest {

	@Autowired(required = true)
	private ICOTriggerService coTriggerService;

	@Test
	@Ignore
	public void test1_getCount_service() {
		long total = coTriggerService.countTotalPendingTriggers();
		assertEquals(5000, total);
	}

	@Test
	@Ignore
	public void test2_getBucket0Triggers_service() {
		long total = coTriggerService.getAllPendingTriggers(5, 0).size();
		assertEquals(809, total);
	}

	@Test
	@Ignore
	public void test3_getBucket1Triggers_service() {
		long total = coTriggerService.getAllPendingTriggers(5, 1).size();
		assertEquals(829, total);
	}

	@Test
	@Ignore
	public void test4_getBucket2Triggers_service() {
		long total = coTriggerService.getAllPendingTriggers(5, 2).size();
		assertEquals(842, total);
	}

	@Test
	@Ignore
	public void test5_getBucket3Triggers_service() {
		long total = coTriggerService.getAllPendingTriggers(5, 3).size();
		assertEquals(801, total);
	}

	@Test
	@Ignore
	public void test6_getBucket4Triggers_service() {
		long total = coTriggerService.getAllPendingTriggers(5, 4).size();
		assertEquals(862, total);
	}

	@Test
	@Ignore
	public void test7_getBucket5Triggers_service() {
		long total = coTriggerService.getAllPendingTriggers(5, 5).size();
		assertEquals(857, total);
	}

	@Transactional
	@Rollback(true)
	@Test
	public void test8_updateTriggerStatusCompleted_service() {
		int updateCount = coTriggerService.updateTriggerStatusCompleted(100);
		assertEquals(1, updateCount);
	}
}
