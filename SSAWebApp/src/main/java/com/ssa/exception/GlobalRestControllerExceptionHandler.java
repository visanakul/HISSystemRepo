package com.ssa.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssa.controller.SSNController;
import com.ssa.model.ResourceResponse;
@RestController
@RestControllerAdvice
/**
 * Global Exception Handler for Rest Controllers
 */
public class GlobalRestControllerExceptionHandler {
	/**
	 * SLF4J Logger
	 */
	private static final Logger LOGGER=LoggerFactory.getLogger(SSNController.class);

	/**
	 * Default Constructor
	 */
	public GlobalRestControllerExceptionHandler() {
		LOGGER.info("***GlobalRestControllerExceptionHandler***");
	}
	
	/**
	 * From ssn number check the existence of User
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(SSNUserNotFoundException.class)
	public ResponseEntity<ResourceResponse> handleSSNNotFoundException(final Exception exception) {
		LOGGER.info("Handling SSNUserNotFoundException");
		LOGGER.info("ResourceApiError Object creation start..." );
		final ResourceResponse apiError=new ResourceResponse();
		apiError.setStatusCode(404);
		apiError.setMsg(exception.getMessage());
		apiError.setDate(new Date());
		LOGGER.debug("Sending Response : "+apiError);
		LOGGER.info("ResourceApiError Object creation end..." );
		
		return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * From ssn number check the existence of State
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(StateNotForUserException.class)
	public ResponseEntity<ResourceResponse> handleStateNotForUserException(final Exception exception) {
		LOGGER.info("Handling StateNotForUserException");
		LOGGER.info("ResourceApiError Object creation start..." );
		final ResourceResponse apiError=new ResourceResponse();
		apiError.setStatusCode(200);
		apiError.setMsg(exception.getMessage());
		apiError.setDate(new Date());
		LOGGER.debug("Sending Response : "+apiError);
		LOGGER.info("ResourceApiError Object creation end..." );
		
		return new ResponseEntity<>(apiError, HttpStatus.OK);
	}
	
}
