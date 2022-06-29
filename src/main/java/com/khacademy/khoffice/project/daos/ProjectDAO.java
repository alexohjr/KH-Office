package com.khacademy.khoffice.project.daos;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.khacademy.khoffice.member.models.MemberDTO;
import com.khacademy.khoffice.project.models.ProjectDTO;
import com.khacademy.khoffice.project.models.ProjectDetailDTO;
import com.khacademy.khoffice.project.models.ProjectListDTO;
import com.khacademy.khoffice.project.models.ProjectMemberDTO;
import com.khacademy.khoffice.task.models.TaskDTO;

public class ProjectDAO extends SqlSessionDaoSupport{
	
	// 프로젝트 생성 
	public int addProject(String string, ProjectDTO projectDTO) {
		return getSqlSession().insert(string, projectDTO);
	}
	
	// 프로젝트 생성 팀원 insert
	public int addProjectMember(String string, ProjectMemberDTO projectMemberDTO) {
		return getSqlSession().insert(string, projectMemberDTO);
	}
	
	// 프로젝트 생성 리더 insert
	public int addProjectLeader(String string, ProjectMemberDTO projectMemberDTO) {
		return getSqlSession().insert(string, projectMemberDTO);
	}
	
	// 생성폼 리더 정보 출력
	public MemberDTO getLeaderInfo(String string, int member_no) {
		MemberDTO memberDTO = getSqlSession().selectOne(string, member_no);
		return memberDTO;
	}
	
	// 내가 속한 프로젝트 정보
	public int getProjectCount(String string, int member_no) {
		int x = getSqlSession().selectOne(string, member_no);
		return x;
	}
	
	// 리스트 프로젝트 정보
	public List<ProjectListDTO> getProjectList(String string, Map<String, Object> object) {
		List<ProjectListDTO> projectList = getSqlSession().selectList(string, object);
		return projectList;
	}
	
	// 리스트 멤버 수
	public int getMemberCount(String string, int project_no) {
		return getSqlSession().selectOne(string, project_no);
	}
	
	// 리스트 태스크 개수
	public int getTaskCount(String string, int project_no) {
		return getSqlSession().selectOne(string, project_no);
	}
	
	// 리스트 리더 정보
	public MemberDTO getLeaderInfoList(String string, int project_no) {
		MemberDTO memberDTO = null;
		memberDTO = getSqlSession().selectOne(string, project_no);
		return memberDTO;
	}
	
	// 프로젝트 상세 보기
	public ProjectDetailDTO getDetail(String string, int project_no) {
		ProjectDetailDTO projectDTO = getSqlSession().selectOne(string, project_no);
		return projectDTO;
	}
	
	// 프로젝트 상세 보기 리더 정보 출력
	public MemberDTO getLeaderInfoDetail(String string, int project_no) {
		MemberDTO memberDTO = getSqlSession().selectOne(string, project_no);
		return memberDTO;
	}
	
	// 프로젝트 팀원 출력
	public List<ProjectMemberDTO> getProjectMember(String string, int project_no) {
		List<ProjectMemberDTO> projectMemberList = getSqlSession().selectList(string, project_no);
		return projectMemberList;  
	}
	
	// 프로젝트 팀원 정보 조회하기
	public MemberDTO getProjectMemberInfo(String string, int member_no) {
		MemberDTO memberDTO = getSqlSession().selectOne(string, member_no);
		return memberDTO;
	}
	
	// 프로젝트 수정하기
	public int updateProject(String string, ProjectDTO projectDTO) {
		return getSqlSession().update(string, projectDTO);
	}
	
	// 프로젝트 멤버 수정 (추가하기)
	public int addNewProjectMember(String string, Map<String, Object> object) {
		return getSqlSession().insert(string, object);
	}
	
	// 프로젝트 멤버 수정 (삭제하기) - 삭제 멤버 태스크 존재 여부
	public int memberOnTask(String string, Map<String, Object> object) {
		int x = getSqlSession().selectOne(string, object);
		
		return x;
	}
	
	// 프로젝트 멤버 수정 (삭제하기)
	public int deleteProjectMember(String string, Map<String, Object> object) {
		return getSqlSession().delete(string, object);
	}
	
	// 프로젝트 삭제하기
	public int deleteProject(String string, int project_no) {
		return getSqlSession().delete(string, project_no);
	}
	
	// 태스크 리스트 가져오기
	public List<TaskDTO> getTaskList(String string, int project_no) {
		List<TaskDTO> taskList = getSqlSession().selectList(string, project_no);
		
		return taskList;
	}
	
	// 태스크 리스트 담당자 정보 가져오기
	public List<MemberDTO> getMemberListByTaskNo(String string, int task_no) {
		List<MemberDTO> memberList = getSqlSession().selectList(string, task_no);
		return memberList;
	}
	
	// 프로젝트 상태 확인
	public int checkProjectStatus(String string, int project_no) {
		int x = getSqlSession().selectOne(string, project_no);
		return x;
	}
	
	// 프로젝트 상태 변경 (태스크 에이잭스)
	public int updateProjectStatus(String string, Map<String, Object> object) {
		int x = getSqlSession().update(string, object);
		return x;
	}
	
} 
