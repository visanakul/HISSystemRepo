package com.ssa.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ssa.controller.SSNController;
import static com.ssa.util.Constants.*;
@Controller
@ControllerAdvice
public class GlobalControllerExceptionHandler {
	private static Logger logger=LoggerFactory.getLogger(SSNController.class);

	public GlobalControllerExceptionHandler() {
		logger.debug("***GlobalExceptionHandler***");
	}
	
	@ExceptionHandler(value = NoUserException.class)
	public String handleNoUserException(Exception ex,Model model) {
		logger.debug("Handling NoUserException");
		model.addAttribute(EXC_KEY,ex.getMessage());
		return VW_SSN_ERROR;
	}
}
