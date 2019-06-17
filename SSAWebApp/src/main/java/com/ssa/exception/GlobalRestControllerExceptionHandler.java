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
public class GlobalRestControllerExceptionHandler {
	private static Logger logger=LoggerFactory.getLogger(SSNController.class);

	public GlobalRestControllerExceptionHandler() {
		logger.debug("***GlobalRestControllerExceptionHandler***");
	}
	@ExceptionHandler(value = SSNUserNotFoundException.class)
	public ResponseEntity<ResourceApiError> handleSSNNotFoundException(Exception ex) {
		logger.debug("Handling SSNUserNotFoundException");
		//model.addAttribute(EXC_KEY,ex.getMessage());
		ResourceApiError apiError=new ResourceApiError();
		apiError.setStatusCode(404);
		apiError.setErrMsg(ex.getMessage());
		apiError.setDate(new Date());
		ResponseEntity<ResourceApiError> entity=new ResponseEntity<ResourceApiError>(apiError, HttpStatus.NOT_FOUND);
		return entity;
	}
	
}
