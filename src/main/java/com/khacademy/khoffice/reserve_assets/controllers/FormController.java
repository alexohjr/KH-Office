package com.khacademy.khoffice.reserve_assets.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.khacademy.khoffice.holding_assets.models.HoldingAssetsMemberDTO;
import com.khacademy.khoffice.reserve_assets.services.ReserveAsstesService;

@Controller("reserveForm")
@RequestMapping("/reserve_assets")
public class FormController {
	
	private ReserveAsstesService reserveAsstesService;

	@Autowired
	@Required
	public void setReserveAsstesService(ReserveAsstesService reserveAsstesService) {
		this.reserveAsstesService = reserveAsstesService;
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String getAddForm(ModelMap model, @RequestParam("pageNum")String pageNum,@RequestParam("day")String day ) {
		
		//자산리스트
		List<HoldingAssetsMemberDTO> holdingAssetsList = null;
		holdingAssetsList = reserveAsstesService.getHoldingAssetsListAll();
		

		model.addAttribute("HoldingAssetsList", holdingAssetsList);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("day", day);

		return "tiles/reserve_assets/add_form";
	}

	

}
