package com.khacademy.khoffice.work_history.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.khacademy.khoffice.work_history.models.WorkHistoryDTO;
import com.khacademy.khoffice.work_history.services.WorkHistoryService;

@Controller("work_history_ajax")
@RequestMapping("/work_history")
public class AjaxController {
   
   private WorkHistoryService workHistoryService;
   
   @Autowired
   @Required
   public void setWorkHistoryService(WorkHistoryService workHistoryService) {
      this.workHistoryService = workHistoryService;
   }

   // 일반회원이 접속할때 접근하는 메서드.
   @RequestMapping(value="/ajax/{member_no}", method=RequestMethod.POST, produces="text/plain;charset=utf-8")
   @ResponseBody
   public String update(@PathVariable("member_no") String member_no,
                  @RequestParam(value="memo", required=false) String memo,
                  @RequestParam(value="start_time", required=false) String start_time,
                  @RequestParam(value="end_time" , required=false) String end_time,
                  @RequestParam(value="work_history_no", required=false) String work_history_no,
                  @RequestParam(value="editTime", required=false) String editTime,
                  @RequestParam(value="admin", required=false) String admin,
                  Model model, 
                  HttpServletResponse response,
                  HttpSession session, 
                  HttpServletRequest request) {
      /* HttpServletRequest request선언하지 않고도 ip 얻어올 수 있는 소스.
       * String ipAddr = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes())
                 .getRequest().getRemoteAddr();*/
      
      WorkHistoryDTO historyDTO = new WorkHistoryDTO();
      Gson gson = new Gson();
      String json = "";
      
      // 출근버튼 클릭했을때 로직
      if(start_time != null && member_no.equals("출근")) {
         int session_memberNo = (int) session.getAttribute("session_memberNo");
         String start_ip = getRequestIp(request);
         historyDTO.setStart_ip(start_ip);
         historyDTO.setMember_no(session_memberNo);
         workHistoryService.insertStartTimeByMemberNo(historyDTO);
         json = gson.toJson(historyDTO);
         //start_time을 sysdate로 insert후 return
      }
      
      // memo에서 enter 눌렀을때 로직
      if(memo != null && work_history_no != null && member_no.equals("edit")) { 
         int session_memberNo = (int) session.getAttribute("session_memberNo");
         historyDTO.setMember_no(session_memberNo);
         historyDTO.setMemo(memo);
         historyDTO.setWork_history_no(Integer.parseInt(work_history_no));
         workHistoryService.updateMemoByNo(historyDTO);
         json = gson.toJson(historyDTO);
         // memo update후 select한 dto를 success로 return.
      }
      // 퇴근버튼 클릭했을때 로직
      if(end_time != null && member_no.equals("퇴근")) { 
         int session_memberNo = (int) session.getAttribute("session_memberNo");
         String end_ip = getRequestIp(request);
         WorkHistoryDTO selectHistoryDTO = workHistoryService.todayHasStartWork(session_memberNo);
         System.out.println("asdf : "+selectHistoryDTO.getWork_history_no());
         historyDTO.setEnd_ip(end_ip);
         historyDTO.setMember_no(session_memberNo);
         historyDTO.setWork_history_no(selectHistoryDTO.getWork_history_no());
         System.out.println("후" +historyDTO);
         workHistoryService.insertEndTimeByMemberNo(historyDTO);
         json = gson.toJson(historyDTO);
         //오늘 날짜와 동일한 태그의 work_history_no를 받아와 
         //end_time을 SYSDATE로 update후 return
      }
      /* 어드민 출/퇴근 추가,수정 로직 */
      if(work_history_no != null && member_no.equals("어드민수정")) { // 있는데이터 수정할때
         historyDTO = workHistoryService.selectWorkHistoryByHistoryNo(work_history_no);
         if(start_time.equals("출근수정")) {
            historyDTO.setStrStart(editTime);
            workHistoryService.updateStartTimeByAdmin(historyDTO);
         }
         if(end_time.equals("퇴근수정")) {
            historyDTO.setStrEnd(editTime);
            String end_ip = getRequestIp(request);
            historyDTO.setEnd_ip(end_ip);
            workHistoryService.updateEndTimeByAdmin(historyDTO);
         }
         
         json = gson.toJson(historyDTO);
      }
      /* 어드민 빈공간에 출근기록 추가 로직 */
      if(member_no != null && admin != null && admin.equals("어드민추가")) {
         historyDTO.setMember_no(Integer.parseInt(member_no));
         if(start_time.equals("출근추가")) {
            String start_ip = getRequestIp(request);
            historyDTO.setStart_ip(start_ip);
            historyDTO.setStrStart(editTime);
            workHistoryService.insertAdminStartTimeByMemberNo(historyDTO);
            json = gson.toJson(historyDTO);
         }
         
      }
      if(member_no != null && admin != null && member_no.equals("삭제")) {
         workHistoryService.deleteAdminByWorkHistoryNo(work_history_no);
      }
      
      response.setContentType("text/html;charset=utf-8");
      return   json.toString();
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   public String getRequestIp(HttpServletRequest request) {
      String ip = request.getHeader("X-FORWARDED-FOR");
      
      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getHeader("Proxy-Client-IP");
       }
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getHeader("WL-Proxy-Client-IP");
       }
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getHeader("HTTP_CLIENT_IP");
       }
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getHeader("HTTP_X_FORWARDED_FOR");
       }
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
           ip = request.getRemoteAddr();
       }

      return ip;
   }
   
   
}