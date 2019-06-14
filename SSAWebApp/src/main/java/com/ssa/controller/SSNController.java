package com.ssa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SSNController {

	public SSNController() {
		System.out.println("***SSNController***");
	}

	@RequestMapping("/register")
	public String showSSNForm(Model model) {
		model.addAttribute("msg","Welcome!!!");
		return "ssnreg";
	}
}
