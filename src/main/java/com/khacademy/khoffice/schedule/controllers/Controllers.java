package com.khacademy.khoffice.schedule.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khacademy.khoffice.schedule.models.ScheduleDTO;
import com.khacademy.khoffice.schedule.models.ScheduleListDTO;
import com.khacademy.khoffice.schedule.services.ScheduleService;

@Controller("schedule")
@RequestMapping("/schedule")
public class Controllers {

	private ScheduleService scheduleService;

	@Autowired
	@Required
	public void setScheduleService(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getList(ModelMap model, HttpServletRequest request, HttpSession session) {

		// 멤버처리 주석
		String member_no = String.valueOf(session.getAttribute("session_memberNo"));

		String strYear = request.getParameter("year");
		String strMonth = request.getParameter("month");

		Calendar cal = Calendar.getInstance();

		int year = cal.get(Calendar.YEAR); // 오늘 년 월 일
		int month = cal.get(Calendar.MONTH);

		int today_year = cal.get(Calendar.YEAR);
		int today_month = cal.get(Calendar.MONTH);
		int today_date = cal.get(Calendar.DATE);

		if (strYear != null) {
			year = Integer.parseInt(strYear);
			month = Integer.parseInt(strMonth);
		}

		// 년도/월 셋팅
		cal.set(year, month, 1);

		int startDay = cal.getMinimum(java.util.Calendar.DATE); // 그 달이 언제부터?
		int endDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH); // 그 달이 언제까지?
		int start = cal.get(java.util.Calendar.DAY_OF_WEEK); // 1일이 언제부터 시작인지
		int newLine = 0;

		// 오늘 날짜 저장.
		Calendar todayCal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String intToday = sdf.format(todayCal.getTime());

		SimpleDateFormat hstart = new SimpleDateFormat("yyyy/MM/dd");

		int vacation_number = 1;

		List<ScheduleDTO> scheduleDTOList = null;
		List<ScheduleDTO> projectDTOList = null;
		List<ScheduleDTO> vacationDTOList = null;

		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("member_no", member_no);

		scheduleDTOList = scheduleService.scheduleList(map);
		projectDTOList = scheduleService.projectList(map);
		vacationDTOList = scheduleService.vacationList(map);

		int scheduleCount = scheduleService.scheduleCount(map);
		int projectCount = scheduleService.projectCount(map);
		int vacationCount = scheduleService.vacationCount(map);

		List<ScheduleListDTO> scheduleListDTO = new ArrayList<ScheduleListDTO>();


		for (int i = 0; i < projectCount; i++) {

			Date startdate = projectDTOList.get(i).getStart_date();
			Date enddate = projectDTOList.get(i).getEnd_date();
			// 시간차이를 시간,분,초를 곱한 값으로 나누면 하루 단위가 나옴
			long diff = enddate.getTime() - startdate.getTime();
			int rrr = (int) (diff / (24 * 60 * 60 * 1000));
			if (rrr == 0) {
				ScheduleListDTO list = new ScheduleListDTO();
				list.setH_num(projectDTOList.get(i).getSchedule_no());
				list.setH_name(projectDTOList.get(i).getSubject());
				list.setStatus("2");
				list.setH_start(projectDTOList.get(i).getStart_date());
				list.setH_end(projectDTOList.get(i).getEnd_date());
				list.setS_date(hstart.format(projectDTOList.get(i).getStart_date()));
				scheduleListDTO.add(list);

			} else {
				for (int j = 0; j < rrr + 1; j++) {

					ScheduleListDTO list = new ScheduleListDTO();
					list.setH_num(projectDTOList.get(i).getSchedule_no());
					if(j==0) {
						list.setH_name("시작 "+projectDTOList.get(i).getSubject());
					}else if(j==rrr) {
						list.setH_name("종료 "+projectDTOList.get(i).getSubject());
					}else {
						list.setH_name(projectDTOList.get(i).getSubject());
					}
					
					
					list.setStatus("2");
					list.setH_start(projectDTOList.get(i).getStart_date());
					list.setH_end(projectDTOList.get(i).getEnd_date());
					Date hDate = projectDTOList.get(i).getStart_date();

					cal.setTime(hDate);
					cal.add(Calendar.DATE, j);
					list.setS_date(hstart.format(cal.getTime()));
					scheduleListDTO.add(list);
				}
			}

		}

		for (int i = 0; i < vacationCount; i++) {

			Date startdate = vacationDTOList.get(i).getStart_date();
			Date enddate = vacationDTOList.get(i).getEnd_date();
			// 시간차이를 시간,분,초를 곱한 값으로 나누면 하루 단위가 나옴
			long diff = enddate.getTime() - startdate.getTime();
			int rrr = (int) (diff / (24 * 60 * 60 * 1000));
			if (rrr == 0) {
				ScheduleListDTO list = new ScheduleListDTO();
				list.setH_num(vacationDTOList.get(i).getSchedule_no());
				list.setH_name(vacationDTOList.get(i).getSubject());
				list.setStatus("3");
				list.setH_start(vacationDTOList.get(i).getStart_date());
				list.setH_end(vacationDTOList.get(i).getEnd_date());
				list.setS_date(hstart.format(vacationDTOList.get(i).getStart_date()));
				scheduleListDTO.add(list);

			} else {
				for (int j = 0; j < rrr + 1; j++) {

					ScheduleListDTO list = new ScheduleListDTO();
					list.setH_num(vacationDTOList.get(i).getSchedule_no());
					list.setH_name(vacationDTOList.get(i).getSubject());
					list.setStatus("3");
					list.setH_start(vacationDTOList.get(i).getStart_date());
					list.setH_end(vacationDTOList.get(i).getEnd_date());
					Date hDate = vacationDTOList.get(i).getStart_date();

					cal.setTime(hDate);
					cal.add(Calendar.DATE, j);
					list.setS_date(hstart.format(cal.getTime()));
					scheduleListDTO.add(list);
				}
			}

		}
		
		for (int i = 0; i < scheduleCount; i++) {

			Date startdate = scheduleDTOList.get(i).getStart_date();
			Date enddate = scheduleDTOList.get(i).getEnd_date();
			// 시간차이를 시간,분,초를 곱한 값으로 나누면 하루 단위가 나옴
			long diff = enddate.getTime() - startdate.getTime();
			int rrr = (int) (diff / (24 * 60 * 60 * 1000));
			if (rrr == 0) {
				ScheduleListDTO list = new ScheduleListDTO();
				list.setH_num(scheduleDTOList.get(i).getSchedule_no());
				list.setH_name(scheduleDTOList.get(i).getSubject());
				list.setStatus("1");
				list.setH_start(scheduleDTOList.get(i).getStart_date());
				list.setH_end(scheduleDTOList.get(i).getEnd_date());
				list.setS_date(hstart.format(scheduleDTOList.get(i).getStart_date()));
				scheduleListDTO.add(list);

			} else {
				for (int j = 0; j < rrr + 1; j++) {

					ScheduleListDTO list = new ScheduleListDTO();
					list.setH_num(scheduleDTOList.get(i).getSchedule_no());
					list.setH_name(scheduleDTOList.get(i).getSubject());
					list.setStatus("1");
					list.setH_start(scheduleDTOList.get(i).getStart_date());
					list.setH_end(scheduleDTOList.get(i).getEnd_date());
					Date hDate = scheduleDTOList.get(i).getStart_date();

					cal.setTime(hDate);
					cal.add(Calendar.DATE, j);
					list.setS_date(hstart.format(cal.getTime()));

					scheduleListDTO.add(list);

				}
			}

		}

		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("startDay", startDay);
		model.addAttribute("endDay", endDay);
		model.addAttribute("start", start);
		model.addAttribute("newLine", newLine);
		model.addAttribute("intToday", intToday);
		model.addAttribute("list", scheduleListDTO);
		model.addAttribute("today_year", today_year);
		model.addAttribute("today_month", today_month);
		model.addAttribute("today_date", today_date);

		model.addAttribute("vacation_number", vacation_number);

		return "tiles/schedule/schedule";

	}

