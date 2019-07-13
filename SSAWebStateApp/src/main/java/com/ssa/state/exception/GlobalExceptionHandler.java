package com.ssa.state.exception;

import static com.ssa.state.util.ConstantUtils.ERROR_VIEW;
import static com.ssa.state.util.ConstantUtils.EXC_KEY;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * Handles exceptions globally
 * @author VISHAL
 *
 */
@Controller
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * SLF4J Logger
	 */
	private static final Logger LOGGER=LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * Default Constructor
	 */
	public GlobalExceptionHandler() {
		LOGGER.info("***GlobalExceptionHandler***");
	}
	
	@ExceptionHandler(EmptyAccountException.class)
	public String handleEmptyAccountException(Exception exception,Model model) {
		LOGGER.info("Handling NoUserException");
		model.addAttribute(EXC_KEY,exception.getMessage());
		return ERROR_VIEW;
	}
}
