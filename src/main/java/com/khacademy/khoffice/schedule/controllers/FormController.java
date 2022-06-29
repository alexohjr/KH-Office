package com.khacademy.khoffice.schedule.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("scheduleForm")
@RequestMapping("/schedule")
public class FormController {
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String getAddForm() {
		System.out.println("form 진입");
		return "tiles/schedule/add_form";
	}
	
	

}
