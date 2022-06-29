package com.khacademy.khoffice.task.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.khacademy.khoffice.member.models.MemberDTO;
import com.khacademy.khoffice.project.service.ProjectService;
import com.khacademy.khoffice.task.models.Pager;
import com.khacademy.khoffice.task.models.ProjectTaskFileDTO;
import com.khacademy.khoffice.task.models.TaskDTO;
import com.khacademy.khoffice.task.models.TaskEditDTO;
import com.khacademy.khoffice.task.services.TaskService;

@Controller("taskController")
@RequestMapping("/task")
public class Controllers {
	private TaskService taskService;
	private ProjectService projectService;

	@Required
	@Autowired
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	@Autowired
	@Required
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getList(ProjectTaskFileDTO projectTaskFileDTO, Pager pager, ModelMap medel) {
		if (pager.getPage_num() == 0) {
			// 페이지번호가 넘어오지 않은 경우
			pager.setPage_num(1);
		}
		// 서비스 호출하여 목록 가져오기
		Map<String, Object> resultMap = taskService.getProjectTaskFileDTOList(projectTaskFileDTO, pager);
		medel.addAllAttributes(resultMap);

		return "tiles/project/task/list";
	}

	// 새로운 TASK 생성
	@RequestMapping(method = RequestMethod.POST)
	public String add(TaskDTO taskDTO, HttpServletRequest request, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		int project_no = taskDTO.getProject_no();
//			int loginMember = 1;
		int loginMember = (int) session.getAttribute("session_memberNo");

		// 파일 업로드
		MultipartFile fileup = taskDTO.getFile();

		if (fileup.getSize() > 0) {
			String file_path = fileup.getOriginalFilename();

			long now = System.currentTimeMillis();
			Random r = new Random();
			int i = r.nextInt(50);
			file_path = now + i + "_" + file_path;
			taskDTO.setFile_path(file_path);

			String realPath = request.getServletContext().getRealPath("/");
			String path = "resources/upload/task/";

			String fullPath = realPath + path;

			try {
				File new_file = new File(fullPath + file_path);
				fileup.transferTo(new_file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			taskDTO.setFile_path("");
		}

		taskService.addTask(taskDTO);
		int task_no = taskDTO.getTask_no();
		map.put("task_no", task_no);
		map.put("project_no", taskDTO.getProject_no());

		// 선택된 task 담당자들 배열로 가져옴
		int memberArr[] = taskDTO.getMember_nos();
		for (int m = 0; m < taskDTO.getMember_nos().length; m++) {
			map.put("member_no", memberArr[m]);
			taskService.addTaskMember(map);
		}
		return "redirect:/task/" + task_no + "?project_no=" + project_no;
	}

	// 태스트 상세 페이지
	@RequestMapping(value = "/{taskNo}", method = RequestMethod.GET)
	public String getDetail(@PathVariable("taskNo") int task_no, HttpServletRequest request, HttpSession session,
			ModelMap model) {
//			int member_no = 1;
		int member_no = (int) session.getAttribute("session_memberNo");
		int project_no = new Integer(request.getParameter("project_no"));

		TaskDTO taskDTO = taskService.getTaskDetail(task_no);

		List<MemberDTO> memberList = projectService.getMemberListByTaskNo(task_no);

		taskDTO.setMemberList(memberList);

		// 프로젝트 상태 정보 문자화
		String taskStatus = taskDTO.getStatus();
		if (taskStatus.equals("0")) {
			taskStatus = "w";
		} else if (taskStatus.equals("1")) {
			taskStatus = "p";
		} else if (taskStatus.equals("2")) {
			taskStatus = "c";
		} 

		taskDTO.setStatus(taskStatus);

		// 원본 파일명으로 만들기
		if (taskDTO.getFile_path() != null) {
			String fileName = taskDTO.getFile_path();
			int index = fileName.indexOf("_");
			String downloadFileName = fileName.substring(index + 1);
			taskDTO.setFile_path(downloadFileName);
		}

		int flag = 0;
		for (int i = 0; i < taskDTO.getMemberList().size(); i++) {
			if (member_no == taskDTO.getMemberList().get(i).getMember_no()) {
				flag += 1;
			}
		}

		model.addAttribute("flag", flag);
		model.addAttribute("taskDTO", taskDTO);
		model.addAttribute("member_no", member_no);
		model.addAttribute("project_no", project_no);

		return "tiles/project/task/detail";
	}

	// 태스크 수정
	@RequestMapping(value = "/{taskNo}", method = RequestMethod.POST)
	public String update(@PathVariable("taskNo") int task_no, HttpServletRequest request, HttpSession session,
			TaskEditDTO taskEditDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		String redirect = "";

		String type = request.getParameter("type");
		int project_no = new Integer(request.getParameter("project_no"));
//			int member_no = 1;
		int member_no = (int) session.getAttribute("session_memberNo");

		if (type.equals("edit") || type == "edit") {
			map.put("project_no", project_no);
			map.put("task_no", task_no);

			// 기존 태스크 멤버들 전부 삭제 후 새로운 멤버 insert
			taskService.deleteTaskMember(task_no);
			int[] newTaskMember = taskEditDTO.getMember_nos();

			for (int i = 0; i < newTaskMember.length; i++) {
				map.put("member_no", newTaskMember[i]);
				taskService.addTaskMember(map);
			}

			// 파일 업로드
			MultipartFile fileup = taskEditDTO.getFile();

			// 새로운 파일이 업로드됨
			if (fileup.getSize() > 0) {
				String file_path = fileup.getOriginalFilename();

				long now = System.currentTimeMillis();
				Random r = new Random();
				int i = r.nextInt(50);
				file_path = now + i + "_" + file_path;
				taskEditDTO.setFile_path(file_path);
				String realPath = request.getServletContext().getRealPath("/");
				String path = "resources/upload/task/";
				String fullPath = realPath + path;
				try {
					File new_file = new File(fullPath + file_path);
					fileup.transferTo(new_file);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			String projectStatus = request.getParameter("projectStatus");
			String taskStatus = taskEditDTO.getStatus();

			taskService.updateTask(taskEditDTO);

			System.out.println("projectStatus :: " + projectStatus);

			if (!projectStatus.equals("2") && !projectStatus.equals("3")) {
				if (taskStatus.equals("2")) {
					int taskStatusCount = taskService.checkTaskStatus(project_no);
					System.out.println("taskStatusCount :: " + taskStatusCount);
					if (taskStatusCount == 0) {
						map.put("project_no", project_no);
						map.put("status", new Integer(1));
						projectService.updateProjectStatus(map);
					}
				}
			}

			redirect = "redirect:/task/" + task_no + "?project_no=" + project_no;

		} else if (type.equals("delete") || type == "delete") {
			taskService.deleteTask(task_no);
			redirect = "redirect:/project/" + project_no;
		}
		return redirect;
	}

}
