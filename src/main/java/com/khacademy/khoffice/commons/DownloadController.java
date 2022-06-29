package com.khacademy.khoffice.commons;

import java.io.File;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.khacademy.khoffice.depart_board.services.DepartBoardService;
import com.khacademy.khoffice.notice_board.services.NoticeBoardService;
import com.khacademy.khoffice.task.services.TaskService;

@Controller("downloadController")
@RequestMapping("/commons")
public class DownloadController implements ApplicationContextAware {
	private TaskService taskService;
	private NoticeBoardService nbservice;
	private DepartBoardService dbservice;
	
	@Required
	@Autowired
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	@Autowired
	@Required	
	public void setNbservice(NoticeBoardService nbservice) {
		this.nbservice = nbservice;
	}
	
	@Autowired
	@Required
	public void setDbservice(DepartBoardService dbservice) {
		this.dbservice = dbservice;
	}
	
	private WebApplicationContext context = null;
	
	@RequestMapping("/download/{tableName}/{no}")
	public ModelAndView download(
		@PathVariable String tableName,
		@PathVariable int no
	) {
		File downloadFile = getFile(tableName, no);
		return new ModelAndView("downloadView", "downloadFile", downloadFile); // 뷰이름, 모델이름, 모델객체 순서
	}
	
	// 자동으로 호출되면서 현재 클래스의 컨텍스트를 어플리케이션 컨텍스트로 맞춘다.
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = (WebApplicationContext) applicationContext;
	}
	
	private File getFile(String tableName, int no) {
		String dbPath = null;
		String path = null;
		if(tableName.equals("task")) {
			dbPath = taskService.getTaskDetail(no).getFile_path();
			path = "resources/upload/task/";
		} else if(tableName.equals("notice_board")) {
			dbPath = nbservice.boardDetail(no).getFile_path();
			path = "resources/upload/board/";
		} else if(tableName.equals("depart_board")) {
			dbPath = dbservice.boardDetail(no).getFile_path();
			path = "resources/upload/board/";
		}
		
		String realPath = context.getServletContext().getRealPath("/"); // 서버에 실제로 올라간 프로젝트명 까지의 절대경로
		String fullPath = realPath + path + dbPath;
		return new File(fullPath);
	}
	
}
