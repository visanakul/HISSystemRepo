package com.ssa.state.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssa.ed.input.EligibilityDetermination;
import com.ssa.ed.input.EligibilityDetermination.CitigenData;
import com.ssa.ed.input.EligibilityDetermination.PlanDetails;
import com.ssa.ed.input.EligibilityDetermination.PlanDetails.SnapPlanData;
import com.ssa.ed.output.PlanInfo;
import com.ssa.state.exception.ActivePlanNotFoundException;
import com.ssa.state.exception.CitizenNotFoundException;
import com.ssa.state.exception.EmptyApplicationException;
import com.ssa.state.model.AccountModel;
import com.ssa.state.model.CitizenModel;
import com.ssa.state.model.CitizenPlanModel;
import com.ssa.state.service.ICitizenApplicationService;
import com.ssa.state.service.ICitizenPlanService;
import com.ssa.state.service.IPlanService;

import static com.ssa.state.util.ConstantUtils.*;
import static com.ssa.state.util.ConstantUtils.Account.ALL_ACCOUNTS_MODEL_KEY;
import static com.ssa.state.util.ConstantUtils.Account.ALL_ACC_1_VIEW;
import static com.ssa.state.util.ConstantUtils.Account.SEND_ALL_ACCOUNTS_1_GET_URL;
import static com.ssa.state.util.ConstantUtils.ApplicationRegistration.*;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * To handle all requests related to application registration
 * 
 * @author VISHAL
 *
 */
@Controller
public class ApplicationRegistrationController {
	/**
	 * Logger to log
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationRegistrationController.class);

	/**
	 * Injecting Application Service
	 */
	@Autowired(required = true)
	private ICitizenApplicationService citizenApplicationService;
	/**
	 * Injecting CitizenPlan Service
	 */
	@Autowired(required = true)
	private ICitizenPlanService citizenPlanService;
	/**
	 * Injecting Plan Service
	 */
	@Autowired(required = true)
	private IPlanService planService;

	/**
	 * Default Constructor
	 */
	public ApplicationRegistrationController() {
		LOGGER.info("***ApplicationRegistrationController***");
	}

	/**
	 * Show Application form and load model with ApplicationModel,Genders info
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(SHOW_APP_REG_GET_URL)
	public String showAppRegForm(Model model) {
		LOGGER.info("showAppRegForm start");

		CitizenModel citizenModel = new CitizenModel();
		loadGenders(model);
		model.addAttribute(CITIZEN_MODEL_KEY, citizenModel);
		model.addAttribute(OP_SELECTED_KEY, APPLICATION_REGISTRATION_VALUE);
		model.addAttribute(OPERATION_BUTTON_TEXT, SAVE_TITLE);
		LOGGER.info("showAppRegForm end");
		return APP_REG_VIEW;
	}

	/**
	 * Loading Genders
	 * 
	 * @param model
	 */
	private void loadGenders(Model model) {
		LOGGER.info("***Gender Loading start***");
		final String[] gens = { MALE, FEMALE };
		model.addAttribute(GENDERS_MODEL_KEY, gens);
		LOGGER.info("***Gender Loading end***");
	}

	/**
	 * Check email availability and send message
	 * 
	 * @return
	 */
	@RequestMapping(CHECK_EMAIL_GET_URL)
	public @ResponseBody String doesEmailExist(@RequestParam("email") String email) {
		LOGGER.info("doesEmailExist start");
		LOGGER.debug("Email id : " + email);
		boolean flag = citizenApplicationService.checkEmail(email);
		String msg = flag ? EMAIL_EXISTS : EMAIL_OK;
		LOGGER.info("doesEmailExist end");
		return msg;
	}

