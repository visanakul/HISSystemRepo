package com.ssa.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ssa.controller.SSNController;
import com.ssa.model.State;
import com.ssa.service.SSNUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api
public class SSNRestController {
	@Autowired
	@ApiModelProperty
	private SSNUserService userService;
	private static Logger logger=LoggerFactory.getLogger(SSNController.class);

	public SSNRestController() {
		logger.debug("***SSNRestController***");
	}

	@GetMapping(value="/getState/{ssn}",produces = {MediaType.APPLICATION_JSON_VALUE})
	@ApiResponses(value = {@ApiResponse(code = 200,message = "User Available"),@ApiResponse(code = 500,message = "User Does not exist")})
	@ApiOperation( value = "Accepts SSN and send State information")
	public State getUserState(@PathVariable("ssn") Integer ssn) {
		logger.debug("Calling getUserState()");
		State state=userService.getUserState(ssn);
		logger.debug("State Data : "+state);
		return state;
	}
}
