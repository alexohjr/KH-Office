package com.khacademy.khoffice.task.services;

import java.util.List;
import java.util.Map;

import com.khacademy.khoffice.member.models.MemberDTO;
import com.khacademy.khoffice.task.models.Pager;
import com.khacademy.khoffice.task.models.ProjectTaskFileDTO;
import com.khacademy.khoffice.task.models.TaskDTO;
import com.khacademy.khoffice.task.models.TaskEditDTO;

public interface TaskService {

	public Map<String, Object> getProjectTaskFileDTOList(ProjectTaskFileDTO proejctTaskFileDTO, Pager pager);

	// 태스크 생성 페이지 가져오기 (팀원 목록 가져오기)
	public List<MemberDTO> getProjectMember(int project_no);

	// 태스크 생성
	public int addTask(TaskDTO taskDTO);

	// 태스크 멤버 추가
	public int addTaskMember(Map<String, Object> object);

	// 태스크 상세 페이지 가져오기
	public TaskDTO getTaskDetail(int task_no);

	// 태스크 멤버 수정 (삭제)
	public int deleteTaskMember(int task_no);

	// 태스크 수정
	public int updateTask(TaskEditDTO taskEditDTO);

	// 태스크 삭제
	public int deleteTask(int task_no);

	// 프로젝트 상세에서 태스크 상태 변경
	public int updateTaskStatus(Map<String, Object> object);

	// 모든 태스크의 상태값 확인
	public int checkTaskStatus(int project_no);

}
