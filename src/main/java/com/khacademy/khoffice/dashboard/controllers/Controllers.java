package com.khacademy.khoffice.dashboard.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.khacademy.khoffice.address.models.AddressDTO;
import com.khacademy.khoffice.address.services.AddressService;
import com.khacademy.khoffice.approval.models.VacationApprovalBoardListDTO;
import com.khacademy.khoffice.approval.models.VacationReportBoardListDTO;
import com.khacademy.khoffice.chat_window.services.ChatWindowService;
import com.khacademy.khoffice.member.models.MemberDTO;
import com.khacademy.khoffice.notice_board.models.NoticeBoardDTO;
import com.khacademy.khoffice.notice_board.services.NoticeBoardService;
import com.khacademy.khoffice.project.models.ProjectListDTO;
import com.khacademy.khoffice.project.service.ProjectService;
import com.khacademy.khoffice.task.models.TaskDTO;
import com.khacademy.khoffice.vacation_approval.services.VacationApprovalService;
import com.khacademy.khoffice.vacation_report.services.VacationReportService;

@Controller("dashboardController")
@RequestMapping("/dashboard")
public class Controllers {

	private ChatWindowService chatWindowService;
	private ProjectService projectService;
	private NoticeBoardService nbservice;
	private VacationReportService vacReportService;
	private VacationApprovalService vacApprovalService;
	private AddressService addressService;

	@Autowired
	@Required
	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	@Autowired

	@Required
	public void setNbService(NoticeBoardService nbservice) {
		this.nbservice = nbservice;
	}

	@Autowired
	@Required
	public void setChatWindowService(ChatWindowService chatWindowService) {
		this.chatWindowService = chatWindowService;
	}

	@Autowired
	@Required
	public void setMemberService(ProjectService projectService) {
		this.projectService = projectService;
	}

	@Autowired
	@Required
	public void setVacReportService(VacationReportService vacReportService) {
		this.vacReportService = vacReportService;
	}

	@Autowired
	@Required
	public void setVacApprovalService(VacationApprovalService vacApprovalService) {
		this.vacApprovalService = vacApprovalService;
	}

