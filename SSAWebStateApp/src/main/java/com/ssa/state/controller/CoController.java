package com.ssa.state.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CoController {
	@RequestMapping("/show_co")
	public String showCoPage() {
		return "copage";
	}
	@RequestMapping("/start_co")
	public @ResponseBody String startCoModule() {
		return "Success";
	}
}
