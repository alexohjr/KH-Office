package com.khacademy.khoffice.vacation_report.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.khacademy.khoffice.approval.models.VacationApprovalBoardListDTO;
import com.khacademy.khoffice.approval.models.VacationReportAddFormDTO;
import com.khacademy.khoffice.approval.models.VacationReportBoardListDTO;
import com.khacademy.khoffice.member.services.MemberService;
import com.khacademy.khoffice.vacation_approval.services.VacationApprovalService;
import com.khacademy.khoffice.vacation_report.services.VacationReportService;

@Controller("vacationReport")
@RequestMapping("/vacation_report")
public class Controllers {
	
	
	public VacationReportService vacReportService;
	
	@Autowired
	@Required
	public void setVacReportService(VacationReportService vacReportService) {
		this.vacReportService = vacReportService;
	}

	public VacationApprovalService vacApprovalService;
	
	@Autowired
	@Required
	public void setVacApprovalService(VacationApprovalService vacApprovalService) {
		this.vacApprovalService = vacApprovalService;
	}
	
	public MemberService memberService;
	
	@Autowired
	@Required
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	// 결재요청 페이징게시판(연차신청서) 불러오는 메서드
	@RequestMapping(method = RequestMethod.GET)
	public String getList(@RequestParam(value = "status", defaultValue = "1") String status,
			@RequestParam(value = "page_num", defaultValue = "1") String pageNum, HttpSession session, Model model) {
		
		System.out.println("vacation_report 패키지 controller 진입성공");
		
		int member_no = (int) session.getAttribute("session_memberNo");
		int pageSize = 3; // 한페이지의 글의 개수
		int currentPage = Integer.parseInt(pageNum);
		int start = (currentPage - 1) * pageSize + 1; // 한 페이지의 시작글 번호
		int end = currentPage * pageSize; // 한 페이지의 마지막 글번호
		int count = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("member_no", member_no);
		
		model.addAttribute("member_no", member_no);
		model.addAttribute("currentPage", currentPage); // 1
		model.addAttribute("pageSize", pageSize); // 3

		// ----------- 결재대기문서일때 ---------------
		if(status.equals("0")) {
			List<Integer> getMyVacreport_noList = vacApprovalService.getMyVacreport_no(member_no);
			List<VacationApprovalBoardListDTO> myApprovalList = new ArrayList<VacationApprovalBoardListDTO>();
			
			// list로받은 vacreport_no를 들고 있는 list만큼 for문돌려 approvalList로 뿌리기위한 작업.
			for(int i=0; i < getMyVacreport_noList.size(); i++) {
				
				int vacreport_no = getMyVacreport_noList.get(i);
				
				List<VacationApprovalBoardListDTO> list = vacApprovalService.getMyApprovalRowsByNo(vacreport_no);
				
				System.out.println("Vac Approval list : " + list);
				// 분기처리 시작.
				if(list.get(0).getMember_no() == member_no && list.get(0).getStatus().equals("0")) {
					System.out.println("난 승인해야할 첫번째 순서고 status도 0인상태.");
					myApprovalList.add(list.get(0));
				}
				if(list.get(1).getMember_no() == member_no && list.get(0).getStatus().equals("1") && list.get(1).getStatus().equals("0")) {
					System.out.println("난 승인해야할 두번째 순서고 첫번째 승인사람이 승인했고, 내 status도 0인상태.");
					myApprovalList.add(list.get(1));
				}
				if(list.get(2).getMember_no() == member_no && list.get(0).getStatus().equals("1") && list.get(1).getStatus().equals("1") && list.get(2).getStatus().equals("0")) {
					System.out.println("난 승인해야할 세번째 순서고 첫번째 승인사람이 승인했고, 두번째사람도 승인했고 내 status도 0인상태.");
					list.get(2).setLast_approval("last");
					myApprovalList.add(list.get(2));
				}
			
			}
		
			start = (currentPage - 1) * pageSize; // 한 페이지의 시작글 번호
			end = currentPage * pageSize - 1; // 한 페이지의 마지막 글번호
			
			model.addAttribute("vacApprovalList", myApprovalList);
			model.addAttribute("start", start);
			model.addAttribute("end", end);
			model.addAttribute("count", myApprovalList.size());
			
			
			return "tiles/approval/response_list";
		
			
		// -------------- 결재요청문서일때 ---------------	
			
		} else if (status.equals("1")) {// 결재요청문서일때
			// select한 vacation_report의 row의 갯수
			count = vacReportService.vacationBoardCount(member_no);
			// map 데이터를 들고 게시판 list 뽑아오는 로직
			List<VacationReportBoardListDTO> list = vacReportService.list(map);
		
			model.addAttribute("vacDTOList", list);
			model.addAttribute("count", count);
			model.addAttribute("start", start);
			model.addAttribute("end", end);
			return "tiles/approval/request_list";
		}
		// pageCount = 3 , pageBlock =10, result=0 startPage=1 endPage=3
		
		return "tiles/error";
		
	}
	
