package com.khacademy.khoffice.cooperation_report.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.khacademy.khoffice.approval.models.CooperationApprovalBoardListDTO;
import com.khacademy.khoffice.approval.models.CooperationReportAddFormDTO;
import com.khacademy.khoffice.approval.models.CooperationReportBoardListDTO;
import com.khacademy.khoffice.cooperation_approval.services.CooperationApprovalService;
import com.khacademy.khoffice.cooperation_report.services.CooperationReportService;

@Controller("cooperationReport")
@RequestMapping("/cooperation_report")
public class Controllers {

	public CooperationReportService cooReportService;

	@Autowired
	@Required
	public void setCooReportService(CooperationReportService cooReportService) {
		this.cooReportService = cooReportService;
	}

	public CooperationApprovalService cooApprovalService;

	@Autowired
	@Required
	public void setCooApprovalService(CooperationApprovalService cooApprovalService) {
		this.cooApprovalService = cooApprovalService;
	}

	// 업무협조요청문서 페이징 게시판 리스트
	@RequestMapping(method = RequestMethod.GET)
	public String getList(@RequestParam(value = "status", defaultValue = "0") String status,
			@RequestParam(value = "page_num", defaultValue = "1") String pageNum, HttpSession session, Model model) {
		int member_no = (int) session.getAttribute("session_memberNo");
		int pageSize = 3; // 한페이지당 게시글 수
		int currentPage = Integer.parseInt(pageNum); // 현재 페이지
		int start = (currentPage - 1) * pageSize + 1; // 한 페이지의 시작글 번호
		int end = currentPage * pageSize; // 한 페이지의 마지막 글번호
		int count = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("member_no", member_no);
		
		model.addAttribute("member_no", member_no);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageSize", pageSize);
		
		// ----------- 결재대기문서일때 ---------------

		if(status.equals("0")) {
		
		List<Integer> getMyCooreport_noList = cooApprovalService.getMyCooreport_noList(member_no);
		List<CooperationApprovalBoardListDTO> myApprovalList = new ArrayList<CooperationApprovalBoardListDTO>();
			
		for(int i=0; i < getMyCooreport_noList.size(); i++) {
			
			// 나의 member_no를통한 결재 conreport_no를 받아서
			int cooreport_no = getMyCooreport_noList.get(i);
			System.out.println("conreport_no : " + cooreport_no);
			
			// cooreport_no를 통해 cooperation_approval row를 가져옴.
			List<CooperationApprovalBoardListDTO> list = cooApprovalService.getMyApprovalRowsByNo(cooreport_no); 
		
			// 분기처리 시작.
			if(list.get(0).getMember_no() == member_no && list.get(0).getStatus().equals("0")) {
				System.out.println("난 승인해야할 첫번째 순서고 status도 0인상태.");
				myApprovalList.add(list.get(0));
			}
			if(list.get(1).getMember_no() == member_no && list.get(0).getStatus().equals("1") && list.get(1).getStatus().equals("0")) {
				System.out.println("난 승인해야할 두번째 순서고 첫번째 승인사람이 승인했고, 내 status도 0인상태.");
				list.get(1).setLast_approval("last");
				myApprovalList.add(list.get(1));
				
			}
		
		}
		
		start = (currentPage - 1) * pageSize; // 한 페이지의 시작글 번호
		end = currentPage * pageSize - 1; // 한 페이지의 마지막 글번호
		
		model.addAttribute("cooApprovalList", myApprovalList);
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("count", myApprovalList.size());
		System.out.println(myApprovalList);
		
		
		
		
		return "tiles/approval/response_list";
			
			
			
		//	----------- 결재요청문서일때 ---------------
		} else if (status.equals("1")) {
		
		// select한 cooperation_report의 row의 갯수.
		// cooBoardDao.cooperationBoardCount(member_no);
			count = cooReportService.cooperationBoardCount(member_no);
			List<CooperationReportBoardListDTO> list = cooReportService.list(map);
			model.addAttribute("cooDTOList", list);
			model.addAttribute("count", count);
			model.addAttribute("start", start);
			model.addAttribute("end", end);
			
			return "tiles/approval/request_list";
		}
		return "tiles/error";
	}

