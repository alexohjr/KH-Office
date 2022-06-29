package com.khacademy.khoffice.work_history.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.khacademy.khoffice.department.models.DepartmentDTO;
import com.khacademy.khoffice.department.services.DepartmentService;
import com.khacademy.khoffice.member.models.MemberDetailDTO;
import com.khacademy.khoffice.member.services.MemberService;
import com.khacademy.khoffice.work_history.models.AdminWorkHistoryDTO;
import com.khacademy.khoffice.work_history.models.WorkHistoryDTO;
import com.khacademy.khoffice.work_history.services.WorkHistoryService;

@Controller("WorkHistoryController")
@RequestMapping("/work_history")
public class Controllers {
	
	WorkHistoryService workHistoryService;
	
	@Autowired
	@Required
	public void setWorkHistoryService(WorkHistoryService workHistoryService) {
		this.workHistoryService = workHistoryService;
	}

	MemberService memberService;
	
	@Autowired
	@Required
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	DepartmentService departmentService;
	
	@Autowired
	@Required
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	

	/* 어드민이 볼 수 있는 부서별 전체 근태관리 페이지 */
	@RequestMapping(method=RequestMethod.GET)
	public String get(@RequestParam(value="page_num", defaultValue="1") String pageNum,
						@RequestParam(value="department_no", defaultValue="10") String department_no,
						@RequestParam(value="strYear", required=false) String strYear,
						@RequestParam(value="strMonth", required=false) String strMonth,
						HttpSession session, Model model) {
		
		/** 한달에 몇일인지 계산하는 로직  **/
		Calendar cal = Calendar.getInstance();
		
		String year = cal.get(Calendar.YEAR) +""; // 2018
		String month = cal.get(Calendar.MONTH) + ""; // 10 0부터시작하기때문에 +1을 해주어야함
		String date = cal.getMinimum(java.util.Calendar.DATE)+""; // 1

		if(strYear != null){
		  year = strYear;
		  int a =Integer.parseInt(strMonth);
		  month = a+"";
		}
		
		if(year.length() < 2) {
			year = "0"+year;
		}
		if(month.length() < 2){
			month = "0"+month;
		}
		if(date.length() < 2){
			date = "0"+date;
		}
		
		//년도/월 셋팅

		cal.set(Integer.parseInt(year), Integer.parseInt(month), 1);

		int startDay = cal.getMinimum(java.util.Calendar.DATE); // 1 == 그달의 첫번째 시작 일
		int endDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH); // 30 == 그 달의 마지막날 일
		int day_of_week = cal.get(java.util.Calendar.DAY_OF_WEEK) -1; // 요일에대한 숫자. 1(일) ~ 7(토)
		List<String> list = new ArrayList<String>();
		for(int i=1; i<=endDay; i++) { // 숫자에대한 값으로 요일을 가져오기위한 로직.
			if(day_of_week == 7) {
				day_of_week = 0;
			}
			day_of_week = day_of_week +1;
			String day_of_weekString = getDateString(day_of_week);
			
			list.add(day_of_weekString);
		}
		
		List<DepartmentDTO> departmentList = departmentService.getDepartmentDTOList();
		model.addAttribute("departmentList", departmentList);
		
		// 부서에있는 모든직원중에 내가 선택한 달의 work_history데이터
		String addedYMD = year+"/"+(Integer.parseInt(month)+1)+"/"+date;
		System.out.println("addedYMD : " + addedYMD);
		
		Map<String, Object> map = null;
		
		List<AdminWorkHistoryDTO> memberListByDepartNo = memberService.getHistoryMemberListByDepartmentNo(Integer.parseInt(department_no));
		List<AdminWorkHistoryDTO> allSelectListByMemberNo = null;
		List<AdminWorkHistoryDTO> tableList = null;
		List<ArrayList<AdminWorkHistoryDTO>> completeWorkhistoryDataBymemberNo = new ArrayList<ArrayList<AdminWorkHistoryDTO>>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		
		
