package com.ssa.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssa.model.SSNUser;
import com.ssa.model.State;
import com.ssa.service.StateService;

@Controller
public class SSNController {

	@Autowired
	private StateService service;
	
	public SSNController() {
		System.out.println("***SSNController***");
	}

	@RequestMapping("/register")
	public String showSSNForm(Model model) {
		loadGenders(model);
		loadStates(model);
		SSNUser user=new SSNUser();
		model.addAttribute("msg","Welcome!!!");
		model.addAttribute("user", user);
		return "ssnreg";
	}

	private void loadStates(Model model) {
		List<State> states=service.getAllStates();
		model.addAttribute("states", states);
	}

	private void loadGenders(Model model) {
		String[] gens= {"Male","Female"};
		List<String> genders=Arrays.asList(gens);
		model.addAttribute("genders", genders);
		
	}
	
	
}
