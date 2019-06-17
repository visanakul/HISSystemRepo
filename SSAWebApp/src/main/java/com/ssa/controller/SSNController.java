package com.ssa.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

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

import static com.ssa.util.Constants.*;

@Controller
public class SSNController {

	@Autowired
	private StateService stateService;
	@Autowired
	private SSNUserService ssnUserService;
	
	private static Logger logger=LoggerFactory.getLogger(SSNController.class);
	
	public SSNController() {
		logger.debug("***SSNController***");
	}

	@RequestMapping(ENROLL_GET_URL)
	public String showSSNForm(Model model) {
		logger.debug("***register request***");
		loadGenders(model);
		loadStates(model);
		SSNUser user=new SSNUser();
		model.addAttribute(MK_USER, user);
		return VW_ENROLL;
	}

	private void loadStates(Model model) {
		
		logger.debug("***Loading state***");
		
		List<State> states=stateService.getAllStates();
		model.addAttribute(MK_STATES, states);
	}

	private void loadGenders(Model model) {
		
		logger.debug("***Loading genders***");
		
		String[] gens= {MALE,FEMALE};
		List<String> genders=Arrays.asList(gens);
		model.addAttribute(MK_GENDERS, genders);
		
	}
	
	@RequestMapping(value = ENROLL_POST_URL,method = RequestMethod.POST)
	public String saveSSNUserInfo(@RequestParam(PHOTO_FILE_PARAM) MultipartFile multipartFile ,@Valid @ModelAttribute(MK_USER) SSNUser user,BindingResult bindingResult,RedirectAttributes ra,Model model) {
		
		logger.debug("***Enrolling user***");
		
		if (bindingResult.hasErrors()) {
			logger.debug("....Incomplete form...."+bindingResult.toString());
			loadGenders(model);
			loadStates(model);
			return VW_ENROLL;
		}
		
		logger.debug("Mutipart : "+multipartFile.getClass().getName());
		logger.debug("User : "+user.toString());
	
		String fileName=multipartFile.getOriginalFilename();
		logger.debug("File Name : "+fileName);
		
		try {
			user.setPhoto(multipartFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		Integer ssn=ssnUserService.registerUser(user);
		logger.debug("SSN : "+ssn);
		String ssnString=SSNUtil.getSSNFormat(ssn);
		
		ra.addFlashAttribute(RA_KEY_ENROLL_SUCCESS, RA_VALUE_ENROLL_SUCCESS+ssnString);
		return REDIRECT_ENROLL_GET_URL;
	}
	
	@RequestMapping(SHOW_USERS_GET_URL)
	public String showAllSSNUsers(Model model) {
		List<SSNUser> userModelList=ssnUserService.getAllUsers();
		model.addAttribute(MK_USER_LIST, userModelList);
		if(userModelList.size()==0) {
			throw new NoUserException("No user registered");
		}
		logger.debug("Total records : "+userModelList.size());
		return VW_SHOW_USERS;
	}
	
	
}