	// 결재작성페이지에서 결재요청을 누르면 실행되는 로직.
	@RequestMapping(method = RequestMethod.POST)
	public String add(@ModelAttribute("getInfo") CooperationReportAddFormDTO getInfo, HttpSession session,
			Model model) {
		int member_no = (int) session.getAttribute("session_memberNo");

		// 세션에서 member_no를 가져와 DTO에 저장
		getInfo.setMember_no(member_no);

		// 1단계 :: 결재작성을 하면 cooperation_report테이블에 insert 추가.
		int insertReportSuccess = cooReportService.oneStepCooperationInsertReport(getInfo);
		// insert하고 바로 등록되었던 PK값 conreport_no를 dto를통해 새롭게 update되어 저장된다.

		// cooperation_report에 글을 하나 등록했으므로 바로 approval테이블에도 팀원/부서장/대표이사 member_no로 저장.
		if (insertReportSuccess == 1) {

			// 사용하기전에 반드시 get_seq를 가져오는 시퀀스 함수를 만들고 사용해야합니다.
			int insertApprovalSuccess = cooApprovalService.twoStepCooperationInsertApproval(getInfo);

			if (insertApprovalSuccess == 2) { // cooperation_approval 테이블에 2개가 삽입되면 성공. 아니면 실패.
				System.out.println("CooperationApproval insert 성공.");
			} else {
				System.out.println("CooperationApproval insert 실패.");
			}
		} else {
			System.out.println("CooperationReport insert 실패.");
		}

		return "redirect:/cooperation_report?status=1";
	}
	
	// 한 게시글에 대한 상세페이지를 가져오는 로직.
	@RequestMapping(value = "/{cooreport_no}", method = RequestMethod.GET)
	public String getDetail(@PathVariable("cooreport_no") int cooreport_no, HttpSession session, Model model) {
		int member_no = (int) session.getAttribute("session_memberNo");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cooreport_no", cooreport_no);
		map.put("member_no", member_no);

		List<CooperationReportAddFormDTO> getDetailList = cooReportService.selectCooperationBoardDetail(map);
		Map<String, CooperationReportAddFormDTO> approvalMap = new HashMap<String, CooperationReportAddFormDTO>();
		Map<String, String> approvalMan = new HashMap<String, String>();
		
		for (int i = 0; i < getDetailList.size(); i++) {
			//getDetailList.get(i).setDepartment_no(); department_no의 값에따라 이름 부여.
			String key = "approvalMan" + (i+1);
			String nameKey = "approvalName" + (i+1);
			
			// pk값인 number_no로 들어오는것을 DB로 들어가서 이름으로 뽑아오기.
			int numberMember_no = getDetailList.get(i).getMember_no();
			
			String getNameByMemberNo = cooReportService.getNameByMemberNo(numberMember_no);
			
			approvalMap.put(key, getDetailList.get(i));
			approvalMan.put(nameKey, getNameByMemberNo);
			
		}
		
		// department_no 가 pk값으로 부서명을 가져오는 로직
		int numberDepartment_no = getDetailList.get(0).getDepartment_no();
		String getNameByDepartmentNo = cooReportService.getNameByDepartmentNo(numberDepartment_no);
		
		model.addAttribute("getNameByDepartmentNo", getNameByDepartmentNo);
		model.addAttribute("type", "cooperation_report");
		model.addAttribute("approvalMap", approvalMap); // list를 approvalMan[i] 키로 DTO 저장한 map객체
		model.addAttribute("approvalMan",approvalMan);
		
		return "tiles/approval/detail";
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

}
