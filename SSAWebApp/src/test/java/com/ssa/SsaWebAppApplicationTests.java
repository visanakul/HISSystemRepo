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

@RunWith(SpringRunner.class)
@SpringBootTest
public class SsaWebAppApplicationTests {

	@Autowired
	private StateService service;

	@Autowired
	private SSNUserService userService;

	public void stateListTest() {
		List<State> stateList=service.getAllStates();
		System.out.println("List : "+stateList);
		assertEquals(52, stateList.size());
	}
	
	@Test
	public void checkSSNDisplay() {
		String ssn="123456789";
		String result=new StringBuilder(ssn).insert(3, '-').insert(6, '-').toString();
		System.out.println("Result : "+result);
		
		assertNotEquals("Generated", result, ssn);
	}
	
	@Test
	public void getUserState() {
		State stateModel=userService.getUserState(100000012);
		System.out.println(stateModel);
		assertNotNull("State Not available",stateModel);
	}

}
