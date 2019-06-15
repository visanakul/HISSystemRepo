package com.ssa.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
	
	public SSNController() {
		System.out.println("***SSNController***");
	}

	@RequestMapping("/register")
	public String showSSNForm(Model model) {
		loadGenders(model);
		loadStates(model);
		SSNUser user=new SSNUser();
		model.addAttribute("user", user);
		return "ssnreg";
	}

	private void loadStates(Model model) {
		List<State> states=stateService.getAllStates();
		model.addAttribute("states", states);
	}

	private void loadGenders(Model model) {
		String[] gens= {"Male","Female"};
		List<String> genders=Arrays.asList(gens);
		model.addAttribute("genders", genders);
		
	}
	
	@RequestMapping(value = "/enroll",method = RequestMethod.POST)
	public String saveSSNUserInfo(@RequestParam("photoFile") MultipartFile multipartFile ,@ModelAttribute("user") SSNUser user,BindingResult bindingResult,RedirectAttributes ra,Model model) {
	
		if (bindingResult.hasErrors()) {
			System.out.println("....Incomplete form...."+bindingResult.toString());
			loadGenders(model);
			loadStates(model);
			return "ssnreg";
		}
		
		System.out.println("Mutipart : "+multipartFile.getClass().getName());
		System.out.println(user);
	
		String fileName=multipartFile.getOriginalFilename();
		System.out.println("File Name : "+fileName);
		
		try {
			user.setPhoto(multipartFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		user.setCreationDate(new Date());
		user.setUpdateDate(new Date());
		
		Integer ssn=ssnUserService.registerUser(user);
		System.out.println("SSN : "+ssn);
		String ssnString=new StringBuilder(ssn+"").insert(3, '-').insert(6, '-').toString();
		ra.addFlashAttribute("msg", "SSN Enrollment completed successfully with "+ssnString);
		return "redirect:/register";
	}
	
	@RequestMapping(value = "showall")
	public String showAllSSNUsers(Model model) {
		List<SSNUser> userModelList=ssnUserService.getAllUsers();
		model.addAttribute("userModelList", userModelList);
		System.out.println("Total records : "+userModelList.size());
		return "showallusers";
	}
	
	
}