	// 대시보드 띄우기
	@RequestMapping(method = RequestMethod.GET)
	public String getDashboard(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model, @RequestParam(value = "page_num", defaultValue = "1") int pageNum) {
		int sessionId = (int) session.getAttribute("session_memberNo");
		// 서비스 호출하여 목록 가져오기
		Map<String, Object> mapChat = chatWindowService.getChatWindowDTOList(pageNum, sessionId);

		response.setContentType("text/html;charset=utf-8");
//				int member_no = (int) session.getAttribute("session_memberNo");

		List<ProjectListDTO> projectList = new ArrayList<ProjectListDTO>();
		List<TaskDTO> taskList = null;
		Map<String, Object> mapProject = new HashMap<String, Object>();

		int pageSize = 4; // 첫 페이지 리스트 개수
		int currentPage = Integer.parseInt("1");
		int startRow = (currentPage - 1) * pageSize + 1; // 한 페이지의 시작글 번호
		int endRow = currentPage * pageSize; // 한 페이지의 마지막 글번호

		mapProject.put("startRow", startRow);
		mapProject.put("endRow", endRow);
		mapProject.put("member_no", sessionId);

		projectList = projectService.getProjcetList(mapProject);
		String projectStatus = "";
		MemberDTO memberDTO = null;

		for (int i = 0; i < projectList.size(); i++) {
			// 프로젝트 상태 문자열로 바꾸기
			projectStatus = projectList.get(i).getStatus();
			if (projectStatus.equals("0")) {
				projectList.get(i).setStatus("p");
			} else if (projectStatus.equals("1")) {
				projectList.get(i).setStatus("c");
			} else if (projectStatus.equals("2")) {
				projectList.get(i).setStatus("w");
			} else if (projectStatus.equals("3")) {
				projectList.get(i).setStatus("s");
			}

			// 해당 project_no 값 가져오기
			int project_no = projectList.get(i).getProject_no();

			// 각 프로젝트의 멤버 수 구한 후 projectList에 값 넣어주기
			int memberCount = projectService.getMemberCount(project_no);
			projectList.get(i).setMemberCount(memberCount - 1);

			// 각 프로젝트의 태스크 수 구한 후 projectList에 값 넣어주기
			int taskCount = projectService.getTaskCount(project_no);
			projectList.get(i).setTaskCount(taskCount);

			// 프로젝트 리더 정보 조회하기
			memberDTO = projectService.getLeaderInfoList(project_no);

			// 프로젝트 리더 정보 list에 담기
			projectList.get(i).setLeaderName(memberDTO.getName());
			projectList.get(i).setLeaderPosition(memberDTO.getPosition());
			projectList.get(i).setLeaderThumb(memberDTO.getThumb_path());

			// 프로젝트 프로그레스 바
			taskList = projectService.getTaskList(project_no);

			// 태스크 개수
			taskCount = taskList.size();
			double taskProgress = 0;
			// 프로젝트 진행률
			double total = 0;

			for (int j = 0; j < taskList.size(); j++) {
				String status = taskList.get(j).getStatus();
				double statusNum = Double.parseDouble(status);

				if (statusNum == 0) {
					statusNum = 0; // 0
				} else if (statusNum == 1) {
					statusNum = 0.5; // 0.5
				} else if (statusNum == 2) {
					statusNum = 1; // 1
				}
				total += statusNum;
			}
			double projectProgress = total / taskCount * 100;

			long projectProgress2 = Math.round(projectProgress);

			projectList.get(i).setProgress(projectProgress2);

		}

		// 공지사항
		Map<String, Object> mapNotice = new HashMap<String, Object>();

		int start = 1; // 한 페이지의 시작글 번호
		int end = 10; // 한 페이지의 마지막 글번호

		mapNotice.put("start", start);
		mapNotice.put("end", end);

		List<NoticeBoardDTO> list = nbservice.boardList(mapNotice);
		int articleCount = nbservice.articleCount();

		// 결제
		List<Integer> getMyVacreport_noList = vacApprovalService.getMyVacreport_no(sessionId);
		List<VacationApprovalBoardListDTO> myApprovalList = new ArrayList<VacationApprovalBoardListDTO>();

		// list로받은 vacreport_no를 들고 있는 list만큼 for문돌려 approvalList로 뿌리기위한 작업.
		for (int i = 0; i < getMyVacreport_noList.size(); i++) {

			int vacreport_no = getMyVacreport_noList.get(i);

			List<VacationApprovalBoardListDTO> listReport = vacApprovalService.getMyApprovalRowsByNo(vacreport_no);

			// 분기처리 시작.
			if (listReport.get(0).getMember_no() == sessionId && listReport.get(0).getStatus().equals("0")) {
				myApprovalList.add(listReport.get(0));
			}
			if (listReport.get(1).getMember_no() == sessionId && listReport.get(0).getStatus().equals("1")
					&& listReport.get(1).getStatus().equals("0")) {
				myApprovalList.add(listReport.get(1));
			}
			if (listReport.get(2).getMember_no() == sessionId && listReport.get(0).getStatus().equals("1")
					&& listReport.get(1).getStatus().equals("1") && listReport.get(2).getStatus().equals("0")) {
				listReport.get(2).setLast_approval("last");
				myApprovalList.add(listReport.get(2));
			}
		}
		int reportCount = myApprovalList.size();
		Map<String, Object> mapReport = new HashMap<String, Object>();
		mapReport.put("start", start);
		mapReport.put("end", end);
		mapReport.put("member_no", sessionId);
		List<VacationReportBoardListDTO> listReport = vacReportService.list(mapReport);

		// 개인주소록
		Map<String, Object> mapAdress = new HashMap<String, Object>();
		mapAdress.put("member_no", sessionId);
		mapAdress.put("start", start);
		mapAdress.put("end", end);
		List<AddressDTO> listAddress = null;
		listAddress = addressService.getAddressList(mapAdress);

		// 최종 모델
		// 개인주소록
		model.addAttribute("listAddress", listAddress);
		// 결제
		model.addAttribute("reportCount", reportCount);
		model.addAttribute("listReport", listReport);
		// 공지사항

		model.addAttribute("list", list);
		model.addAttribute("articleCount", articleCount);

		// 채팅
		model.addAllAttributes(mapChat);
		// 프로젝트
		model.addAttribute("project", projectList);

		return "tiles/dashboard";
	}
}