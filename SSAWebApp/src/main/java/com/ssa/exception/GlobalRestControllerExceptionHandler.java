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
import com.ssa.model.ResourceApiError;
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
		LOGGER.debug("***GlobalRestControllerExceptionHandler***");
	}
	
	/**
	 * From ssn number check the existence of User
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(SSNUserNotFoundException.class)
	public ResponseEntity<ResourceApiError> handleSSNNotFoundException(final Exception exception) {
		LOGGER.debug("Handling SSNUserNotFoundException");
		//model.addAttribute(EXC_KEY,ex.getMessage());
		final ResourceApiError apiError=new ResourceApiError();
		apiError.setStatusCode(404);
		apiError.setErrMsg(exception.getMessage());
		apiError.setDate(new Date());
		return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
	}
	
}
