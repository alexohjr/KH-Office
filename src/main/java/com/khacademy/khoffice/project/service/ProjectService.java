package com.khacademy.khoffice.project.service;

import java.util.List;
import java.util.Map;

import com.khacademy.khoffice.member.models.MemberDTO;
import com.khacademy.khoffice.project.models.ProjectDTO;
import com.khacademy.khoffice.project.models.ProjectDetailDTO;
import com.khacademy.khoffice.project.models.ProjectListDTO;
import com.khacademy.khoffice.project.models.ProjectMemberDTO;
import com.khacademy.khoffice.task.models.TaskDTO;

public interface ProjectService {

	// 프로젝트 생성 
	public int addProject(ProjectDTO projectDTO);
	
	// 프로젝트 생성 팀원 insert
	public int addProjectMember(ProjectMemberDTO projectMemberDTO);
	
	// 프로젝트 생성 리더 insert
	public int addProjectLeader(ProjectMemberDTO projectMemberDTO);
	
	// 생성폼 리더 정보 출력
	public MemberDTO getLeaderInfo(int member_no);

	// 내가 속한 프로젝트 개수 
	public int getProjectCount(int member_no);
	
	// 리스트 프로젝트 정보
	public List<ProjectListDTO> getProjcetList(Map<String, Object> object);
	
	// 리스트 멤버 수
	public int getMemberCount(int project_no);
	
	// 리스트 태스크 개수
	public int getTaskCount(int project_no);
	
	// 리스트 리더 정보
	public MemberDTO getLeaderInfoList(int project_no);
	
	// 프로젝트 상세 보기
	public ProjectDetailDTO getDetail(int project_no);
	
	// 프로젝트 상세보기 리더정보출력
	public MemberDTO getLeaderInfoDetail(int project_no);
	
	// 프로젝트 팀원넘버 조회하기
	public List<ProjectMemberDTO> getProjectMembers(int project_no);
	
	// 프로젝트 팀원 정보 조회하기
	public MemberDTO getProjectMemberInfo(int member_no);
	
	// 프로젝트 수정하기
	public int updateProject(ProjectDTO projectDTO);
	
	// 프로젝트 멤버 수정 (추가하기)
	public int addNewProjectMember(Map<String, Object> object);
	
	// 프로젝트 멤버 수정 (삭제하기) - 삭제 멤버 태스크 존재 여부
	public int memberOnTask(Map<String, Object> object);

	// 프로젝트 멤버 수정 (삭제하기)
	public int deleteProjectMember(Map<String, Object> object);
	
	// 프로젝트 삭제하기
	public int deleteProject(int project_no);
	
	// 태스크 리스트 가져오기
	public List<TaskDTO> getTaskList(int project_no);
	
	// 태스크 리스트 담당자 정보 가져오기
	public List<MemberDTO> getMemberListByTaskNo(int task_no);
	
	// 프로젝트 상태 확인
	public int checkProjectStatus(int project_no);
	
	// 프로젝트 상태 변경 (태스크 에이잭스)
	public int updateProjectStatus(Map<String, Object> object);
}
