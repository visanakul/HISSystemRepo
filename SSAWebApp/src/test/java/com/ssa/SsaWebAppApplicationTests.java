package com.ssa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssa.exception.SSNUserNotFoundException;
import com.ssa.model.State;
import com.ssa.service.SSNUserService;
import com.ssa.service.StateService;
import com.ssa.util.SSNUtil;

/**
 * Test class
 * 
 * @author VISHAL
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SsaWebAppApplicationTests {

	/**
	 * State service accesses STATE_MASTER table
	 */
	@Autowired
	private StateService service;

	/**
	 * User service accesses USER_MASTER table
	 */
	@Autowired
	private SSNUserService userService;

	/**
	 * Test for getting state list
	 */
	@Test
	public void stateListTest() {
		List<State> stateList = service.getAllStates();
		System.out.println("List : " + stateList);
		assertEquals(52, stateList.size());
	}

	/**
	 * Test for SSN format success
	 */
	@Test
	public void test_getSSNFormat_success() {
		Integer ssn = 100000020;
		String result = SSNUtil.getSSNFormat(ssn);
		System.out.println("Result : " + result);

		assertEquals("100-000-020", result);
	}

	/**
	 * Test for SSN format fail
	 */
	@Test
	public void test_getSSNFormat_fail() {
		Integer ssn = 100000020;
		String result = SSNUtil.getSSNFormat(ssn);
		System.out.println("Result : " + result);

		assertNotEquals(ssn.toString().length(), result.length());
	}

	/**
	 * Test for getting state as per ssn Success test
	 */
	@Test
	public void test_getUserState_success() {
		State stateModel = userService.getUserState(100000020);
		System.out.println(stateModel);
		assertEquals("Arizona", stateModel.getStateName());
	}

	/**
	 * Test for getting state as per ssn Success test
	 */
	@Test
	public void test_getUserState1_success() {
		String state = userService.getStateBySSN(100000020);
		System.out.println(state);
		assertEquals("Arizona", state);
	}

	/**
	 * Test for getting state as per ssn Fail test
	 */
	@Test(expected = SSNUserNotFoundException.class)
	public void test_getUserState_fail() {
		State stateModel = userService.getUserState(100000011);
		System.out.println(stateModel);
		assertNull(stateModel);
	}

}