		for(int i=0; i<memberListByDepartNo.size(); i++) { // 부서전체만큼 돌림.
			int member_no = memberListByDepartNo.get(i).getMember_no();
			
			tableList = new ArrayList<AdminWorkHistoryDTO>();
			allSelectListByMemberNo = new ArrayList<AdminWorkHistoryDTO>();
			map = new HashMap<String, Object>();
			map.put("addedYMD", addedYMD);
			map.put("member_no", member_no);
			

			allSelectListByMemberNo = workHistoryService.allSelectWorkHistoryList(map);
			for(int j=0; j<endDay; j++) { // 31일까지 dto를 생성.
				tableList.add(new AdminWorkHistoryDTO());
			}
			
				// 그 달의 일수만큼 table을 만든것 중, 데이터가있는 곳은 가지고있는 DTO를 set
				for(int k=0; k<allSelectListByMemberNo.size(); k++) { 
					if(allSelectListByMemberNo.get(k).getEnd_time() != null && allSelectListByMemberNo.get(k).getStart_time() != null) {
						Date getStart = allSelectListByMemberNo.get(k).getStart_time();
						Date getEnd = allSelectListByMemberNo.get(k).getEnd_time();
						long start_t = getStart.getTime();
						long end_t = getEnd.getTime();
						long work_t_ms= (end_t - start_t) / 1000;
						String hour = (work_t_ms/3600) +"";
						String min = ((work_t_ms % 3600) / 60) +"";
						if(hour.length() < 2) {
							hour = "0"+hour;
						}
						if(min.length() < 2) {
							min = "0"+min;
						}
						String work_t = hour+":"+min;
						allSelectListByMemberNo.get(k).setWork_time(work_t);
					}
					Date start_date = allSelectListByMemberNo.get(k).getStart_time();
					int test = Integer.parseInt(sdf.format(start_date));
					tableList.set(test-1,allSelectListByMemberNo.get(k));
				} /* 그 달의 일수만큼 돌리는 for문 끝 */
				
			completeWorkhistoryDataBymemberNo.add((ArrayList<AdminWorkHistoryDTO>) tableList);
		} /* 부서 전체만큼 돌리는 for문 끝 */
		
		/**** paging 구간. ***/
		
		int pageSize=3; // 한페이지의 글의 갯수 // (0,3) (3,6) (6,9)
		int currentPage = Integer.parseInt(pageNum); // 2
		int count = completeWorkhistoryDataBymemberNo.size(); // 4
		int startPage = (currentPage-1) * pageSize;
		int endPage = currentPage * pageSize; 
		int pageCount = count / pageSize + (count % pageSize ==0 ? 0 : 1);
		int pageBlock = 5;
		int result = (currentPage % pageBlock) == 0 ? (currentPage/pageBlock)-1 : (currentPage/pageBlock);
		//pageNum =3 일때 result는 0 pageNum=6일때 result는 1
		int startPagingNum = result * pageBlock+1;
		int endPagingNum = startPagingNum + pageBlock-1;
		if(endPagingNum > count) {
			endPagingNum = pageCount;
		}
		
		if(startPage > count) {
			startPage = count-1;
		}
		
		if(endPage > count) {
			endPage = count;
		}
		
		model.addAttribute("department_no",department_no);
		model.addAttribute("year", year); 
		model.addAttribute("month", month); 
		model.addAttribute("date", date); 
		model.addAttribute("startDay", startDay);
		model.addAttribute("endDay", endDay);
		model.addAttribute("day_of_week", day_of_week);
		model.addAttribute("list" , list);
		model.addAttribute("memberListByDepartNo",memberListByDepartNo.subList(startPage, endPage));
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("pageBlock",pageBlock);
		model.addAttribute("pageCount",pageCount);
		model.addAttribute("page_num",pageNum);
		model.addAttribute("startPagingNum",startPagingNum);
		model.addAttribute("endPagingNum",endPagingNum);
		model.addAttribute("workHistoryDTO", completeWorkhistoryDataBymemberNo.subList(startPage, endPage));
		
