package com.khacademy.khoffice.cooperation_approval.controllers;

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

import com.khacademy.khoffice.approval.models.ConsultReportAddFormDTO;
import com.khacademy.khoffice.approval.models.CooperationApprovalUpdateStatusDTO;
import com.khacademy.khoffice.cooperation_approval.services.CooperationApprovalService;

@Controller("cooperationApproval")
@RequestMapping("/cooperation_approval")
public class Controllers {

	CooperationApprovalService cooApprovalService;

	@Autowired
	@Required
	public void setCooApprovalService(CooperationApprovalService cooApprovalService) {
		this.cooApprovalService = cooApprovalService;
	}

	// 결재작성페이지에서 결재요청을 누르면 실행되는 로직.
	@RequestMapping(method = RequestMethod.POST)
	public String add(@ModelAttribute("getInfo") ConsultReportAddFormDTO getInfo, HttpSession session, Model model) {


		return "redirect:/consult_report?status=1";
	}

	@RequestMapping(value = "/{cooapproval_no}", method = RequestMethod.POST)
	public String update(@ModelAttribute("updateStatus") CooperationApprovalUpdateStatusDTO updateStatus,
						@PathVariable("cooapproval_no") String cooapproval_no,
						@RequestParam(value="last_approval", required=false) String last_approval, HttpSession session) {
			
		// button을 눌러서 가져오는 status 값에따라 1일지 2일지 변경.
		int member_no = (int) session.getAttribute("session_memberNo");
		updateStatus.setMember_no(member_no);
		int updateSuccess = 0;
		
		if(last_approval.equals("last") && updateStatus.getStatus().equals("1")) {
			updateSuccess = cooApprovalService.updateLastApprovalStatus(updateStatus);
			System.out.println("last쪽");
		} else {
			updateSuccess = cooApprovalService.updateCooApprovalStatus(updateStatus);
			System.out.println("아닌쪽");
		}
		
		if (updateSuccess == 1) {
			System.out.println("CooperationApproval status변경 성공");
		} else {
			System.out.println("update 실패");
		}

		return "redirect:/cooperation_report?page_num=1";
	}
}
