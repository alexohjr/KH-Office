package com.khacademy.khoffice.project.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.khacademy.khoffice.member.models.MemberDTO;
import com.khacademy.khoffice.project.daos.ProjectDAO;
import com.khacademy.khoffice.project.models.ProjectDTO;
import com.khacademy.khoffice.project.models.ProjectDetailDTO;
import com.khacademy.khoffice.project.models.ProjectListDTO;
import com.khacademy.khoffice.project.models.ProjectMemberDTO;
import com.khacademy.khoffice.task.models.TaskDTO;

@Service
public class ProjectServiceImpl implements ProjectService {
	private ProjectDAO projectDAO;

	@Autowired
	@Required
	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}
	
	// 프로젝트 생성 
	@Override
	public int addProject(ProjectDTO projectDTO) {
		int project_no = 0;
		
		project_no = projectDAO.addProject("project.addNewProject", projectDTO);
		
		return project_no;
	}
	
	// 프로젝트 생성 팀원 insert
	@Override
	public int addProjectMember(ProjectMemberDTO projectMemberDTO) {
		int x = 0;
		
		x = projectDAO.addProjectMember("project.addProjectMember", projectMemberDTO);
		
		if (x > 0) {
			System.out.println("멤버 인서트 성공");
		}
		else {
			System.out.println("멤버 인서트 실패");
		}
		
		return x;
	}
	
	// 프로젝트 생성 리더 insert
	@Override
	public int addProjectLeader(ProjectMemberDTO projectMemberDTO) {
		int x = 0;
		
		x = projectDAO.addProjectLeader("project.addProjectLeader", projectMemberDTO);
		
		if (x > 0) {
			System.out.println("리더 인서트 성공");
		}
		else {
			System.out.println("리더 인서트 실패");
		}
		
		return x; 
	}
	 
	// 생성폼 리더 정보 출력
	@Override
	public List<ProjectListDTO> getProjcetList(Map<String, Object> object) {
		List<ProjectListDTO> projectList = null;
		
		projectList = projectDAO.getProjectList("project.getProjectList", object);
		
		return projectList;
	}
	
	// 내가 속한 프로젝트 개수
	@Override
	public int getProjectCount(int member_no) {
		int x = 0;
		
		x = projectDAO.getProjectCount("project.getProjectCount", member_no);
		
		return x;
	}
	
	// 리스트 프로젝트 정보
	@Override
	public MemberDTO getLeaderInfo(int member_no) {
		MemberDTO memberDTO = null;
		
		memberDTO = projectDAO.getLeaderInfo("project.selectOne", member_no);
		
		return memberDTO;
	}
	
	// 리스트 멤버 수
	@Override
	public int getMemberCount(int project_no) {
		int memberCount = 0;
		memberCount = projectDAO.getMemberCount("project.getMemberCount", project_no);
		return memberCount;
	}
	
	// 리스트 태스크 개수
	@Override
	public int getTaskCount(int project_no) {
		int taskCount = 0;
		taskCount = projectDAO.getTaskCount("project.getTaskCount", project_no);
		return taskCount;
	}
	
	// 리스트 리더 정보
	@Override
	public MemberDTO getLeaderInfoList(int project_no)
	{
		MemberDTO memberDTO = null;
	
		memberDTO = projectDAO.getLeaderInfoList("project.getLeaderInfoList", project_no);
		
		return memberDTO;
	}
	
	// 프로젝트 상세 보기
	@Override
	public ProjectDetailDTO getDetail(int project_no) {
		ProjectDetailDTO projectDetailDTO = null;
		projectDetailDTO = projectDAO.getDetail("project.getDetail", project_no);
		return projectDetailDTO; 
	}
	
	// 프로젝트 상세 보기 리더 정보 출력
	@Override
	public MemberDTO getLeaderInfoDetail(int project_no) {
		MemberDTO memberDTO = null;
		memberDTO = projectDAO.getLeaderInfoDetail("project.getLeaderInfoDetail", project_no);
		return memberDTO;
	}
	
	// 프로젝트 팀원들 조회
	@Override
	public List<ProjectMemberDTO> getProjectMembers(int project_no) {
		List<ProjectMemberDTO> projectMemberList = projectDAO.getProjectMember("project.getProjectMember", project_no);
		return projectMemberList;
	}
	
	// 프로젝트 팀원 정보 조회하기
	@Override
	public MemberDTO getProjectMemberInfo(int member_no) {
		MemberDTO memberDTO = projectDAO.getProjectMemberInfo("project.getProjectMemberInfo", member_no);
		return memberDTO;  
	}
	
	// 프로젝트 수정하기
	@Override
	public int updateProject(ProjectDTO projectDTO) {
		int x = projectDAO.updateProject("project.updateProject", projectDTO);
		return x;
	}
	
	// 프로젝트 멤버 수정 (추가하기)
	@Override
	public int addNewProjectMember(Map<String, Object> object) {
		int x = projectDAO.addNewProjectMember("project.addNewProjectMember", object);
		return x;
	}
	
	// 프로젝트 멤버 수정 (삭제하기) - 삭제 멤버 태스크 존재 여부
	@Override
	public int memberOnTask(Map<String, Object> object)
	{
		int x = projectDAO.memberOnTask("project.memberOnTask", object);
	
		return x;
	}
	
	// 프로젝트 멤버 수정 (삭제하기)
	@Override
	public int deleteProjectMember(Map<String, Object> object)
	{
		int x = projectDAO.deleteProjectMember("project.deleteProjectMember", object);
	
		return x;
	}
	
	// 프로젝트 삭제하기
	@Override
	public int deleteProject(int project_no) {
		int x = projectDAO.deleteProject("project.deleteProject", project_no);
		return x;
	}
	
	// 태스크 리스트 가져오기
	@Override
	public List<TaskDTO> getTaskList(int project_no) {
		List<TaskDTO> taskList = projectDAO.getTaskList("task.getTaskList", project_no);
		return taskList;
	}
	
	// 태스트 리스트 담당자 정보 가져오기
	@Override
	public List<MemberDTO> getMemberListByTaskNo(int task_no) {
		List<MemberDTO> memberList = projectDAO.getMemberListByTaskNo("task.getMemberListByTaskNo", task_no);
		return memberList;
	}
	
	// 프로젝트 상태 확인
	@Override
	public int checkProjectStatus(int project_no) {
		int x = projectDAO.checkProjectStatus("project.checkProjectStatus", project_no);
		return x;
	}
	
	// 프로젝트 상태 변경
	@Override
	public int updateProjectStatus(Map<String, Object> object) {
		int x = projectDAO.updateProjectStatus("project.updateProjectStatus", object);
		return x;
	}
}
