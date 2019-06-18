package com.ssa.exception;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ssa.controller.SSNController;
import static com.ssa.util.ConstantUtils.*;

@Controller
@ControllerAdvice
/**
 * Global Exception Handler for Controllers
 */
public class GlobalControllerExceptionHandler {
	/**
	 * SLF4J Logger
	 */
	private static final Logger LOGGER=LoggerFactory.getLogger(SSNController.class);

	/**
	 * Default Constructor
	 */
	public GlobalControllerExceptionHandler() {
		LOGGER.debug("***GlobalExceptionHandler***");
	}
	
	/**
	 * Handles Exception if user not found in Table
	 * @param ex
	 * @param model
	 * @return
	 */
	@ExceptionHandler(NoUserException.class)
	public String handleNoUserException(final Exception exception,final Model model) {
		LOGGER.debug("Handling NoUserException");
		model.addAttribute(EXC_KEY,exception.getMessage());
		return VW_SSN_ERROR;
	}
}
