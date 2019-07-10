package com.ssa.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.validation.Valid;

import org.apache.tomcat.util.codec.binary.Base64;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssa.exception.NoUserException;
import com.ssa.exception.StatesNotFoundException;
import com.ssa.model.SSNUser;
import com.ssa.model.State;
import com.ssa.service.SSNUserService;
import com.ssa.service.StateService;
import com.ssa.util.SSNUtil;

import static com.ssa.util.ConstantUtils.*;

/**
 * Controller for Handling requests
 * 
 * @author VISHAL
 *
 */
@Controller
public class SSNController {

	/**
	 * For accessing State Data
	 */
	@Autowired(required = true)
	private StateService stateService;
	/**
	 * For accessing User Data
	 */
	@Autowired(required = true)
	private SSNUserService ssnUserService;

	/**
	 * Logger to log
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SSNController.class);

	/**
	 * Default Constructor
	 */
	public SSNController() {
		LOGGER.info("***SSNController***");
	}

	/**
	 * Show SSN Enrollment form
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(ENROLL_GET_URL)
	public String showSSNForm(final Model model) {
		LOGGER.info("***Show Enroll form request start***");
		loadGenders(model);
		loadStates(model);
		SSNUser user = new SSNUser();
		model.addAttribute(USER_MODEL_KEY, user);
		LOGGER.info("***Show Enroll form request end***");
		return ENROLL_VIEW;
	}
//
//	/**
//	 * Loading model attribute states
//	 * @return
//	 */
//	@ModelAttribute(name = MK_STATES)
//	public List<State> loadStateData() {
//		LOGGER.info("State Data Loading...");
//		List<State> statesList = null;
//		try {
//			statesList = stateService.getAllStates();
//			if (statesList != null && !statesList.isEmpty()) {
//				LOGGER.debug("Got state list : " + statesList);
//			} else {
//				LOGGER.warn("State data not available");
//				throw new StatesNotFoundException("No state data");
//			}
//		} catch (Exception exception) {
//			LOGGER.error(exception.getMessage());
//		}
//
//		LOGGER.info("State Data Loaded...");
//		return statesList;
//	}
//	
//	/**
//	 * Loading model attribute genders
//	 * @return
//	 */
//	@ModelAttribute(name = MK_GENDERS)
//	public String[] loadGenderData() {
//		LOGGER.info("Gender Data Loading...");
//		String[] gens = { MALE, FEMALE };
//		LOGGER.info("Gender Data Loaded...");
//		return gens;
//	}


	/**
	 * Loading state list in model
	 * 
	 * @param model
	 */
	private void loadStates(final Model model) {

		LOGGER.info("***State loading start***");
		List<State> statesList = null;
		try {
			statesList = stateService.getAllStates();
			if (statesList != null && !statesList.isEmpty()) {
				LOGGER.debug("Got state list : " + statesList);
				model.addAttribute(STATES_MODEL_KEY, statesList);
			} else {
				LOGGER.warn("State data not available");
				throw new StatesNotFoundException("No state data");
			}
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
		}
		LOGGER.info("***State loading end***");
	}

	/**
	 * Loading genders list in model
	 * 
	 * @param model
	 */
	private void loadGenders(final Model model) {

		LOGGER.info("***Gender Loading start***");
		final String[] gens = { MALE, FEMALE };
		model.addAttribute(GENDERS_MODEL_KEY, gens);
		LOGGER.info("***Gender Loading end***");

	}

	/**
	 * Store the User data in USER_MASTER
	 * 
	 * @param multipartFile
	 * @param user
	 * @param bindingResult
	 * @param redirectAttributes
	 * @param model
	 * @return
	 */
	@RequestMapping(value = ENROLL_POST_URL, method = RequestMethod.POST)
	public String saveSSNUserInfo(@RequestParam(PHOTO_FILE_REQUEST_PARAM) MultipartFile multipartFile,
			@Valid @ModelAttribute(USER_MODEL_KEY) SSNUser user, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {

		LOGGER.info("***Enrolling user start***");

		if(!validateData(bindingResult, model)) {
			return ENROLL_VIEW;
		}

		LOGGER.debug("User data Received: " + user.toString());

		String  base64Encoded=convertImageToBase64String(multipartFile);
		user.setPhoto(base64Encoded);
		Integer ssn = ssnUserService.registerUser(user);
		LOGGER.debug("SSN : " + ssn);
		String ssnString = SSNUtil.getSSNFormat(ssn);

		redirectAttributes.addFlashAttribute(ENROLL_SUCCESS_REDIRECT_KEY, ENROLL_SUCCESS_REDIRECT_VALUE + ssnString);

		LOGGER.info("***Enrolling user end***");

		return REDIRECT_ENROLL_GET_URL;
	}

	/**
	 * Converting multipart to Base64 String
	 * @param multipartFile
	 * @return
	 */
	private String convertImageToBase64String(final MultipartFile multipartFile) {
		final String fileName = multipartFile.getOriginalFilename();
		LOGGER.debug("Received File Name : " + fileName);

		try {
			LOGGER.info("Converting multipart to base64Encode string start");
			byte[] photoData = multipartFile.getBytes();
			byte[] encodeBase64 = Base64.encodeBase64(photoData);
			String base64Encoded = new String(encodeBase64, "UTF-8");
			LOGGER.info("Converting multipart to base64Encode string end");
			return  base64Encoded;
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Exception : " + e.toString());
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			LOGGER.error("Exception : " + e.toString());
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * Server side validation
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	private boolean validateData(BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			LOGGER.error("***Server side validation fails***");
			loadGenders(model);
			loadStates(model);
			return false;
		}
		LOGGER.info("***Server side validation success***");
		return true;
	}

	/**
	 * Getting all users
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(SHOW_USERS_GET_URL)
	public String showAllSSNUsers(Model model) {
		LOGGER.info("Show All user request start");
		List<SSNUser> userModelList = ssnUserService.getAllUsers();

		if (userModelList==null || userModelList.isEmpty()) {
			throw new NoUserException("No user registered");
		}
		model.addAttribute(USER_LIST_MODEL_KEY, userModelList);
		LOGGER.debug("Total records : " + userModelList.size());
		LOGGER.info("Show All user request end");
		return SHOW_USERS_VIEW;
	}
	
	@RequestMapping("/show")
	public String showUserTable() {
		return "show_table";
	}
	
	@RequestMapping("/getlist")
	public @ResponseBody List<SSNUser> showAll() {
		return ssnUserService.getAllUsers();
	}

}
