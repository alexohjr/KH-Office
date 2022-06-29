package com.khacademy.khoffice.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("test")
public class Test {
	
	@RequestMapping(value="/**")
	public String home(Model model) {
		return "home";
	}  

}
