package com.khacademy.khoffice.address.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khacademy.khoffice.address.services.AddressService;
import com.khacademy.khoffice.member.models.MemberDTO;
import com.khacademy.khoffice.member.services.MemberService;

@Controller("addressAjaxController")
@RequestMapping("/address")
public class AjaxController {
	private AddressService addressService;
	
	@Autowired
	@Required
	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}  
	
	// 즐겨찾기
	@RequestMapping(value="/ajax/{addressNo}",method=RequestMethod.POST, produces="text/plain;charset=utf-8")
	@ResponseBody
	public String update(
		HttpServletRequest request,
		HttpServletResponse response,
		@PathVariable("addressNo") int addressNo,
		@RequestParam(value="page_num",defaultValue="1")String pageNum,
		@RequestParam(defaultValue="")String keyword,
		@RequestParam("is_bookmark") int isBookmark
	) {
		
		response.setContentType("text/html;charset=utf-8");
		
		//세션에 저장된 값 받아오기
		HttpSession session = request.getSession();	
		int memberNo = (int) session.getAttribute("session_memberNo");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_no", memberNo);
		map.put("address_no",addressNo);
		boolean result = false;
		
		if(isBookmark == 0) { 
			result =  addressService.insertbookmark(map);
		} else if(isBookmark == 1) {
			result =  addressService.deleteBookmark(map);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", result);
		
		return jsonObject.toString();
	}
	
	
	
}