package com.ssa.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

import com.ssa.model.SSNUser;
import com.ssa.model.State;
import com.ssa.service.SSNUserService;
import com.ssa.service.StateService;

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

	@RequestMapping("/register")
	public String showSSNForm(Model model) {
		logger.debug("***register request***");
		loadGenders(model);
		loadStates(model);
		SSNUser user=new SSNUser();
		model.addAttribute("user", user);
		return "ssnreg";
	}

	private void loadStates(Model model) {
		
		logger.debug("***Loading state***");
		
		List<State> states=stateService.getAllStates();
		model.addAttribute("states", states);
	}

	private void loadGenders(Model model) {
		
		logger.debug("***Loading genders***");
		
		String[] gens= {"Male","Female"};
		List<String> genders=Arrays.asList(gens);
		model.addAttribute("genders", genders);
		
	}
	
	@RequestMapping(value = "/enroll",method = RequestMethod.POST)
	public String saveSSNUserInfo(@RequestParam("photoFile") MultipartFile multipartFile ,@ModelAttribute("user") SSNUser user,BindingResult bindingResult,RedirectAttributes ra,Model model) {
		
		logger.debug("***Enrolling user***");
		
		if (bindingResult.hasErrors()) {
			logger.debug("....Incomplete form...."+bindingResult.toString());
			loadGenders(model);
			loadStates(model);
			return "ssnreg";
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
//		user.setCreationDate(new Date());
//		user.setUpdateDate(new Date());
//		
		Integer ssn=ssnUserService.registerUser(user);
		logger.debug("SSN : "+ssn);
		String ssnString=new StringBuilder(ssn+"").insert(3, '-').insert(6, '-').toString();
		ra.addFlashAttribute("msg", "SSN Enrollment completed successfully with "+ssnString);
		return "redirect:/register";
	}
	
	@RequestMapping(value = "showall")
	public String showAllSSNUsers(Model model) {
		List<SSNUser> userModelList=ssnUserService.getAllUsers();
		model.addAttribute("userModelList", userModelList);
		logger.debug("Total records : "+userModelList.size());
		return "showallusers";
	}
	
	
}
