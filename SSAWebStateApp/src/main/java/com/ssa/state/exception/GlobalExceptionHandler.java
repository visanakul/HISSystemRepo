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
 * 
 * @author VISHAL
 *
 */
@Controller
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * SLF4J Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * Default Constructor
	 */
	public GlobalExceptionHandler() {
		LOGGER.info("***GlobalExceptionHandler***");
	}

	/**
	 * No account record in table
	 * 
	 * @param exception
	 * @param model
	 * @return
	 */
	@ExceptionHandler(EmptyAccountException.class)
	public String handleEmptyAccountException(Exception exception, Model model) {
		LOGGER.info("Handling EmptyAccountException");
		model.addAttribute(EXC_KEY, exception.getMessage());
		return ERROR_VIEW;
	}

	/**
	 * No application record in table
	 * 
	 * @param exception
	 * @param model
	 * @return
	 */
	@ExceptionHandler(EmptyApplicationException.class)
	public String handleEmptyApplicationException(Exception exception, Model model) {
		LOGGER.info("Handling EmptyApplicationException");
		model.addAttribute(EXC_KEY, exception.getMessage());
		return ERROR_VIEW;
	}
	/**
	 * No Plan in table
	 * 
	 * @param exception
	 * @param model
	 * @return
	 */
	@ExceptionHandler(EmptyPlanException.class)
	public String handleEmptyPlanException(Exception exception, Model model) {
		LOGGER.info("Handling EmptyPlanException");
		model.addAttribute(EXC_KEY, exception.getMessage());
		return ERROR_VIEW;
	}

	/**
	 * Plan save error
	 * 
	 * @param exception
	 * @param model
	 * @return
	 */
	@ExceptionHandler(PlanSaveException.class)
	public String handlePlanSaveException(Exception exception, Model model) {
		LOGGER.info("Handling handlePlanSaveException");
		model.addAttribute(EXC_KEY, exception.getMessage());
		return ERROR_VIEW;
	}

	/**
	 * Account not found error
	 * 
	 * @param exception
	 * @param model
	 * @return
	 */
	@ExceptionHandler(AccountNotFoundException.class)
	public String handleAccountNotFoundException(Exception exception, Model model) {
		LOGGER.info("Handling AccountNotFoundException");
		model.addAttribute(EXC_KEY, exception.getMessage());
		return ERROR_VIEW;
	}

	/**
	 * Account not found error
	 * 
	 * @param exception
	 * @param model
	 * @return
	 */
	@ExceptionHandler(ActivePlanNotFoundException.class)
	public String handleActivePlanNotFoundException(Exception exception, Model model) {
		LOGGER.info("Handling ActivePlanNotFoundException");
		model.addAttribute(EXC_KEY, exception.getMessage());
		return ERROR_VIEW;
	}

	/**
	 * Application not found error
	 * 
	 * @param exception
	 * @param model
	 * @return
	 */
	@ExceptionHandler(CitizenNotFoundException.class)
	public String handleCitizenNotFoundException(Exception exception, Model model) {
		LOGGER.info("Handling CitizenNotFoundException");
		model.addAttribute(EXC_KEY, exception.getMessage());
		return ERROR_VIEW;
	}

	/**
	 * BatchProcessException 
	 * 
	 * @param exception
	 * @param model
	 * @return
	 */
	@ExceptionHandler(BatchProcessException.class)
	public String handleBatchProcessException(Exception exception, Model model) {
		LOGGER.info("Handling BatchProcessException");
		model.addAttribute(EXC_KEY, exception.getMessage());
		return ERROR_VIEW;
	}

	/**
	 * Other Unknown error
	 * 
	 * @param exception
	 * @param model
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public String handleGeneralException(Exception exception, Model model) {
		LOGGER.info("Handling handleGeneralException");
		model.addAttribute(EXC_KEY, exception.getMessage());
		return ERROR_VIEW;
	}
}
