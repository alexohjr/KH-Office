package com.khacademy.khoffice.consult_report.controllers;

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

import com.khacademy.khoffice.approval.models.ConsultReportAddFormDTO;
import com.khacademy.khoffice.consult_report.services.ConsultReportService;

@Controller("consultReportFormController")
@RequestMapping("/consult_report")
public class FormController {
	
	ConsultReportService conReportService;
	
	@Autowired
	@Required
	public void setConReportService(ConsultReportService conReportService) {
		this.conReportService = conReportService;
	}

	// 결재작성 (품의서) 템플릿 불러오는 메서드.
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String getAddForm(Model model, HttpSession session) {
		System.out.println("consult_report FormController들어왔는지 확인");
		
		int conreport_no = conReportService.selectLastConreportNo();
		// 가장 마지막에 등록된 conreport_no값 가져오는로직.
		if(conreport_no == 0) {
			conreport_no = 1;
		} else {
			conreport_no = conreport_no+1;
		}
		
		int member_no = (int) session.getAttribute("session_memberNo");
		ConsultReportAddFormDTO getInfo = conReportService.consultGetInfo(member_no);
		
		model.addAttribute("getInfo", getInfo);
		model.addAttribute("conreport_no", conreport_no);
		model.addAttribute("type", "consult_report");
		
		return "tiles/approval/add_form";
	}
	
	// 승인대기문서에서 내가 승인해야할 폼 불러오는 메서드
	@RequestMapping(value="/form/{conreport_no}", method=RequestMethod.GET)
	public String getApprovalForm(@PathVariable("conreport_no") String conreport_no,
								@RequestParam(value="page_num", defaultValue="1") String pageNum,
								@RequestParam(value="conapproval_no") int conapproval_no,
								@RequestParam(value="last_approval", required=false) String last_approval, HttpSession session, Model model) {
		
		int member_no = (int) session.getAttribute("session_memberNo");
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("conreport_no: "+ conreport_no);
		System.out.println("member_no : " + member_no);
		map.put("conreport_no", conreport_no);
		map.put("member_no", member_no);

		//게시글의 pk conreport_no와 member_no를 가지고 db 조회해서 해당 row를 list로 가져옴.
		List<ConsultReportAddFormDTO> getDetailList = conReportService.selectConsultBoardDetail(map);
		System.out.println("getDetailList : " + getDetailList);
		Map<String, ConsultReportAddFormDTO> approvalMap = new HashMap<String, ConsultReportAddFormDTO>();
		Map<String, String> approvalMan = new HashMap<String, String>();
		
		
		for (int i = 0; i < getDetailList.size(); i++) {
			getDetailList.toString();
			String key = "approvalMan" + (i+1);
			String nameKey = "approvalName" + (i+1);
			
			// pk값인 number_no로 들어오는것을 DB로 들어가서 이름으로 뽑아오기.
			int numberMember_no = getDetailList.get(i).getMember_no();
			String getNameByMemberNo = conReportService.getNameByMemberNo(numberMember_no);
			System.out.println("getNameByMemberNo :  "+ getNameByMemberNo);
			System.out.println("getDetailList.get(i) : " + getDetailList.get(i));
			approvalMap.put(key, getDetailList.get(i));
			approvalMan.put(nameKey, getNameByMemberNo);
		}
		System.out.println("approvalMap : " + approvalMap);
		System.out.println("approvalMan : " + approvalMan);
		model.addAttribute("last_approval",last_approval);
		model.addAttribute("conapproval_no", conapproval_no);
		model.addAttribute("type", "consult_report");
		model.addAttribute("approvalMap", approvalMap); // list를 approvalMan[i] 키로 DTO 저장한 map객체
		model.addAttribute("approvalMan",approvalMan);
		
		return "tiles/approval/approval_form";
	}
}
