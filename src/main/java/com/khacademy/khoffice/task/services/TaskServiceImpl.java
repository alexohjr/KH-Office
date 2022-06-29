package com.khacademy.khoffice.task.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.khacademy.khoffice.member.models.MemberDTO;
import com.khacademy.khoffice.task.daos.TaskDAO;
import com.khacademy.khoffice.task.models.Pager;
import com.khacademy.khoffice.task.models.ProjectTaskFileDTO;
import com.khacademy.khoffice.task.models.TaskDTO;
import com.khacademy.khoffice.task.models.TaskEditDTO;

@Service
public class TaskServiceImpl implements TaskService{
	private TaskDAO taskDAO;

	@Required
	@Autowired
	public void setTaskDAO(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}

	@Override
	public Map<String, Object> getProjectTaskFileDTOList(ProjectTaskFileDTO proejctTaskFileDTO, Pager pager) {
		
		System.out.println(pager.getType());
		System.out.println(pager.getKeyword());
		
		// 페이징 계산
		int pageSize = 10; // 한 페이지에 보여줄 글 수
		int pagerSize = 5; // 페이저 갯수
		
		int postCount = 0;
		int pageCount = 0;
		int startPager = 0;
		int endPager = 0;
		String queryString = ""; // 페이징시 넘겨줄 쿼리스트링
		
		int pageNum = pager.getPage_num();
		
		System.out.println("pageNum : " + pageNum);
		
		pager.setStart((pageNum - 1) * pageSize + 1); // 페이저 시작 수
		pager.setEnd(pageNum * pageSize); // 페이저 끝 수
		
		// 리스트 구해오기
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("proejctTaskFileDTO", proejctTaskFileDTO);
		map.put("pager", pager);

		List<ProjectTaskFileDTO> projectTaskFileDTOList = taskDAO.getProjectTaskFileDTOList("task.selectProjectTaskByStartEnd", map);
		
 		postCount = taskDAO.getTaskCount("task.selectCount", map); // 전체 글 갯수
		
		if (postCount > 0) { // 글이 있는 경우에
			pageCount = postCount / pageSize + (postCount % pageSize == 0 ? 0 : 1);

			int tmp = (pageNum % pagerSize) == 0 ? (pageNum / pagerSize) - 1 : (pageNum / pagerSize);
			startPager = (tmp * pagerSize + 1);
			endPager = startPager + pagerSize - 1;

			if (endPager > pageCount) {
				endPager = pageCount;
			}
		}
//		"&page_num=" + pageNum + 
		queryString = "keyword=" + pager.getKeyword() + "&type=" + pager.getType();
		
		pager.setPostCount(postCount);
		pager.setPageCount(pageCount);
		pager.setStartPager(startPager);
		pager.setEndPager(endPager);
		pager.setPagerSize(pagerSize);
		pager.setPageSize(pageSize);
		pager.setQueryString(queryString);
		pager.setPage_num(pageNum);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("projectTaskFileDTOList", projectTaskFileDTOList);
		resultMap.put("pager", pager);
		
		return resultMap;
	}

	// 프로젝트 생성 페이지 가져오기 (팀원 목록 출력)
	@Override
	public List<MemberDTO> getProjectMember(int project_no) {
		List<MemberDTO> memberList = taskDAO.getProjectMember("task.getProjectMember", project_no);
		return memberList;
	}

	// 태스크 생성
	@Override
	public int addTask(TaskDTO taskDTO) {
		int x = taskDAO.addTask("task.addTask", taskDTO);
		return x;
	}

	// 태스크 멤버 추가
	@Override
	public int addTaskMember(Map<String, Object> object) {
		int x = taskDAO.addTaskMember("task.addTaskMember", object);
		return x;
	}

	// 태스크 상세 페이지 가져오기
	@Override
	public TaskDTO getTaskDetail(int task_no) {
		TaskDTO taskDTO = taskDAO.getTaskDetail("task.getTaskDetail", task_no);
		return taskDTO;
	}

	// 태스크 멤버 수정 (삭제)
	@Override
	public int deleteTaskMember(int task_no) {
		int x = taskDAO.deleteTaskMember("task.deleteTaskMember", task_no);
		return x;
	}

	// 태스크 수정
	@Override
	public int updateTask(TaskEditDTO taskEditDTO) {
		int x = taskDAO.updateTask("task.updateTask", taskEditDTO);
		return x;
	}

	// 태스크 삭제
	@Override
	public int deleteTask(int task_no) {
		int x = taskDAO.deleteTask("task.deleteTask", task_no);
		return x;
	}

	// 프로젝트 상세에서 태스크 상태 변경
	@Override
	public int updateTaskStatus(Map<String, Object> object) {
		int x = taskDAO.updateTaskStatus("task.updateTaskStatus", object);
		return x;
	}

	// 모든 태스크의 상태값 확인
	@Override
	public int checkTaskStatus(int project_no) {
		int x = taskDAO.checkTaskStatus("task.checkTaskStatus", project_no);
		return x;
	}
	
}
