package com.khacademy.khoffice.consult_report.controllers;

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

import com.khacademy.khoffice.approval.models.ConsultApprovalBoardListDTO;
import com.khacademy.khoffice.approval.models.ConsultReportAddFormDTO;
import com.khacademy.khoffice.approval.models.ConsultReportBoardListDTO;
import com.khacademy.khoffice.consult_approval.services.ConsultApprovalService;
import com.khacademy.khoffice.consult_report.services.ConsultReportService;

@Controller("consultReportController")
@RequestMapping("/consult_report")
public class Controllers {

	ConsultReportService conReportService;

	@Autowired
	@Required
	public void setConReportService(ConsultReportService conReportService) {
		this.conReportService = conReportService;
	}

	ConsultApprovalService conApprovalService;

	@Autowired
	@Required
	public void setConApprovalService(ConsultApprovalService conApprovalService) {
		this.conApprovalService = conApprovalService;
	}

	// 결재요청 페이징게시판(품의서) 불러오는 메서드
	@RequestMapping(method = RequestMethod.GET)
	public String getList(@RequestParam(value = "status", defaultValue = "0") String status,
			@RequestParam(value = "page_num", defaultValue = "1") String pageNum, HttpSession session, Model model) {
		
		int member_no = (int) session.getAttribute("session_memberNo");
		int pageSize = 3; // 한페이지의 글의 개수 //
		int currentPage = Integer.parseInt(pageNum); //1
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
			
			//count = conReportService.consultApprovalBoard(member_no);
			List<Integer> getMyConreport_noList = conApprovalService.getMyConreport_no(member_no);
			List<ConsultApprovalBoardListDTO> myApprovalList = new ArrayList<ConsultApprovalBoardListDTO>();
			
			for(int i=0; i<getMyConreport_noList.size(); i++) {
				
					// 나의 member_no를통한 결재 conreport_no를 받아서
					int conreport_no = getMyConreport_noList.get(i); 
					System.out.println("conreport_no : " + conreport_no);
					
					// conreport_no를 통해 consult_approval row를 가져옴
					List<ConsultApprovalBoardListDTO> list = conApprovalService.getMyApprovalRowsByNo(conreport_no); 
					
					// 분기처리 시작. 
					// list.get(0) : 내가 첫번째 , list.get(1) : 내가 두번째 , list.get(2) : 내가 세번째
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
			
			
			/*
			 * 	<c:set var="pageCount" value="${count/pageSize+(count%pageSize==0 ? 0:1)}"/>
				<c:set var="pageBlock" value="${10}"/> 
				<fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />   
				<c:set var="startPage" value="${result * 10 +1}"/>   
				<c:set var="endPage" value="${startPage + pageBlock-1}"/>
				<c:if test="${endPage > pageCount}">
					<c:set var="endPage" value="${pageCount}"/>
				</c:if>
				
				를 jsp단에서 한번 저장되면 유지되는듯 함.. 물어보고 확인해보고 안되면 메서드로 java단에서 처리하고 넘어가야함.
				2018-10-30(화) =====>>> 수요일날 마저 정리
			 * 
			 * 
			 * 
			 * 
			 * */
			
			
			
			
			start = (currentPage - 1) * pageSize; // 한 페이지의 시작글 번호
			end = currentPage * pageSize - 1; // 한 페이지의 마지막 글번호
			
			model.addAttribute("conApprovalList", myApprovalList);
			model.addAttribute("start", start);
			model.addAttribute("end", end);
			model.addAttribute("count", myApprovalList.size());
			System.out.println(myApprovalList);
			
			return "tiles/approval/response_list";
			
			
			
		//	----------- 결재요청문서일때 ---------------
			
		} else if(status.equals("1")){ // 결재요청문서일때
			// select한 consult_report의 row의 갯수.
			count = conReportService.consultBoardCount(member_no);
			// map데이터를 들고 게시판 list 뽑아오는 로직
			List<ConsultReportBoardListDTO> list = conReportService.list(map);
			model.addAttribute("conDTOList", list);
			model.addAttribute("count", count);
			model.addAttribute("start", start);
			model.addAttribute("end", end);
			return "tiles/approval/request_list";
		}
		
		return "tiles/error";
	}

	// 결재작성페이지에서 결재요청을 누르면 실행되는 로직.
	@RequestMapping(method = RequestMethod.POST)
	public String add(@ModelAttribute("getInfo") ConsultReportAddFormDTO getInfo, HttpSession session, Model model) {
		int member_no = (int) session.getAttribute("session_memberNo");

		// 세션에서 member_no를 가져와 DTO에 저장
		getInfo.setMember_no(member_no);

		// 1단계 :: 결재작성을 하면 consult_report테이블에 insert 추가.
		int insertReportSuccess = conReportService.oneStepConsultInsertReport(getInfo);
		// insert하고 바로 등록되었던 PK값 conreport_no를 dto를통해 새롭게 update되어 저장된다.
		
		// consult_report에 글을 하나 등록했으므로 바로 approval테이블에도 팀원/부서장/대표이사 member_no로 저장.
		if (insertReportSuccess == 1) {

			// 사용하기전에 반드시 get_seq를 가져오는 시퀀스 함수를 만들고 사용해야합니다.
			int insertApprovalSuccess = conApprovalService.twoStepConsultInsertApproval(getInfo);

			if (insertApprovalSuccess == 3) { // consult_approval 테이블에 3개가 삽입되면 성공. 아니면 실패.
				System.out.println("ConsultApproval insert 성공.");
			} else {
				System.out.println("ConsultApproval insert 실패.");
			}
		} else {
			System.out.println("ConsultReport insert 실패.");
		}

		return "redirect:/consult_report?status=1";
	}

	// 한 게시글에 대한 상세페이지를 가져오는 로직.
	@RequestMapping(value = "/{conreport_no}", method = RequestMethod.GET)
	public String getDetail(@PathVariable("conreport_no") int conreport_no, HttpSession session, Model model) {

		int member_no = (int) session.getAttribute("session_memberNo");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("conreport_no", conreport_no);
		map.put("member_no", member_no);

		//게시글의 pk conreport_no와 member_no를 가지고 db 조회해서 해당 row를 list로 가져옴.
		List<ConsultReportAddFormDTO> getDetailList = conReportService.selectConsultBoardDetail(map);
		
		Map<String, ConsultReportAddFormDTO> approvalMap = new HashMap<String, ConsultReportAddFormDTO>();
		Map<String, String> approvalMan = new HashMap<String, String>();
		
		
		for (int i = 0; i < getDetailList.size(); i++) {
			getDetailList.toString();
			String key = "approvalMan" + (i+1);
			String nameKey = "approvalName" + (i+1);
			
			// pk값인 number_no로 들어오는것을 DB로 들어가서 이름으로 뽑아오기.
			int numberMember_no = getDetailList.get(i).getMember_no();
			String getNameByMemberNo = conReportService.getNameByMemberNo(numberMember_no);
			
			approvalMap.put(key, getDetailList.get(i));
			approvalMan.put(nameKey, getNameByMemberNo);
		}
		
		model.addAttribute("type", "consult_report");
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
