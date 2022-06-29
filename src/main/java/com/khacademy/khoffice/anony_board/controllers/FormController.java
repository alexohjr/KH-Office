package com.khacademy.khoffice.anony_board.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller("anony_boardForm")
@RequestMapping("/anony_board")
public class FormController {
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String getAddForm() {
		return "tiles/anony_board/add_form";
	}
	
}
