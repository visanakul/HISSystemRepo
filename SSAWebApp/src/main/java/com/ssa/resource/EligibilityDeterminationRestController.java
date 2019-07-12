package com.ssa.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssa.controller.SSNController;
import com.ssa.ed.input.EligibilityDetermination;
import com.ssa.ed.output.PlanInfo;
import com.ssa.service.IEligibilityDeterminationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api
public class EligibilityDeterminationRestController {
	
	/**
	 * SLF4J Logging
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SSNController.class);

	/**
	 * Default Constructor
	 */
	public EligibilityDeterminationRestController() {
		LOGGER.info("***EligibilityDeterminationRestController***");
	}
	/**
	 * Injecting Eligibility Determination Service object
	 */
	@Autowired(required = true)
	@ApiModelProperty
	private IEligibilityDeterminationService eligibilityDeterminationService;
	
	/**
	 * Method handles checkEligibility request and returns PlanInfo Details
	 * @param eligibilityDetermination
	 * @return PlanInfo
	 */
	@PostMapping(value = "/checkEligibility", 
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	@ApiResponses(value = {@ApiResponse(code=200,message = "Server Response",response =PlanInfo.class )})
	@ApiOperation(value = "Determines eligibility and returns PlanInfo")
	public PlanInfo checkEDForPlanInfo(EligibilityDetermination eligibilityDetermination) {
		LOGGER.info("checkEDForPlanInfo Request start");
		LOGGER.debug("Data Received : {}",eligibilityDetermination);
		PlanInfo planInfo=eligibilityDeterminationService.getPlanInfo(eligibilityDetermination);
		LOGGER.debug("Result Received : {}",planInfo);
		LOGGER.info("checkEDForPlanInfo Request end");
		return planInfo;
	}
}
