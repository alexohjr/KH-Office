package com.khacademy.khoffice.holding_assets.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.khacademy.khoffice.holding_assets.models.HoldingAssetsMemberDTO;
import com.khacademy.khoffice.holding_assets.services.HoldingAssetsService;

@Controller("holdingForm")
@RequestMapping("/holding_assets")
public class FormController {
	
	private HoldingAssetsService holdingAssetsService;

	@Autowired
	@Required
	public void setHoldingAssetsService(HoldingAssetsService holdingAssetsService) {
		this.holdingAssetsService = holdingAssetsService;
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String getAddForm() {
		return "tiles/holding_assets/add_form";
	}

	@RequestMapping(value="/form/{hAssets_no}",method=RequestMethod.GET)
	public String getEditForm(@PathVariable("hAssets_no")String hAssets_no, ModelMap model) {
		
		HoldingAssetsMemberDTO holdingAssetsMemberDTO = holdingAssetsService.getSelectHoldingAssetsOne(hAssets_no);
		
		model.addAttribute("holdingAssetsMemberDTO",holdingAssetsMemberDTO);
		
		return "tiles/holding_assets/edit_form";
	}
}
