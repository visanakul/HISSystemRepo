package com.ssa.state;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssa.state.repository.ICOTriggerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class COTriggerTableTest {

	@Autowired(required = true)
	private ICOTriggerRepository coTriggerRepository;
	@Test
	public void test1_getCount_repository() {
		long total=coTriggerRepository.count();
		assertEquals(5000, total);
	}
	
	@Test
	public void test2_getBucket0Triggers_repository() {
		long total=coTriggerRepository.findAll(5, 0).size();
		assertEquals(809, total);
	}
	@Test
	public void test3_getBucket1Triggers_repository() {
		long total=coTriggerRepository.findAll(5, 1).size();
		assertEquals(829, total);
	}
	@Test
	public void test4_getBucket2Triggers_repository() {
		long total=coTriggerRepository.findAll(5, 2).size();
		assertEquals(842, total);
	}
	@Test
	public void test5_getBucket3Triggers_repository() {
		long total=coTriggerRepository.findAll(5, 3).size();
		assertEquals(801, total);
	}
	@Test
	public void test6_getBucket4Triggers_repository() {
		long total=coTriggerRepository.findAll(5, 4).size();
		assertEquals(862, total);
	}
	@Test
	public void test7_getBucket5Triggers_repository() {
		long total=coTriggerRepository.findAll(5, 5).size();
		assertEquals(857, total);
	}
}
