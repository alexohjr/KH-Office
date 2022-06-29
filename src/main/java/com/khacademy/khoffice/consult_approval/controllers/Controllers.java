package com.khacademy.khoffice.consult_approval.controllers;

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

import com.khacademy.khoffice.approval.models.ConsultApprovalUpdateStatusDTO;
import com.khacademy.khoffice.approval.models.ConsultReportAddFormDTO;
import com.khacademy.khoffice.consult_approval.services.ConsultApprovalService;

@Controller("consultApproval")
@RequestMapping("/consult_approval")
public class Controllers {
	
	ConsultApprovalService conApprovalService;

	@Autowired
	@Required
	public void setConApprovalService(ConsultApprovalService conApprovalService) {
		this.conApprovalService = conApprovalService;
	}
	
	// 결재작성페이지에서 결재요청을 누르면 실행되는 로직.
	@RequestMapping(method = RequestMethod.POST)
	public String add(@ModelAttribute("getInfo") ConsultReportAddFormDTO getInfo, HttpSession session, Model model) {

		System.out.println("consult_approval.controllers패키지의 Controllers add메서드에 진입성공.");
		System.out.println(getInfo.toString());

		return "redirect:/consult_report?status=1";
	}
	
	@RequestMapping(value="/{conapproval_no}", method = RequestMethod.POST)
	public String update(@ModelAttribute("updateStatus") ConsultApprovalUpdateStatusDTO updateStatus, 
						@PathVariable("conapproval_no") String conapproval_no,
						@RequestParam(value="last_approval", required=false) String last_approval, HttpSession session) {
		
			// button을 눌러서 가져오는 status 값에따라 1일지 2일지 변경.
			int member_no = (int) session.getAttribute("session_memberNo");

			updateStatus.setMember_no(member_no);
			int updateSuccess = 0;
			
			
			// 최종결재자이면서, 승인을 눌른다면 '최종결재완료'로 변경하기위한 status=3변경
			if(last_approval.equals("last") && updateStatus.getStatus().equals("1")) {
				updateSuccess = conApprovalService.updateLastApprovalStatus(updateStatus);
				System.out.println("last쪽");
			} else {
				updateSuccess = conApprovalService.updateConApprovalStatus(updateStatus);
				System.out.println("아닌쪽");
			}

			
			if(updateSuccess == 1) {
				System.out.println("ConsultApproval status 변경 성공");
			} else {
				System.out.println("update 실패");
			}
			
		
		return "redirect:/consult_report?page_num=1";
	}
}
