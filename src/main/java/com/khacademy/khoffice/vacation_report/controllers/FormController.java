package com.khacademy.khoffice.vacation_report.controllers;

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

import com.khacademy.khoffice.approval.models.VacationReportAddFormDTO;
import com.khacademy.khoffice.member.models.MemberDTO;
import com.khacademy.khoffice.member.services.MemberService;
import com.khacademy.khoffice.vacation_report.services.VacationReportService;

@Controller("vacationFormReport")
@RequestMapping("/vacation_report")
public class FormController {

	VacationReportService vacReportservice;

	@Autowired
	@Required
	public void setVacReportservice(VacationReportService vacReportservice) {
		this.vacReportservice = vacReportservice;
	}
	
	MemberService memberService;
	
	@Autowired
	@Required
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	// 결재작성(연차신청서) 템플릿 불러오는 메서드.
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String getAddForm(Model model, HttpSession session) {

		// list에 모든 사원 member를 가져와서 member_no값과 name값을 가져와 
		//옵션에는 name value에는 member_no값을 넣어주면 끝.
		List<MemberDTO> substitute = memberService.getAllMemberList();
		
		int vacreport_no = vacReportservice.selectLastVacreportNo();
		// vacreport_no 값 가져오기 성공.
		if (vacreport_no == 0) {
			vacreport_no = 1;
		} else {
			vacreport_no = vacreport_no + 1;
		}

		int member_no = (int) session.getAttribute("session_memberNo");
		VacationReportAddFormDTO getInfo = vacReportservice.vacationGetInfo(member_no);
		
		int remainder_leave = memberService.getRemainderLeaveCount(member_no);
		
		model.addAttribute("substitute",substitute);
		model.addAttribute("getInfo", getInfo);
		model.addAttribute("vacreport_no", vacreport_no);
		model.addAttribute("type", "vacation_report");
		model.addAttribute("remainder_leave", remainder_leave);
		model.addAttribute("member_no", member_no);
		
		return "tiles/approval/add_form";
	}

	// 승인대기문서에서 내가 승인해야할 폼 불러오는 메서드
	@RequestMapping(value = "/form/{vacreport_no}", method = RequestMethod.GET)
	public String getApprovalForm(@PathVariable("vacreport_no") String vacreport_no,
			@RequestParam(value = "page_num", defaultValue = "1") String pageNum,
			@RequestParam(value = "vacapproval_no") int vacapproval_no,
			@RequestParam(value="last_approval", required=false) String last_approval, HttpSession session, Model model) {

		int member_no = (int) session.getAttribute("session_memberNo");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("vacreport_no", vacreport_no);
		map.put("member_no", member_no);

		// 게시글의 pk conreport_no와 member_no를 가지고 db 조회해서 해당 row를 list로 가져옴.
		List<VacationReportAddFormDTO> getDetailList = vacReportservice.selectVacationBoardDetail(map);
		
		// 등록할때 들어간 값은 member_no값이므로 detail페이지에서 보여줄 업무대리인은 다시 이름으로 보여주기위한 db 조회 후 set과정
		String numberSubstitute = getDetailList.get(0).getSubstitute();
		String stringSubstitute = memberService.getNameByNo(numberSubstitute);
		getDetailList.get(0).setSubstitute(stringSubstitute);
		
		
		Map<String, VacationReportAddFormDTO> approvalMap = new HashMap<String, VacationReportAddFormDTO>();
		Map<String, String> approvalMan = new HashMap<String, String>();

		for (int i = 0; i < getDetailList.size(); i++) {
			getDetailList.toString();
			String key = "approvalMan" + (i + 1);
			String nameKey = "approvalName" + (i + 1);

			// pk값인 number_no로 들어오는것을 DB로 들어가서 이름으로 뽑아오기.
			int numberMember_no = getDetailList.get(i).getMember_no();
			String getNameByMemberNo = vacReportservice.getNameByMemberNo(numberMember_no);

			approvalMap.put(key, getDetailList.get(i));
			approvalMan.put(nameKey, getNameByMemberNo);
		}
		
		model.addAttribute("last_approval", last_approval);
		model.addAttribute("vacapproval_no", vacapproval_no);
		model.addAttribute("type", "vacation_report");
		model.addAttribute("approvalMap", approvalMap); // list를 approvalMan[i] 키로 DTO 저장한 map객체
		model.addAttribute("approvalMan", approvalMan);

		return "tiles/approval/approval_form";
	}
}