		return "tiles/work_history/list";
	} /*** getList 메서드 - End - ***/
	

	
	
	/*  회원에대한 근태관리 상세페이지. */
	@RequestMapping(value="{memberNo}" , method=RequestMethod.GET)
	public String getDetail(@PathVariable("memberNo") String member_no,
						@RequestParam(value="strYear", required=false) String strYear,
						@RequestParam(value="strMonth", required=false) String strMonth, HttpSession session, Model model) {
		
		String admin = (String) session.getAttribute("session_adminID");
		if(admin != null) {
			model.addAttribute("admin",admin);
		}

		Calendar cal = Calendar.getInstance();
		
		int year = cal.get(Calendar.YEAR); // 2018
		int month = cal.get(Calendar.MONTH); // 10 0부터시작하기때문에 +1을 해주어야함
		int date = cal.get(Calendar.DATE); // 1

		if(strYear != null){
		  year = Integer.parseInt(strYear);
		  month = Integer.parseInt(strMonth);
		}

		//년도/월 셋팅

		cal.set(year, month, 1);

		int startDay = cal.getMinimum(java.util.Calendar.DATE); // 1 == 그달의 첫번째 시작 일
		int endDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH); // 30 == 그 달의 마지막날 일
		int day_of_week = cal.get(java.util.Calendar.DAY_OF_WEEK) -1; // 요일에대한 숫자. 1(일) ~ 7(토)
		List<String> list = new ArrayList<String>();
		for(int i=1; i<=endDay; i++) { // 숫자에대한 값으로 요일을 가져오기위한 로직.
			if(day_of_week == 7) {
				day_of_week = 0;
			}
			day_of_week = day_of_week +1;
			String day_of_weekString = getDateString(day_of_week);
			
			list.add(day_of_weekString);
		}
		
		List<WorkHistoryDTO> historyDTOList = workHistoryService.selectWorkHistoryByMemberNo(Integer.parseInt(member_no));
		SimpleDateFormat sdf = new SimpleDateFormat("a HH:mm:ss");
		for(int i=0; i<historyDTOList.size(); i++) {
			Date getStartTime = historyDTOList.get(i).getStart_time();
			Date getEndTime = historyDTOList.get(i).getEnd_time();
			Calendar calendar = Calendar.getInstance();
			Calendar calendar2 = Calendar.getInstance();
			
			calendar.setTime(getStartTime);
			long start_t = calendar.getTimeInMillis();
			historyDTOList.get(i).setStrStart(sdf.format(start_t));
			int dbYear = calendar.get(Calendar.YEAR);
			int dbMonth = calendar.get(Calendar.MONTH);
			int dbDate = calendar.get(Calendar.DATE);
			historyDTOList.get(i).setDb_year(dbYear);
			historyDTOList.get(i).setDb_month(dbMonth);
			historyDTOList.get(i).setDb_date(dbDate);
			
			
			if(getEndTime != null) {
				calendar2.setTime(getEndTime);
				long end_t = calendar2.getTimeInMillis();
				historyDTOList.get(i).setStrEnd(sdf.format(end_t));
				long work_t_ms= (end_t - start_t) / 1000;
				String hour = (work_t_ms/3600) +"";
				String min = ((work_t_ms % 3600) / 60) +"";
				String sec = (work_t_ms % 60) +"";
				if(hour.length() < 2) {
					hour = "0"+hour;
				}
				if(min.length() < 2) {
					min = "0"+min;
				}
				if(sec.length() <2) {
					sec = "0"+sec;
				}
				String work_t = hour+":"+min+":"+sec;
				historyDTOList.get(i).setWork_time(work_t);
			}
			
		}
		
		List<MemberDetailDTO> detailDTO = memberService.getMemberDetail(Integer.parseInt(member_no));
		
		model.addAttribute("detailDTO", detailDTO);
		model.addAttribute("member_no", member_no);
		model.addAttribute("year", year); 
		model.addAttribute("month", month); 
		model.addAttribute("date", date); 
		model.addAttribute("startDay", startDay);
		model.addAttribute("endDay", endDay);
		model.addAttribute("day_of_week", day_of_week);
		model.addAttribute("list" , list);
		model.addAttribute("historyDTOList",historyDTOList);
		return "tiles/work_history/detail";
	}  /*** getDetail 메서드 - End - ***/
	
	
	
	
	public String getDateString(int day) {
		String a = ""; 
		switch(day) {
		case 1:
	         a = "일";
	         break ;
	     case 2:
	         a = "월";
	         break ;
	     case 3:
	         a = "화";
	         break ;
	     case 4:
	         a = "수";
	         break ;
	     case 5:
	         a = "목";	
	         break ;
	     case 6:
	         a = "금";
	         break ;
	     case 7:
	         a = "토";
	         break ;
		}
		return a;
	}
	
}