	// 결재작성페이지에서 결재요청을 누르면 실행되는 로직.
	@RequestMapping(method = RequestMethod.POST)
	public String add(@ModelAttribute("getInfo") VacationReportAddFormDTO getInfo, HttpSession session, Model model) {
		int member_no = (int) session.getAttribute("session_memberNo");
			
		// 세션에서 member_no를 가져와 DTO에 저장
		getInfo.setMember_no(member_no);

		// 1단계 :: 결재작성을 하면 consult_report테이블에 insert 추가.
		int insertReportSuccess = vacReportService.oneStepVacationInsertReport(getInfo);
		// insert하고 바로 등록되었던 PK값 vacreport_no를 dto를통해 새롭게 update되어 저장된다.

		// vacation_report에 글을 하나 등록했으므로 바로 approval테이블에도 팀원/부서장/대표이사 member_no로 저장.
		if (insertReportSuccess == 1) {

			// 사용하기전에 반드시 get_seq를 가져오는 시퀀스 함수를 만들고 사용해야합니다.
			int insertApprovalSuccess = vacApprovalService.twoStepVacationInsertApproval(getInfo);

			if (insertApprovalSuccess == 3) { // VacationApproval 테이블에 3개가 삽입되면 성공. 아니면 실패.
				System.out.println("VacationApproval insert 성공.");
			} else {
				System.out.println("VacationApproval insert 실패.");
			}
		} else {
			System.out.println("VacationReport insert 실패.");
		}

		return "redirect:/vacation_report?status=1";
	}

	// 한 게시글에 대한 상세페이지를 가져오는 로직.
	@RequestMapping(value = "/{vacreport_no}", method = RequestMethod.GET)
	public String getDetail(@PathVariable("vacreport_no") int vacreport_no, HttpSession session, Model model) {

		int member_no = (int) session.getAttribute("session_memberNo");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("vacreport_no", vacreport_no);
		map.put("member_no", member_no);

		List<VacationReportAddFormDTO> getDetailList = vacReportService.selectVacationBoardDetail(map);

		// 등록할때 들어가는 값은 member_no값이므로 detail페이지에서 보여줄 업무대리인은 다시 이름으로 보여주기위한 set과정
		String numberSubstitute = getDetailList.get(0).getSubstitute();
		String stringSubstitute = memberService.getNameByNo(numberSubstitute);
		getDetailList.get(0).setSubstitute(stringSubstitute);
			
		Map<String, VacationReportAddFormDTO> approvalMap = new HashMap<String, VacationReportAddFormDTO>();
		Map<String, String> approvalMan = new HashMap<String, String>();
			
		for (int i = 0; i < getDetailList.size(); i++) {
			String key = "approvalMan" + (i+1);
			String nameKey = "approvalName" + (i+1);
			// pk값인 number_no로 들어오는것을 DB로 들어가서 이름으로 뽑아오기.
			int numberMember_no = getDetailList.get(i).getMember_no();
			String getNameByMemberNo = vacReportService.getNameByMemberNo(numberMember_no);
				
			approvalMap.put(key, getDetailList.get(i));
			approvalMan.put(nameKey, getNameByMemberNo);
		}
			
		model.addAttribute("type", "vacation_report");
		model.addAttribute("approvalMap", approvalMap); // list를 approvalMan[i] 키로 DTO 저장한 map객체
		model.addAttribute("approvalMan",approvalMan);
			
		return "tiles/approval/detail";
	}

}
