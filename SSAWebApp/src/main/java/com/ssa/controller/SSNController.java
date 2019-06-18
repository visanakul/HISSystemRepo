package com.ssa.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssa.exception.NoUserException;
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
	@Autowired
	private StateService stateService;
	/**
	 * For accessing User Data
	 */
	@Autowired
	private SSNUserService ssnUserService;

	/**
	 * Logger to log
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SSNController.class);

	/**
	 * Default Constructor
	 */
	public SSNController() {
		LOGGER.debug("***SSNController***");
	}

	/**
	 * Show SSN Enrollment form
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(ENROLL_GET_URL)
	public String showSSNForm(final Model model) {
		LOGGER.debug("***register request***");
		loadGenders(model);
		loadStates(model);
		SSNUser user = new SSNUser();
		model.addAttribute(MK_USER, user);
		return VW_ENROLL;
	}

	/**
	 * Loading state list in model
	 * 
	 * @param model
	 */
	private void loadStates(final Model model) {

		LOGGER.debug("***Loading state***");

		List<State> states = stateService.getAllStates();
		model.addAttribute(MK_STATES, states);
	}

	/**
	 * Loading genders list in model
	 * 
	 * @param model
	 */
	private void loadGenders(final Model model) {

		LOGGER.debug("***Loading genders***");

		String[] gens = { MALE, FEMALE };
		List<String> genders = Arrays.asList(gens);
		model.addAttribute(MK_GENDERS, genders);

	}

	/**
	 * Store the User data in USER_MASTER
	 * 
	 * @param multipartFile
	 * @param user
	 * @param bindingResult
	 * @param ra
	 * @param model
	 * @return
	 */
	@RequestMapping(value = ENROLL_POST_URL, method = RequestMethod.POST)
	public String saveSSNUserInfo(@RequestParam(PHOTO_FILE_PARAM) MultipartFile multipartFile,
			@Valid @ModelAttribute(MK_USER) SSNUser user, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {

		LOGGER.debug("***Enrolling user***");

		if (bindingResult.hasErrors()) {
			LOGGER.debug("....Incomplete form...." + bindingResult.toString());
			loadGenders(model);
			loadStates(model);
			return VW_ENROLL;
		}

		LOGGER.debug("User : " + user.toString());

		String fileName = multipartFile.getOriginalFilename();
		LOGGER.debug("File Name : " + fileName);

		try {
			byte[] photoData = multipartFile.getBytes();
			byte[] encodeBase64 = Base64.encodeBase64(photoData);
			String base64Encoded;
			base64Encoded = new String(encodeBase64, "UTF-8");
			user.setPhoto(base64Encoded);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Exception : " + e.toString());
		} catch (IOException e) {
			LOGGER.error("Exception : " + e.toString());
		}

		Integer ssn = ssnUserService.registerUser(user);
		LOGGER.debug("SSN : " + ssn);
		String ssnString = SSNUtil.getSSNFormat(ssn);

		redirectAttributes.addFlashAttribute(RA_KEY_ENROLL_SUCCESS, RA_VALUE_ENROLL_SUCCESS + ssnString);
		return REDIRECT_ENROLL_GET_URL;
	}

	/**
	 * Getting all users
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(SHOW_USERS_GET_URL)
	public String showAllSSNUsers(final Model model) {
		List<SSNUser> userModelList = ssnUserService.getAllUsers();

		if (userModelList.isEmpty()) {
			throw new NoUserException("No user registered");
		}
		model.addAttribute(MK_USER_LIST, userModelList);
		LOGGER.debug("Total records : " + userModelList.size());
		return VW_SHOW_USERS;
	}

}
