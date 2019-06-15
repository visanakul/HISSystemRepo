package com.ssa.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ssa.model.State;
import com.ssa.service.SSNUserService;

@RestController
public class SSNRestController {
	@Autowired
	private SSNUserService userService;
	
	@GetMapping("/getState/{ssn}")
	public State getUserState(@PathVariable("ssn") Integer ssn) {
		State state=userService.getUserState(ssn);
		return state;
	}
}
