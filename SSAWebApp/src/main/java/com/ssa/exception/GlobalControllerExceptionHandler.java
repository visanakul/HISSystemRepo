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
		LOGGER.info("***GlobalExceptionHandler***");
	}
	
	/**
	 * Handles Exception if user not found in Table
	 * @param exception
	 * @param model
	 * @return
	 */
	@ExceptionHandler(NoUserException.class)
	public String handleNoUserException(final Exception exception,final Model model) {
		LOGGER.info("Handling NoUserException");
		model.addAttribute(EXC_KEY,exception.getMessage());
		return ERROR_VIEW;
	}
	
	/**
	 * Handles Exception if state not found in Table
	 * @param exception
	 * @param model
	 * @return
	 */
	@ExceptionHandler(NoStateException.class)
	public String handleNoStateException(final Exception exception,final Model model) {
		LOGGER.info("Handling NoStateException");
		model.addAttribute(EXC_KEY,exception.getMessage());
		return ERROR_VIEW;
	}
	
	/**
	 * If state data not loaded from STATE_MASTER, it handles exception
	 * @param exception
	 * @param model
	 * @return
	 */
	@ExceptionHandler(StatesNotFoundException.class)
	public String handleStatesNotFoundException(final Exception exception,final Model model) {
		LOGGER.info("Handling StatesNotFoundException");
		model.addAttribute(EXC_KEY,exception.getMessage());
		return ERROR_VIEW;
	}
	/**
	 * State data not available for user
	 * @param exception
	 * @param model
	 * @return
	 */
	
	@ExceptionHandler(StateNotForUserException.class)
	public String handlev(final Exception exception,final Model model) {
		LOGGER.info("Handling StateNotForUserException");
		model.addAttribute(EXC_KEY,exception.getMessage());
		return ERROR_VIEW;
	}
	@ExceptionHandler(RegisterException.class)
	public String handleRegisterException(final Exception exception,final Model model) {
		LOGGER.info("Handling RegisterException");
		model.addAttribute(EXC_KEY,exception.getMessage());
		return ERROR_VIEW;
	}
	
	/**
	 * Handle other exceptions
	 * @param exception
	 * @param model
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	public String handleOtherExceptions(final Exception exception,final Model model) {
		LOGGER.info("Handling Other Exception");
		model.addAttribute(EXC_KEY,exception.getMessage());
		return ERROR_VIEW;
	}
	
}
