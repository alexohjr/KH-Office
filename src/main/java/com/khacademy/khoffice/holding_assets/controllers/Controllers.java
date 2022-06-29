package com.khacademy.khoffice.holding_assets.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.khacademy.khoffice.holding_assets.models.HoldingAssetsDTO;
import com.khacademy.khoffice.holding_assets.models.HoldingAssetsMemberDTO;
import com.khacademy.khoffice.holding_assets.services.HoldingAssetsService;

@Controller("holding")
@RequestMapping("/holding_assets")
public class Controllers {

	private HoldingAssetsService holdingAssetsService;

	@Autowired
	@Required
	public void setHoldingAssetsService(HoldingAssetsService holdingAssetsService) {
		this.holdingAssetsService = holdingAssetsService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getList(HttpServletRequest request, ModelMap model) {
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null || pageNum == "") {
			pageNum = "1";
		}
		int pageSize = 10; // 한페이지의 글의 개수
		int currentPage = Integer.parseInt(pageNum);
		int start = (currentPage - 1) * pageSize + 1; // 한 페이지의 시작글 번호
		int end = currentPage * pageSize; // 한 페이지의 마지막 글번호
		int count = 0; // 총 건수
		int number = 0; // 순번

		String keyword = request.getParameter("keyword");
		Map<Object, Object> map = new HashMap<>();

		map.put("start", start);
		map.put("end", end);
		List<HoldingAssetsMemberDTO> HoldingAssetsList = null;
		if (keyword == null || keyword == "") {
			count = holdingAssetsService.holdingAssetsAllCount();
			HoldingAssetsList = holdingAssetsService.getHoldingAssetsListAll(map);
			number = count;
			model.addAttribute("HoldingAssetsList", HoldingAssetsList);
			model.addAttribute("number", number);
			model.addAttribute("start", start);
			model.addAttribute("end", end);
			model.addAttribute("count", count);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("pageNum", pageNum);

			//return "tiles/holding_assets/list?pageNum=" + pageNum;
			
			return "tiles/holding_assets/list";
		} else {
			map.put("keyword", keyword);
			count = holdingAssetsService.holdingAssetsSearchCount(keyword);
			HoldingAssetsList = holdingAssetsService.getSelectHoldingAssetsSearch(map);
			number = count;
			model.addAttribute("keyword", keyword);
			model.addAttribute("HoldingAssetsList", HoldingAssetsList);
			model.addAttribute("number", number);
			model.addAttribute("start", start);
			model.addAttribute("end", end);
			model.addAttribute("count", count);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("pageNum", pageNum);

			//return "tiles/holding_assets/list?pageNum=" + pageNum + "&keyowrd=" + keyword;
			
			return "tiles/holding_assets/list";

		}

	}

	@RequestMapping(method = RequestMethod.POST)
	public String add(HttpServletRequest request) {

		String member_no = request.getParameter("member_no");

		HoldingAssetsDTO holdingAssetsDTO = new HoldingAssetsDTO();
		holdingAssetsDTO.setMember_no(member_no);
		holdingAssetsDTO.setName(request.getParameter("name"));


		holdingAssetsService.addHoldingAssets(holdingAssetsDTO);

		return "redirect:/holding_assets";
	}

	@RequestMapping(value = "/{hAssets_no}", method = RequestMethod.POST)
	public String update(@PathVariable("hAssets_no") String hAssets_no, HttpServletRequest request) {
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null || pageNum == "") {
			pageNum = "1";
		}
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyowrd");
		if (keyword == null || keyword == "") {
			keyword = "";
		}
		if (type.equals("delete")) {
			holdingAssetsService.deleteHoldingAssets(hAssets_no);

		} else if (type.equals("edit")) {
			
			HoldingAssetsDTO holdingAssetsDTO = new HoldingAssetsDTO();
			holdingAssetsDTO.sethAssets_no(hAssets_no);
			holdingAssetsDTO.setName(request.getParameter("name"));
			holdingAssetsDTO.setMember_no(request.getParameter("member_no"));
			holdingAssetsService.editHoldingAssets(holdingAssetsDTO);
//?keyword=" + keyword + "&pageNum=" + pageNum;
		}

		return "redirect:/holding_assets?keyword=" + keyword + "&pageNum=" + pageNum;
	}

}
