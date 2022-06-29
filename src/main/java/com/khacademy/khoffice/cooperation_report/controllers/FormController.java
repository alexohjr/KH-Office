package com.khacademy.khoffice.cooperation_report.controllers;

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

import com.khacademy.khoffice.approval.models.CooperationReportAddFormDTO;
import com.khacademy.khoffice.cooperation_report.services.CooperationReportService;
import com.khacademy.khoffice.department.models.DepartmentDTO;
import com.khacademy.khoffice.department.services.DepartmentService;

@Controller("cooperationFormReport")
@RequestMapping("/cooperation_report")
public class FormController {
   
   CooperationReportService cooReportService;
   
   @Autowired
   @Required
   public void setCooReportService(CooperationReportService cooReportService) {
      this.cooReportService = cooReportService;
   }
   
   DepartmentService departmentService;
   
   @Autowired
   @Required
   public void setDepartmentService(DepartmentService departmentService) {
      this.departmentService = departmentService;
   }

   // 결재작성(업무협조요청서) 템플릿 불러오는 메서드
   @RequestMapping(value = "/form", method = RequestMethod.GET)
   public String getAddForm(HttpSession session, Model model) {
      
      // list에 모든 부서 department를 가져와서 department_no값과 name값을 가져와 
      //옵션에는 name value에는 department_no값을 넣어주면 끝.
      List<DepartmentDTO> departmentList = departmentService.getDepartmentDTOList();
      
      // 가장 마지막에 등록된 cooreport_no가져옴
      int cooreport_no = cooReportService.selectLastCooreportNo();
      if(cooreport_no == 0) {
         cooreport_no = 1;
      } else {
         cooreport_no = cooreport_no +1;
      }
      
      int member_no = (int) session.getAttribute("session_memberNo");
      
      CooperationReportAddFormDTO getInfo = cooReportService.cooperationGetInfo(member_no);
      
      model.addAttribute("department_no",departmentList);
      model.addAttribute("getInfo", getInfo);
      model.addAttribute("cooreport_no", cooreport_no);
      model.addAttribute("type", "cooperation_report");
      
      return "tiles/approval/add_form";
   }
   
   // 승인대기문서에서 내가 승인해야할 폼 불러오는 메서드
      @RequestMapping(value="/form/{cooreport_no}", method=RequestMethod.GET)
      public String getApprovalForm(@PathVariable("cooreport_no") String cooreport_no,
                           @RequestParam(value="page_num", defaultValue="1") String pageNum,
                           @RequestParam(value="cooapproval_no") int cooapproval_no,
                           @RequestParam(value="last_approval",required=false) String last_approval,HttpSession session, Model model) {
         
    	  int member_no = (int) session.getAttribute("session_memberNo");
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("cooreport_no", cooreport_no);
         map.put("member_no", member_no);

         //게시글의 pk conreport_no와 member_no를 가지고 db 조회해서 해당 row를 list로 가져옴.
         List<CooperationReportAddFormDTO> getDetailList = cooReportService.selectCooperationBoardDetail(map);
         
         Map<String, CooperationReportAddFormDTO> approvalMap = new HashMap<String, CooperationReportAddFormDTO>();
         Map<String, String> approvalMan = new HashMap<String, String>();
         
         
         for (int i = 0; i < getDetailList.size(); i++) {
            getDetailList.toString();
            String key = "approvalMan" + (i+1);
            String nameKey = "approvalName" + (i+1);
            
            // pk값인 number_no로 들어오는것을 DB로 들어가서 이름으로 뽑아오기.
            int numberMember_no = getDetailList.get(i).getMember_no();
            String getNameByMemberNo = cooReportService.getNameByMemberNo(numberMember_no);
            
            approvalMap.put(key, getDetailList.get(i));
            approvalMan.put(nameKey, getNameByMemberNo);
         }
         int numberDepartment_no = getDetailList.get(1).getDepartment_no();
         String getNameByDepartmentNo = cooReportService.getNameByDepartmentNo(numberDepartment_no);
         model.addAttribute("getNameByDepartmentNo", getNameByDepartmentNo);
         
         model.addAttribute("last_approval",last_approval);
         model.addAttribute("cooapproval_no", cooapproval_no);
         model.addAttribute("type", "cooperation_report");
         model.addAttribute("approvalMap", approvalMap); // list를 approvalMan[i] 키로 DTO 저장한 map객체
         model.addAttribute("approvalMan",approvalMan);
         
         return "tiles/approval/approval_form";
      }
}