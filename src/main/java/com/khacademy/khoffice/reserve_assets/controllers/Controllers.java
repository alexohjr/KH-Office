package com.khacademy.khoffice.reserve_assets.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.khacademy.khoffice.holding_assets.models.HoldingAssetsMemberDTO;
import com.khacademy.khoffice.reserve_assets.models.HoldingReserveDTO;
import com.khacademy.khoffice.reserve_assets.models.ReserveAsstesDTO;
import com.khacademy.khoffice.reserve_assets.models.ReserveMyMemberDTO;
import com.khacademy.khoffice.reserve_assets.services.ReserveAsstesService;

@Controller("reserve")
@RequestMapping("/reserve_assets")
public class Controllers {

	private ReserveAsstesService reserveAsstesService;

	@Autowired
	@Required
	public void setReserveAsstesService(ReserveAsstesService reserveAsstesService) {
		this.reserveAsstesService = reserveAsstesService;
	}

	// reserve_assets/list.jsp
	@RequestMapping(method = RequestMethod.GET)
	public String getList(HttpServletRequest request, ModelMap model, HttpSession session) {

		// session 처리해야함 !!!
		String member_no = String.valueOf(session.getAttribute("session_memberNo"));
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null || pageNum == "") {
			pageNum = "1";
		}
		String day = request.getParameter("day");
		if (day == null || day == "") {
			SimpleDateFormat transFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date();
			day = transFormat1.format(today);
		}
		ArrayList<String> member = new ArrayList<String>();

		int pageSize = 10; // 한페이지의 글의 개수
		int currentPage = Integer.parseInt(pageNum);
		int start = (currentPage - 1) * pageSize + 1; // 한 페이지의 시작글 번호
		int end = currentPage * pageSize; // 한 페이지의 마지막 글번호
		int reserveCount = 0; // 총 건수

		SimpleDateFormat transFormat1 = new SimpleDateFormat("HH");
		List<ReserveMyMemberDTO> reserveMyMemberListDTO = null;
		List<ReserveAsstesDTO> reserveAsstesListDTO = null;
		List<HoldingReserveDTO> holdingReserveList = new ArrayList<HoldingReserveDTO>();

		List<HoldingAssetsMemberDTO> holding = null;
		int hodingCount = reserveAsstesService.holdingAssetsAllCount();
		holding = reserveAsstesService.getHoldingAssetsListAll();
		Map<Object, Object> rrr = new HashMap<>();

		// 자산리스트 수 만큼 for문 돌림
		for (int i = 0; i < hodingCount; i++) {
			HoldingReserveDTO holdingReserveDTO = new HoldingReserveDTO();
			String hAssets_no = holding.get(i).gethAssets_no();
			int count = reserveAsstesService.reserveAssetsCount(hAssets_no);
			if (count > 0) {
				holdingReserveDTO.setH_name(holding.get(i).getH_name());
				holdingReserveDTO.sethAssets_no(holding.get(i).gethAssets_no());
				ArrayList<Object> time = new ArrayList<Object>();
				ArrayList<Object> rTime = new ArrayList<Object>();
				for (int j = 1; j < 18; j++) {

					time.add(j);

				}
				Map<Object, Object> startMap = new HashMap<>();

				startMap.put("day", day);
				startMap.put("hAssets_no", holding.get(i).gethAssets_no());
				reserveAsstesListDTO = reserveAsstesService.getdayMember(startMap);

				for (ReserveAsstesDTO reserveAsstesDTO : reserveAsstesListDTO) {

					Date startTime = reserveAsstesDTO.getStart_time();
					String start1 = transFormat1.format(startTime);
					int s = Integer.parseInt(start1);

					Date endTime = reserveAsstesDTO.getEnd_time();
					String end1 = transFormat1.format(endTime);
					int e = Integer.parseInt(end1);
					int r = e - s;
					for (int t = 0; t < r; t++) {
						time.set((s + t) - 1, reserveAsstesDTO.getName());
					}
				}
				for (int r = 1; r < 9; r++) {

					rTime.add(r);
				}
				int ad = 0;
				for (Object obj : rTime) {
					time.remove(rTime.get(ad));
					ad++;
				}
				for (int it = 0; it < 9; it++) {
					if (time.get(it) instanceof Integer) {
						time.set(it, "-");
					}
				}

				holdingReserveDTO.setReserve(time);

			} else {
				holdingReserveDTO.setH_name(holding.get(i).getH_name());
				holdingReserveDTO.sethAssets_no(holding.get(i).gethAssets_no());
				ArrayList<Object> time = new ArrayList<Object>();
				for (int j = 0; j < 9; j++) {

					time.add("-");

				}

				holdingReserveDTO.setReserve(time);
			}
			holdingReserveList.add(i, holdingReserveDTO);
			System.out.println(holdingReserveDTO);
		}

		Map<Object, Object> map = new HashMap<>();

		map.put("start", start);
		map.put("end", end);
		map.put("member_no", member_no);
		reserveCount = reserveAsstesService.myResuerCount(member_no);
		reserveMyMemberListDTO = reserveAsstesService.getMyReserveList(map);

		for (int i = 0; i < reserveCount; i++) {
			String sub = reserveMyMemberListDTO.get(i).getPurpose();
			if (sub.length() > 20) {
				reserveAsstesListDTO.get(i).setPurpose(sub.substring(0, 20));
			}
		}

		model.addAttribute("reserveMyMemberListDTO", reserveMyMemberListDTO);
		model.addAttribute("holdingReserveList", holdingReserveList);
		model.addAttribute("reserveCount", reserveCount);
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("day", day);

		return "tiles/reserve_assets/list";
	}

	@RequestMapping(value = "{rAssets_no}", method = RequestMethod.POST)
	public String update(@PathVariable("rAssets_no") String rAssets_no, HttpServletRequest request) {

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null || pageNum == "") {
			pageNum = "1";
		}
		String day = request.getParameter("day");
		if (day == null || day == "") {
			SimpleDateFormat transFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date();
			day = transFormat1.format(today);
		}
		reserveAsstesService.myReserveDelete(rAssets_no);

		return "redirect:/reserve_assets?pageNum=" + pageNum + "&day=" + day;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String add(ReserveAsstesDTO reserveAsstesDTO, HttpSession session, HttpServletRequest request)
			throws ParseException {

		// session 처리해야함!!!
		String member_no = String.valueOf(session.getAttribute("session_memberNo"));

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null || pageNum == "") {
			pageNum = "1";
		}
		String day = request.getParameter("day");
		if (day == null || day == "") {
			SimpleDateFormat transFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date();
			day = transFormat1.format(today);
		}

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd kk");

		String start = reserveAsstesDTO.getReservationDate() + " " + reserveAsstesDTO.getStart();
		String end = reserveAsstesDTO.getReservationDate() + " " + reserveAsstesDTO.getEnd();
		Date start_time = transFormat.parse(start);
		Date end_time = transFormat.parse(end);
		reserveAsstesDTO.setStart_time(start_time);
		reserveAsstesDTO.setEnd_time(end_time);
		reserveAsstesDTO.setMember_no(member_no);
		reserveAsstesService.addReserveAssets(reserveAsstesDTO);

		return "redirect:/reserve_assets?pageNum=" + pageNum + "&day=" + day;
	}

}
