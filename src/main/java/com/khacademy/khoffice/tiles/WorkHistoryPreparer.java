package com.khacademy.khoffice.tiles;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.khacademy.khoffice.work_history.models.WorkHistoryDTO;
import com.khacademy.khoffice.work_history.services.WorkHistoryService;

@Component
public class WorkHistoryPreparer implements ViewPreparer {

	WorkHistoryService workHistoryService;
	
	@Autowired
	@Required
	public void setWorkHistoryService(WorkHistoryService workHistoryService) {
		this.workHistoryService = workHistoryService;
	}

	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		
		Object memberSession = session.getAttribute("session_memberNo");
		Object adminSession = session.getAttribute("session_adminID");
		
		int member_no = 0;
		
		if(memberSession != null) {
			member_no = (int)memberSession;
		}
		
		if(memberSession != null && member_no != 0) {
			System.out.println("member_no 있음");
			WorkHistoryDTO historyDTO = new WorkHistoryDTO();
			historyDTO = workHistoryService.todayHasStartWork(member_no);
			if(historyDTO == null) { 
				// start + end 둘다 null 이므로 출근/퇴근버튼 on
				WorkHistoryDTO historyDTO2 = new WorkHistoryDTO();
				historyDTO2.setTodayHasStartWork("empty");
				historyDTO2.setTodayHasEndWork("empty");
				attributeContext.putAttribute("historyDTO", new Attribute(historyDTO2));			
			} else if (historyDTO.getStart_time() != null && historyDTO.getEnd_time() == null) {
				// 출근버튼은 한번 눌러서 insert됐고, 퇴근이 없을때 퇴근버튼 기능만 활성화
				historyDTO.setTodayHasStartWork("has");
				historyDTO.setTodayHasEndWork("empty");
				attributeContext.putAttribute("historyDTO", new Attribute(historyDTO));
			} else if (historyDTO.getStart_time() != null && historyDTO.getEnd_time() != null) {
				// 출근버튼과 퇴근버튼 둘다 값이 들어가있으면 둘다 비활성화
				historyDTO.setTodayHasStartWork("has");
				historyDTO.setTodayHasEndWork("has");
				attributeContext.putAttribute("historyDTO", new Attribute(historyDTO));
			}
		} else if (adminSession != null){ // admin일때는 else로 진입.
			System.out.println("어드민임");
			WorkHistoryDTO historyDTO2 = new WorkHistoryDTO();
			historyDTO2.setTodayHasStartWork("admin");
			attributeContext.putAttribute("historyDTO", new Attribute(historyDTO2));	
		}
	}

	
}
