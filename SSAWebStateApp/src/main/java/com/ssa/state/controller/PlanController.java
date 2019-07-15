package com.ssa.state.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssa.state.model.PlanModel;
import com.ssa.state.service.IPlanService;

import static com.ssa.state.util.ConstantUtils.*;
import javax.validation.Valid;

/**
 * To handle all requests related to plan
 * 
 * @author VISHAL
 *
 */
@Controller
public class PlanController {
	/**
	 * Logger to log
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PlanController.class);
	/**
	 * Autowire PlanService
	 */
	@Autowired
	private IPlanService planService;

	/**
	 * Default Constructor
	 */
	public PlanController() {
		LOGGER.info("***PlanController***");
	}

	/**
	 * Handles Plan add show Form request
	 * 
	 * @return
	 */
	@RequestMapping(PLAN_ADD_GET_URL)
	public String showPlanForm(Model model) {
		LOGGER.info("***showPlanForm start***");
		model.addAttribute(PLAN_MODEL_KEY, new PlanModel());
		LOGGER.info("***showPlanForm end***");
		return PLAN_ADD_VIEW;
	}

	/**
	 * Handles Add plan Form request
	 * 
	 * @return
	 */
	@RequestMapping(PLAN_SAVE_POST_URL)
	public String savePlan(@Valid @ModelAttribute(PLAN_MODEL_KEY) PlanModel planModel, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		LOGGER.info("***savePlan controller start***");
		LOGGER.debug("Plan data Received: " + planModel.toString());
		if (!validateData(bindingResult, model)) {
			LOGGER.info("***savePlan controller end***");
			return PLAN_ADD_VIEW;
		}

		boolean result=planService.savePlan(planModel);
		if (result) {
			redirectAttributes.addFlashAttribute(PLAN_SAVE_REDIRECT_KEY, PLAN_SAVE_SUCCESS_REDIRECT_VALUE);
		} else {
			redirectAttributes.addFlashAttribute(PLAN_SAVE_REDIRECT_KEY, PLAN_SAVE_FAIL_REDIRECT_VALUE);

		}
		LOGGER.info("***savePlan controller end***");
		return REDIRECT_PLAN_ADD_GET_URL;
	}

	/**
	 * Server side validation
	 * 
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	private boolean validateData(BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			LOGGER.error("***Server side validation fails***");
			return false;
		}
		LOGGER.info("***Server side validation success***");
		return true;
	}

}
