package com.khacademy.khoffice.reserve_assets.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khacademy.khoffice.reserve_assets.models.ReserveAsstesDTO;
import com.khacademy.khoffice.reserve_assets.services.ReserveAsstesService;

@Controller("holding/ajax")
@RequestMapping("/reserve_assets")
public class AjaxController {
	private ReserveAsstesService reserveAsstesService;

	@Autowired
	@Required
	public void setReserveAsstesService(ReserveAsstesService reserveAsstesService) {
		this.reserveAsstesService = reserveAsstesService;
	}

	@RequestMapping(value = "/ajax_start", method = RequestMethod.GET)
	@ResponseBody
	public String getHoldingStart(HttpServletResponse response, @RequestParam("hAssets_no") String hAssets_no,@RequestParam("day") String day) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat transFormat1 = new SimpleDateFormat("HH");
		List<ReserveAsstesDTO> reserveAsstesListDTO = null;

		ArrayList<String> rTime = new ArrayList<String>();
		ArrayList<String> startTime = new ArrayList<String>();

		// String day = transFormat.format(today);
		Map<Object, Object> startMap = new HashMap<>();

		startMap.put("day", day);
		startMap.put("hAssets_no", hAssets_no);
		reserveAsstesListDTO = reserveAsstesService.getday(startMap);
		
		//startTime 없다면 9~18 까지 넣어주기
		if (reserveAsstesListDTO.size() == 0) {
			for (int i = 9; i < 18; i++) {
				startTime.add(i + "");
			}
		} else { 
			//예약이 존재한다면 endTime 에서 startTime 뺸 후 statTime 부터 뺸 시간 만큼 넣어준다 
			for (ReserveAsstesDTO reserveAsstesDTO : reserveAsstesListDTO) {

				Date start = reserveAsstesDTO.getStart_time();
				String start1 = transFormat1.format(start);
				int s = Integer.parseInt(start1);

				Date end = reserveAsstesDTO.getEnd_time();
				String end1 = transFormat1.format(end);
				int e = Integer.parseInt(end1);
				
				int r = e - s;
				for (int i = 0; i < r; i++) {
					rTime.add((s + i) + "");
				}
			}
			//starTime 넣어준다 9~18까지
			for (int i = 9; i < 18; i++) {

				startTime.add("" + i);
			}
			int add = 0;
			//startTime 에서 예약 불가능 한 시간을 startTime 에서 remove 시켜준다.
			for (String string : rTime) {
				startTime.remove(rTime.get(add));
				add++;
			}
		}
		System.out.println(startTime);

		JSONObject jso = new JSONObject();
		jso.put("data", startTime);
		response.setContentType("text/html;charset=UTF-8");
		return jso.toString();
	}

	@RequestMapping(value = "/ajax_end", method = RequestMethod.GET)
	@ResponseBody
	public String getHoldingEnd(HttpServletResponse response, @RequestParam("hAssets_no") String hAssets_no, @RequestParam("startTime") String startTime, @RequestParam("day") String day) throws Exception {
		ArrayList<String> endTime = new ArrayList<String>();
		List<ReserveAsstesDTO> reserveAsstesListDTO = null;
		Map<Object, Object> endMap = new HashMap<>();
		
		//startTime 시간 정해주기
		String start = day + " " + startTime;
		int star = Integer.parseInt(startTime);
		endMap.put("start_time", start);
		endMap.put("day", day);
		endMap.put("hAssets_no", hAssets_no);

		//startTime 시간 보다 늦은 시간 startTime 갖고오기
		List<String> end = reserveAsstesService.getEnd(endMap);
		
		//startTime 시간 보다 늦은시간이 없다면 startTime 이후로 시간 넣어주기
		if (end.size() == 0) {
			for (int i = star; i < 18; i++) {
				endTime.add((i +1)+ "");
			}
			
		} else { //start 시간보다 늦은시간이 존재 한다면 가장 빠른 startTime 시간까지 넣어주기
			String endt = end.get(0);

			int end1 = Integer.parseInt(endt);
			int en = end1 - star;
			for (int i = 0; i < en; i++) {
				endTime.add(((star + 1) + i) + "");

			} 
		}
		System.out.println(endTime);
		JSONObject jso = new JSONObject();
		jso.put("data", endTime);
		response.setContentType("text/html;charset=UTF-8");
		return jso.toString();
	}
}
