package com.ssa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssa.model.State;
import com.ssa.service.SSNUserService;
import com.ssa.service.StateService;
/**
 * Test class
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
	public void stateListTest() {
		List<State> stateList=service.getAllStates();
		System.out.println("List : "+stateList);
		assertEquals(52, stateList.size());
	}
	
	/**
	 * Test for SSN format 
	 */
	@Test
	public void checkSSNDisplay() {
		String ssn="123456789";
		String result=new StringBuilder(ssn).insert(3, '-').insert(6, '-').toString();
		System.out.println("Result : "+result);
		
		assertNotEquals("Generated", result, ssn);
	}
	
	/**
	 * Test for getting state as per ssn
	 */
	@Test
	public void getUserState() {
		State stateModel=userService.getUserState(100000012);
		System.out.println(stateModel);
		assertNotNull("State Not available",stateModel);
	}

}
