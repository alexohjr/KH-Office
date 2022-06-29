package com.khacademy.khoffice.task.daos;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.khacademy.khoffice.member.models.MemberDTO;
import com.khacademy.khoffice.task.models.Pager;
import com.khacademy.khoffice.task.models.ProjectTaskFileDTO;
import com.khacademy.khoffice.task.models.TaskDTO;
import com.khacademy.khoffice.task.models.TaskEditDTO;

public class TaskDAO extends SqlSessionDaoSupport {
	public List<ProjectTaskFileDTO> getProjectTaskFileDTOList(String string, Map<String, Object> map) {
		Pager pager = (Pager) map.get("pager");
		System.out.println(pager.getType());
		System.out.println(pager.getKeyword());
		return getSqlSession().selectList(string, map);
	}

	public int getTaskCount(String string, Map<String, Object> map) {
		return getSqlSession().selectOne(string, map);
	}

	// 태스크 생성 페이지 가져오기 (팀원 목록 출력)
	public List<MemberDTO> getProjectMember(String string, int project_no) {
		List<MemberDTO> memberList = getSqlSession().selectList(string, project_no);
		return memberList;
	}

	// 태스크 생성
	public int addTask(String string, TaskDTO taskDTO) {
		int x = getSqlSession().insert(string, taskDTO);
		return x;
	}

	// 태스크 멤버 추가
	public int addTaskMember(String string, Map<String, Object> object) {
		int x = getSqlSession().insert(string, object);
		return x;
	}

	// 태스크 상세 페이지 가져오기
	public TaskDTO getTaskDetail(String string, int task_no) {
		TaskDTO taskDTO = getSqlSession().selectOne(string, task_no);
		return taskDTO;
	}

	// 태스크 멤버 수정(삭제)
	public int deleteTaskMember(String string, int task_no) {
		int x = getSqlSession().delete(string, task_no);
		return x;
	}

	// 태스크 수정
	public int updateTask(String string, TaskEditDTO taskEditDTO) {
		int x = getSqlSession().update(string, taskEditDTO);
		return x;
	}

	// 태스크 삭제
	public int deleteTask(String string, int task_no) {
		int x = getSqlSession().delete(string, task_no);
		return x;
	}

	// 프로젝트 상세 에서 태스크 상태 변경
	public int updateTaskStatus(String string, Map<String, Object> object) {
		int x = getSqlSession().update(string, object);
		return x;
	}

	// 모든 태스크의 상태값 확인
	public int checkTaskStatus(String string, int project_no) {
		int x = getSqlSession().selectOne(string, project_no);
		return x;
	}

}