	/**
	 * Save Application info in Db table
	 * 
	 * @return
	 */
	@RequestMapping(value = SAVE_APP_POST_URL, method = RequestMethod.POST)
	public String saveApplicationInfo(@Valid @ModelAttribute CitizenModel citizenModel, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, HttpSession session, Model model) {
		LOGGER.info("saveApplicationInfo start");
		LOGGER.debug("Citizen data : " + citizenModel);
		try {
			if (!validateData(bindingResult, model)) {
				loadGenders(model);
				loadActivePlans(model);
				return APP_REG_VIEW;
			}
			LOGGER.debug("User data Received: " + citizenModel.toString());

			String city = citizenApplicationService.getCityName(citizenModel.getSsn());
			if (!city.toUpperCase().equals("Alabama".toUpperCase())) {
				redirectAttributes.addFlashAttribute(INVALID_CITY_KEY, INVALID_CITY_VALUE);
			} else {
				citizenModel.setActive(true);
				Integer appNo = citizenApplicationService.addOrUpdateApplication(citizenModel);
				LOGGER.debug("Application no : " + appNo);
				if (appNo != null) {
					citizenModel.setAppNo(appNo);
					redirectAttributes.addFlashAttribute(APP_REG_KEY,
							APP_REG_SUCCESS_VALUE + " Applicaion no :" + appNo);
					session.setAttribute(CITIZEN_SESSION_KEY, citizenModel);
				} else {
					redirectAttributes.addFlashAttribute(APP_REG_KEY, APP_REG_FAIL_VALUE);
				}
			}
			redirectAttributes.addFlashAttribute(OP_SELECTED_KEY, APPLICATION_REGISTRATION_VALUE);
			redirectAttributes.addFlashAttribute(OPERATION_BUTTON_TEXT, SAVE_TITLE);
			return REDIRECT_SHOW_APP_FORM_GET_URL;
		} catch (Exception exception) {
			LOGGER.error("Exception : " + exception);
			throw new RuntimeException(exception.getMessage());
		} finally {
			LOGGER.info("saveApplicationInfo end");
		}

	}

