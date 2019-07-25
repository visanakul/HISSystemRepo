package com.ssa.state.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssa.state.exception.EmptyPlanException;
import com.ssa.state.exception.PlanSaveException;
import com.ssa.state.model.AccountModel;
import com.ssa.state.model.PlanModel;
import com.ssa.state.service.IPlanService;

import static com.ssa.state.util.ConstantUtils.*;
import static com.ssa.state.util.ConstantUtils.Plan.*;

import java.util.List;

import javax.transaction.Transactional;
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
	@RequestMapping(SHOW_PLAN_GET_URL)
	public String showPlanForm(@RequestParam(value = "accNo", required = false) Integer id, Model model) {
		LOGGER.info("***showPlanForm start***");
		try {
			PlanModel planModel = null;
			if (id == null) {
				LOGGER.info("Request for new plan");
				planModel = new PlanModel();
				model.addAttribute(OP_SELECTED_KEY, PLAN_ADD_VALUE);
				model.addAttribute(OPERATION_BUTTON_TEXT, SAVE_TITLE);
			} else {
				LOGGER.info("Request for edit plan id : " + id);
				planModel = planService.getPlanById(id);
				model.addAttribute(OP_SELECTED_KEY, PLAN_UPDATE_VALUE);
				model.addAttribute(OPERATION_BUTTON_TEXT, UPDATE_TITLE);
			}
			model.addAttribute(PLAN_MODEL_KEY, planModel);
			return PLAN_VIEW;
		} catch (Exception exception) {
			LOGGER.error("Error in hadling showPlanAddOrEditForm request");
			throw new RuntimeException(exception.getMessage());
		} finally {
			LOGGER.info("showPlanForm end");
		}

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
		try {
			planModel.setActive(true);
			LOGGER.debug("Plan data Received: " + planModel.toString());
			if (!validateData(bindingResult, model)) {
				LOGGER.info("***savePlan controller end***");
				return PLAN_VIEW;
			}

			boolean result = planService.savePlan(planModel);
			LOGGER.debug("Save Plan result : " + result);

			model.addAttribute(PLAN_MODEL_KEY, new PlanModel());
			if (result) {
				redirectAttributes.addFlashAttribute(PLAN_SAVE_KEY, PLAN_SAVE_SUCCESS_VALUE);
			} else {
				redirectAttributes.addFlashAttribute(PLAN_SAVE_KEY, PLAN_SAVE_FAIL_VALUE);

			}
			return REDIRECT_PLAN_ADD_GET_URL;
		} catch (Exception exception) {
			exception.printStackTrace();
			LOGGER.error("Exception : " + exception.getMessage());
			throw new RuntimeException(exception.getMessage());
		} finally {
			LOGGER.info("***savePlan controller end***");
		}

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

	/**
	 * Send all records to ajax call for displaying it in JQuery data table
	 * 
	 * @return
	 */

	@RequestMapping(SEND_ALL_PLANS_GET_URL)
	public String sendAllPlan(Model model) {
		LOGGER.info("sendAllPlan start");
		try {
			List<PlanModel> planModels = planService.getAllPlans();
			LOGGER.debug("AccountModels " + planModels);
			if (planModels == null) {
				LOGGER.warn("No plan data");
				throw new EmptyPlanException("No plan found");
			}
			model.addAttribute(ALL_PLANS_MODEL_KEY, planModels);
			return ALL_PLAN_VIEW;
		} catch (Exception exception) {
			LOGGER.error("Exception : " + exception);
			exception.printStackTrace();
			throw new RuntimeException(exception.getMessage());
		} finally {
			LOGGER.info("sendAllPlan end");
		}

	}

	@RequestMapping("soft_delete/{id}/{active}/plan")
	@Transactional
	public @ResponseBody String softDeleteRequest(@PathVariable("id") Integer id,
			@PathVariable("active") boolean active) {
		try {
			LOGGER.info("softDeleteRequest start");
			LOGGER.debug("Plan id : " + id + " Active : " + active);
			boolean status = planService.planDeactivateOrActivate(active, id);
			LOGGER.debug("Status : " + status);
			LOGGER.info("softDeleteRequest end");
			if (status) {
				return "OK";
			} else {
				return "Error";
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * Update plan info in Db table
	 * 
	 * @return
	 */
	@RequestMapping(value = UPDATE_PLAN_POST_URL, method = RequestMethod.POST)
	public String updatePlanInfo(@Valid @ModelAttribute PlanModel planModel, BindingResult bindingResult, Model model) {
		LOGGER.info("updatePlanInfo start");
		try {
			if (!validateData(bindingResult, model)) {
				return PLAN_VIEW;
			}
			LOGGER.debug("Plan data Received: " + planModel.toString());

			boolean result = planService.addOrUpdatePlan(planModel);
			if (result) {
				model.addAttribute(PLAN_UPDATE_KEY, PLAN_UPDATE_SUCCESS_VALUE);
				LOGGER.info(PLAN_UPDATE_SUCCESS_VALUE);
			} else {
				model.addAttribute(PLAN_UPDATE_KEY, PLAN_UPDATE_FAIL_VALUE);
				LOGGER.info(PLAN_UPDATE_FAIL_VALUE);
			}
			model.addAttribute(PLAN_MODEL_KEY, planModel);
			model.addAttribute(OP_SELECTED_KEY, PLAN_UPDATE_VALUE);
			model.addAttribute(OPERATION_BUTTON_TEXT, UPDATE_TITLE);
		} catch (Exception exception) {
			LOGGER.error("Exception : " + exception);
			throw new RuntimeException(exception.getMessage());
		} finally {
			LOGGER.info("updatePlanInfo end");
		}
		return PLAN_VIEW;
	}

}
