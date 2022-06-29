package com.khacademy.khoffice.vacation_approval.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.khacademy.khoffice.approval.models.VacationApprovalUpdateStatusDTO;
import com.khacademy.khoffice.member.services.MemberService;
import com.khacademy.khoffice.vacation_approval.services.VacationApprovalService;

@Controller("vacationApproval")
@RequestMapping("/vacation_approval")
public class Controllers {

	VacationApprovalService vacApprovalService;

	@Autowired
	@Required
	public void setVacApprovalService(VacationApprovalService vacApprovalService) {
		this.vacApprovalService = vacApprovalService;
	}
	
	MemberService memberService;
	
	@Autowired
	@Required
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	// approval_form에서 승인/반려에 따른 결재로직
	@RequestMapping(value = "/{vacapproval_no}", method = RequestMethod.POST)
	public String update(@ModelAttribute("updateStatus") VacationApprovalUpdateStatusDTO updateStatus,
						@PathVariable("vacapproval_no") String vacapproval_no,
						@RequestParam(value="last_approval", required=false) String last_approval, HttpSession session) {

		// button을 눌러서 가져오는 status 값에따라 1일지 2일지 변경.
		int member_no = (int) session.getAttribute("session_memberNo");
		updateStatus.setMember_no(member_no);
		int updateSuccess = 0;

		// 최종결재자이면서, 승인을 눌른다면 '최종결재완료'로 변경하기위한 status=3변경, 그외엔 버튼 status값에따라 update
		if(last_approval.equals("last") && updateStatus.getStatus().equals("1")) {
			updateSuccess = vacApprovalService.updateLastApprovalStatus(updateStatus);
			//마지막 승인이 완료되면 처음 작성했던 기안자의 가지고있던 remainder_leave에서 사용하려는 연차일수만큼 차감
			int decreaseRemainderLeave = memberService.decreaseRemainderLeave(updateStatus);
			if(decreaseRemainderLeave == 1) {
				System.out.println("member테이블의 remainder_leave 값 수정 성공!");
			}
		} else {
			updateSuccess = vacApprovalService.updateVacApprovalStatus(updateStatus);
		}
		
		if (updateSuccess == 1) { 
			System.out.println("VacationApproval status 변경 성공");
			
		} else {
			System.out.println("update 실패");
		}

		return "redirect:/vacation_report?page_num=1";
	}
}