	private void loadActivePlans(Model model) {
		LOGGER.info("loadActivePlans start");
		try {
			List<String> planNames = planService.getAllActivePlans();
			if (planNames == null || planNames.size() == 0) {
				LOGGER.warn("No active plan");
				throw new ActivePlanNotFoundException("No active plan");
			}
			LOGGER.debug("Plan names : " + planNames);
			model.addAttribute(PLANS_MODEL_KEY, planNames);
		} catch (Exception exception) {
			LOGGER.info("Exception : " + exception.getMessage());
			throw new RuntimeException(exception.getMessage());
		} finally {
			LOGGER.info("loadActivePlans end");
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

	@RequestMapping(SEND_ALL_APPLICATIONS_GET_URL)
	public String sendAllApplications(Model model) {
		LOGGER.info("sendAllApplications start");
		try {
			List<CitizenModel> citizenModels = citizenApplicationService.getAllApplications();
			LOGGER.debug("CitizenModels " + citizenModels);
			if (citizenModels == null || citizenModels.size() == 0) {
				LOGGER.warn("No application");
				throw new EmptyApplicationException("No application");
			}
			model.addAttribute(ALL_APPLICATIONS_MODEL_KEY, citizenModels);
			return ALL_APP_VIEW;
		} catch (Exception exception) {
			LOGGER.info("Exception : " + exception.getMessage());
			throw new RuntimeException(exception.getMessage());
		} finally {
			LOGGER.info("sendAllApplications end");
		}

	}

	@RequestMapping("/show_create_case")
	public String showCreateCaseForm(Model model) {
		LOGGER.info("showCreateCaseForm start");
		LOGGER.info("showCreateCaseForm end");
		return "create_case";
	}

	@RequestMapping("/app_no_check")
	public String showCitizenData(@RequestParam(value = "appNo") Integer appNo, Model model) {
		LOGGER.info("showCitizenData start");
		try {
			CitizenModel citizenModel = citizenApplicationService.getApplicationByAppNo(appNo);
			if (citizenModel != null) {
				// throw new CitizenNotFoundException("Citizen application does not exist");
				model.addAttribute("citizen", citizenModel);
			} else {
				model.addAttribute("msg", "Record not found");
			}
			model.addAttribute("appNo", appNo);
			return "create_case";
		} catch (Exception exception) {
			LOGGER.info("Exception : " + exception.getMessage());
			throw new RuntimeException(exception.getMessage());
		} finally {
			LOGGER.info("showCitizenData end");
		}
	}

	@RequestMapping("/show_generate_case")
	public String showGenerateCaseForm(@RequestParam("appNo") Integer appNo, HttpSession session, Model model) {
		LOGGER.info("showGenerateCaseForm start");
		try {
			LOGGER.debug("Application number " + appNo);
			CitizenPlanModel citizenPlanModel = new CitizenPlanModel();
			citizenPlanModel.setAppNo(appNo);
			citizenPlanModel = citizenPlanService.saveOrUpdateCitizenPlanInfo(citizenPlanModel);
			LOGGER.debug("CitizenPlanModel : " + citizenPlanModel);
			if (citizenPlanModel == null) {
				LOGGER.error("Unable to generate Case");
				model.addAttribute("msg", "Unable to generate Case");
				return "create_case";
			}
			session.setAttribute("citizenPlanModel", citizenPlanModel);
			return "redirect:/show_select_plan";
		} catch (Exception exception) {
			LOGGER.info("Exception : " + exception.getMessage());
			throw new RuntimeException(exception.getMessage());
		} finally {
			LOGGER.info("showGenerateCaseForm end");
		}

	}

	@RequestMapping("/show_select_plan")
	public String showSelectPlanFormPage(Model model) {
		LOGGER.info("showSelectPlanFormPage start");
		try {
			List<String> activePlans = planService.getAllActivePlans();
			if (activePlans == null) {
				LOGGER.error("No active plans");
				model.addAttribute("msg", "Unable to get Active plans");
			}
			LOGGER.debug("Active plans : " + activePlans);
			model.addAttribute("activePlans", activePlans);
			return "select_plan";
		} catch (Exception exception) {
			LOGGER.info("Exception : " + exception.getMessage());
			throw new RuntimeException(exception.getMessage());
		} finally {
			LOGGER.info("showSelectPlanFormPage end");
		}
	}

	@RequestMapping("/show_selected_plan")
	public String showSelectedPlanFormPage(@RequestParam("planName") String planName, Model model) {
		LOGGER.info("showSelectedPlanFormPage start");
		LOGGER.debug("Plan name selected : " + planName);
		LOGGER.info("showSelectedPlanFormPage end");
		return "redirect:/show_" + planName.toLowerCase() + "_plan";
	}

	@RequestMapping("/show_snap_plan")
	public String showSnapPlanFormPage(HttpSession session, Model model) {
		LOGGER.info("showSnapPlanFormPage start");
		SnapPlanData snapPlanData = new SnapPlanData();
		loadEmployedData(model);
		model.addAttribute("snapPlanData", snapPlanData);
		CitizenPlanModel citizenPlanModel = (CitizenPlanModel) session.getAttribute("citizenPlanModel");
		citizenPlanModel.setPlanId(planService.getPlanIdByName("Snap"));
		session.setAttribute("citizenPlanModel", citizenPlanModel);
		LOGGER.debug("Updated CitizenPlanModel in session : " + citizenPlanModel);
		LOGGER.info("showSnapPlanFormPage end");
		return "snap_plan";
	}

	private void loadEmployedData(Model model) {
		String isEmployed[] = { "Y", "N" };
		model.addAttribute("isEmployed", isEmployed);
	}

	@RequestMapping("/show_ed")
	public String showEDFormPage(@ModelAttribute("snapPlanData") SnapPlanData snapPlanData, HttpSession session,
			Model model) {
		LOGGER.info("showEDFormPage start");
		LOGGER.debug("SnapPlanData received: " + snapPlanData);
		session.setAttribute("planSelected", snapPlanData);
		session.setAttribute("planName", "Snap");
		LOGGER.info("showEDFormPage end");
		return "ed";
	}

	@RequestMapping("/determine_eligibility")
	public String ed(HttpSession session, Model model) {
		PlanInfo planInfo = new PlanInfo();
		CitizenPlanModel citizenPlanModel = (CitizenPlanModel) session.getAttribute("citizenPlanModel");
		SnapPlanData snapPlanData = (SnapPlanData) session.getAttribute("planSelected");

		EligibilityDetermination eligibilityDetermination = new EligibilityDetermination();
		snapPlanData.setPlanInfo(planInfo);
		CitigenData citigenData = new CitigenData();
		citigenData.setCaseNum(citizenPlanModel.getCaseNo());
		citigenData.setFirstName("Abc");
		citigenData.setDob("12-3-1991");
		citigenData.setGender("Male");
		citigenData.setLastName("Last");
		citigenData.setSsn("124545");
		citigenData.setPlanSelected("Snap");
		PlanDetails planDetails = new PlanDetails();
		planDetails.setSnapPlanData(snapPlanData);
		eligibilityDetermination.setCitigenData(citigenData);
		eligibilityDetermination.setPlanDetails(planDetails);

		planInfo = planService.findEligibility(eligibilityDetermination);
		if (planInfo.getPlanApproved() != null) {
			model.addAttribute("msg", "Plan approved");
		} else {
			model.addAttribute("msg", "Plan Denied");
		}
		return "ed";
	}
}
