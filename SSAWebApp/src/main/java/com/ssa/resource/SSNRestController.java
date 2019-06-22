package com.ssa.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ssa.controller.SSNController;
import com.ssa.model.ResourceApiError;
import com.ssa.model.State;
import com.ssa.service.SSNUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import static com.ssa.util.ConstantUtils.*;
@RestController
@Api
/**
 * Rest Controller for Any application
 * @author VISHAL
 *
 */
public class SSNRestController {
	@Autowired(required = true)
	@ApiModelProperty
	/**
	 * User service for accessing USER_MASTER
	 */
	private SSNUserService userService;//NOPMD
	/**
	 * SLF4J Logging
	 */
	private static final Logger LOGGER=LoggerFactory.getLogger(SSNController.class);

	/**
	 * Default Constructor
	 */
	public SSNRestController() {
		LOGGER.info("***SSNRestController***");
	}

	@GetMapping(value=GET_STATE_GET_URL,produces = {MediaType.APPLICATION_JSON_VALUE})
	@ApiResponses(value = {@ApiResponse(code = 200,message = "User Available",response = State.class),@ApiResponse(code = 400,message = "User Does not exist",response = ResourceApiError.class)})
	@ApiOperation("Accepts SSN and send State information")
	/**
	 * Getting state data When SSN is passed
	 * @param ssn
	 * @return
	 */
	public State getUserState(@PathVariable("ssn") final Integer ssn) {
		LOGGER.info("Getting User State");
		return userService.getUserState(ssn);
	}
}
