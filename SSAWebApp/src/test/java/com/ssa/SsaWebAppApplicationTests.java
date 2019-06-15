package com.ssa;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssa.model.State;
import com.ssa.service.StateService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SsaWebAppApplicationTests {

	@Autowired
	private StateService service;
	@Test
	public void stateListTest() {
		List<State> stateList=service.getAllStates();
		System.out.println("List : "+stateList);
		assertEquals(52, stateList.size());
	}

}