	/*@RequestMapping(method = RequestMethod.POST)
	public String add(HttpSession session, HttpServletRequest request) throws ParseException {
		System.out.println("저장하기 진입!");
		// 주석처리 로그인
		String member_no = String.valueOf(session.getAttribute("session_memberNo"));
		ScheduleDTO scheduleDTO = new ScheduleDTO();

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

		String start = request.getParameter("start_date");
		String end = request.getParameter("end_date");
		Date start_date = transFormat.parse(start);
		Date end_date = transFormat.parse(end);

		scheduleDTO.setMember_no(member_no);
		scheduleDTO.setStart_date(start_date);
		scheduleDTO.setEnd_date(end_date);
		scheduleDTO.setSubject(request.getParameter("subject"));
		scheduleService.add(scheduleDTO);

		return "tiles/schedule/ss";
	}*/
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String add(HttpSession session, HttpServletResponse response,@RequestParam("name")String name,@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate) throws ParseException {
		// 주석처리 로그인
		String member_no = String.valueOf(session.getAttribute("session_memberNo"));
		ScheduleDTO scheduleDTO = new ScheduleDTO();

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

		String start = startDate;
		String end = endDate;
		Date start_date = transFormat.parse(start);
		Date end_date = transFormat.parse(end);

		scheduleDTO.setMember_no(member_no);
		scheduleDTO.setStart_date(start_date);
		scheduleDTO.setEnd_date(end_date);
		scheduleDTO.setSubject(name);
		int i = scheduleService.add(scheduleDTO);
		JSONObject jso = new JSONObject();
		
		jso.put("data", i);
		response.setContentType("text/html;charset=UTF-8");
		return jso.toString();
	}
	
	@RequestMapping(value = "{h_num}", method = RequestMethod.POST)
	public String update(@PathVariable("h_num") String schedule_no, HttpServletRequest request) {
		
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("schedule_no", schedule_no);
		
		scheduleService.scheduleDelete(map);
		
		return "redirect:/schedule?year=" + year + "&month=" + month;
	}

}
