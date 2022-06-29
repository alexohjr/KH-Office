package com.khacademy.khoffice.project.controllers;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.khacademy.khoffice.member.models.MemberDTO;
import com.khacademy.khoffice.project.models.ProjectDTO;
import com.khacademy.khoffice.project.models.ProjectDetailDTO;
import com.khacademy.khoffice.project.models.ProjectListDTO;
import com.khacademy.khoffice.project.models.ProjectMemberDTO;
import com.khacademy.khoffice.project.service.ProjectService;
import com.khacademy.khoffice.project_comment.models.ProjectCommentDTO;
import com.khacademy.khoffice.project_comment.service.ProjectCommentService;
import com.khacademy.khoffice.task.models.TaskDTO;

@Controller("projectControllers")
@RequestMapping("/project")
public class Controllers {
	
	private ProjectService projectService;
	private ProjectCommentService projectCommentService;

	@Autowired
	@Required
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	@Autowired
	@Required
	public void setProjectCommentService(ProjectCommentService projectCommentService) {
		this.projectCommentService = projectCommentService;
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	// 프로젝트 리스트 출력
	@RequestMapping(method=RequestMethod.GET, produces="text/plain;charset=utf-8")
	public String getList(HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession session) {
		response.setContentType("text/html;charset=utf-8");
		int member_no = (int) session.getAttribute("session_memberNo");
//		int member_no = 1;
		
		List<ProjectListDTO> projectList = new ArrayList<ProjectListDTO>();
		List<TaskDTO> taskList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		
		int projectCount = projectService.getProjectCount(member_no);
		
		int pageSize = 8; // 첫 페이지 리스트 개수
		int currentPage = Integer.parseInt("1");
		int startRow = (currentPage - 1) * pageSize + 1; // 한 페이지의 시작글 번호
		int endRow = currentPage * pageSize; // 한 페이지의 마지막 글번호
		
		
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("member_no", member_no);

		projectList = projectService.getProjcetList(map);
		
		String projectStatus = "";
		
		MemberDTO memberDTO = null;
		   
		if (projectCount > 0) {
			for (int i=0; i < projectList.size(); i++) {
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
				projectList.get(i).setMemberCount(memberCount-1);
				
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
						
				for(int j=0; j<taskList.size(); j++) {
					String status = taskList.get(j).getStatus();
					double statusNum = Double.parseDouble(status);
								
					if(statusNum == 0) { 
						statusNum = 0;   // 0
					} else if(statusNum == 1) {
						statusNum = 0.5; // 0.5
					} else if(statusNum == 2) {
						statusNum = 1; // 1
					}
					total += statusNum;
				}
				double projectProgress = total/taskCount * 100;
				long projectProgress2 = Math.round(projectProgress);
				projectList.get(i).setProgress(projectProgress2);
			}
		}
		model.addAttribute("project", projectList);
		model.addAttribute("projectCount", projectCount);
		return "tiles/project/list";
	}
	
	// 새로운 프로젝트 생성
	@RequestMapping(method=RequestMethod.POST)
	public String add(ProjectDTO projectDTO, ProjectMemberDTO projectMemberDTO, HttpServletRequest request) {
		
		// 선택된 팀원 배열로 가져옴
		int memberArr[] = projectMemberDTO.getMember_nos();
		// 프로젝트 생성
		int project_no = projectService.addProject(projectDTO);
		// 생성된 프로젝트의 project_no 값 가져옴
		project_no = projectDTO.getProject_no();
		projectMemberDTO.setProject_no(project_no);

		// 팀원 수 많큼 project_member 테이블에 insert
		for (int i=0; i < projectMemberDTO.getMember_nos().length; i++) {
			projectMemberDTO.setMember_no(memberArr[i]);
			int x = projectService.addProjectMember(projectMemberDTO);
		}
		
		// 프로젝트 리더 insert
		projectMemberDTO.setMember_no(projectMemberDTO.getIs_leader());
		int ok = projectService.addProjectLeader(projectMemberDTO);
		return "redirect:/project";
	}
	
	// 프로젝트 상세 보기
	@RequestMapping(value="/{projectNo}", method=RequestMethod.GET)
	public String getDetail(@PathVariable("projectNo") int project_no, ModelMap model, HttpServletRequest request, HttpSession session
			) {
		ProjectDetailDTO projectDetailDTO = null;
		MemberDTO leaderDTO = null;
		MemberDTO memberDTO = null;
		List<ProjectMemberDTO> projectMemberList = null;
		List<TaskDTO> taskList = null;
		List<ProjectCommentDTO> projectCommentList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		
//		int loginMember = 1;
		int loginMember = (int) session.getAttribute("session_memberNo");
		 
		// 프로젝트 상세 정보 가져오기
		projectDetailDTO = projectService.getDetail(project_no);
		String projectStatus = projectDetailDTO.getStatus();
		  
		// 프로젝트 상태 정보 문자화
		if (projectStatus.equals("0")) {
			projectStatus = "p";
		} else if (projectStatus.equals("1")) {
			projectStatus = "c";
		} else if (projectStatus.equals("2")) {
			projectStatus = "w";
		} else if (projectStatus.equals("3")) {
			projectStatus = "s";
		}
		projectDetailDTO.setStatus(projectStatus);
		
		// 프로젝트 리더 정보 가져오기
		leaderDTO = projectService.getLeaderInfoDetail(project_no);
		// 프로젝트 팀원넘버 목록 가져오기
		projectMemberList = projectService.getProjectMembers(project_no);
			// 프로젝트 팀원 정보 가져오기
		for (int i=0; i < projectMemberList.size(); i++) {
			int projectMemberNo = projectMemberList.get(i).getMember_no();
			memberDTO = projectService.getProjectMemberInfo(projectMemberNo);
			projectMemberList.get(i).setName(memberDTO.getName());
			projectMemberList.get(i).setPosition(memberDTO.getPosition());
			projectMemberList.get(i).setMember_no(memberDTO.getMember_no());
			projectMemberList.get(i).setThumb_path(memberDTO.getThumb_path());
		}
		
		// 프로젝트 프로그레스 바
		
		taskList = projectService.getTaskList(project_no);
		
		// 태스크 개수
		int taskCount = taskList.size();
		double taskProgress = 0;
		// 프로젝트 진행률
		double total = 0;
		
		for(int i=0; i<taskList.size(); i++) {
			String status = taskList.get(i).getStatus();
			double statusNum = Double.parseDouble(status);
				
			if(statusNum == 0) {
				statusNum = 0;   // 0
			} else if(statusNum == 1) {
				statusNum = 0.5; // 0.5  
			} else if(statusNum == 2) {
				statusNum = 1; // 1
			}
			total += statusNum;
			
			if (taskList.get(i).getFile_path() != null) {
				String fileName = taskList.get(i).getFile_path();
				int index = fileName.indexOf("_");
				String downloadFileName = fileName.substring(index+1);
				taskList.get(i).setFile_path(downloadFileName);
			}
		}
		double projectProgress = total/taskCount * 100;
		
		projectProgress = Math.round(projectProgress*10)/10.0;
		
		// 태스크 리스트 뿌리기
		int memberCount = 0;
		
		// 로그인한 사람이 태스크의 담당자 인지 구분
		int flag = 0;
		
		for(int i=0; i<taskList.size(); i++) {
			int task_no = taskList.get(i).getTask_no();
			List<MemberDTO> memberList = projectService.getMemberListByTaskNo(task_no);
			taskList.get(i).setMemberList(memberList);
		}
		
		
		// 프로젝트 댓글 리스트 뿌리기
		
		int projectCommentCount = projectCommentService.getProjectCommentCount(project_no);
		
		if(projectCommentCount > 0) {
			int pageSize = 5; // 한페이지의 댓글의 개수
			int currentPage = Integer.parseInt("1");
			int startRow = (currentPage - 1) * pageSize + 1; // 한 페이지의 시작글 번호
			int endRow = currentPage * pageSize; // 한 페이지의 마지막 글번호
			
			map.put("startRow", startRow);
			map.put("endRow", endRow);
			
			
			map.put("project_no", project_no);
			projectCommentList = projectCommentService.getProjectCommentList(map);
			model.addAttribute("projectCommentList", projectCommentList);
		}
		 
		model.addAttribute("project", projectDetailDTO);
		model.addAttribute("leader", leaderDTO);
		model.addAttribute("projectMember", projectMemberList);
		model.addAttribute("projectMemberCount", projectMemberList.size());
		model.addAttribute("loginMember", loginMember);
		model.addAttribute("taskList", taskList);
		model.addAttribute("projectProgress", projectProgress);
		model.addAttribute("flag", flag);
		model.addAttribute("projectCommentCount", projectCommentCount);
		
		return "tiles/project/detail";
	}	
	
	// 프로젝트 수정/삭제
   @RequestMapping(value="/{projectNo}", method=RequestMethod.POST)
   public String update(@PathVariable("projectNo") int project_no, HttpServletRequest request, ProjectDTO projectDTO, ProjectMemberDTO projectMemberDTO, ModelMap model) {
	  Map<String, Object> map = new HashMap<String, Object>();
      String redirect = "";
      String type = request.getParameter("type");
      List<ProjectMemberDTO> projectMemberList = new ArrayList<>();
      
      // 프로젝트 수정
      if (type.equals("edit")) {
         int x = projectService.updateProject(projectDTO);
         
         // 기존 member_no 들의 값
         projectMemberList = projectService.getProjectMembers(project_no);
         
         // 수정된 member_no 들의 값
         int[] tmp = projectMemberDTO.getMember_nos();
         
         // 수정된 member_no 들의 값을 List형으로 복제
         List<Integer> memberList = new ArrayList<>();
         for(int i =0; i < tmp.length; i++) {
            memberList.add(tmp[i]);
         }
         
         // 기존 list와 수정후 list값을 복제
         List<Integer> modifyTmp = new ArrayList<>(memberList);
         List<ProjectMemberDTO> originTmp = new ArrayList<>(projectMemberList);
         
         for(ProjectMemberDTO now : originTmp){
            for(int member : modifyTmp) {
               if(now.getMember_no() == member) {
                  int index = memberList.indexOf(member);
                  projectMemberList.remove(now);
                  memberList.remove(index);
               }
            }
         }
         
	     // 프로젝트 멤버 삭제
         for (int k=0; k< projectMemberList.size(); k++) {
            map.put("member_no", projectMemberList.get(k).getMember_no());
    		map.put("project_no", project_no);
    		projectService.deleteProjectMember(map);
         }
         
         // 프로젝트 멤버 추가
         for (int l=0; l< memberList.size(); l++) {
    		map.put("member_no", memberList.get(l).intValue());
    		map.put("project_no", project_no);
            projectService.addNewProjectMember(map);
         }
	         
         redirect = "redirect:/project/"+project_no;
      } else if (type.equals("delete")) {
         int x = projectService.deleteProject(project_no);
         redirect = "redirect:/project";
      }
      return redirect;
   }
}
