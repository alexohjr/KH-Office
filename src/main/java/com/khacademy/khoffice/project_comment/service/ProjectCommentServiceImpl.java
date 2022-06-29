package com.khacademy.khoffice.project_comment.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.khacademy.khoffice.project_comment.daos.ProjectCommentDAO;
import com.khacademy.khoffice.project_comment.models.ProjectCommentDTO;

@Service
public class ProjectCommentServiceImpl implements ProjectCommentService {
	private ProjectCommentDAO projectCommentDAO;

	@Autowired
	@Required
	public void setProjectCommentDAO(ProjectCommentDAO projectCommentDAO) {
		this.projectCommentDAO = projectCommentDAO;
	}
	
	/*// pmember_no 값 구하기
	@Override
	public int getPmemberNo(Map<String, Object> object) {
		int pmember_no = 0;
		pmember_no = projectCommentDAO.getPmemberNo("project_comment.getPmemberNo", object);
		return pmember_no;
	}*/
	
	// 프로젝트 댓글 추가
	@Override
	public int addProjectComment(ProjectCommentDTO pcommentDTO) {
		int x = projectCommentDAO.addProjectComment("project_comment.addProjectComment", pcommentDTO);
		return x;
	}
	
	// 프로젝트 댓글 카운트
	public int getProjectCommentCount(int project_no) {
		int x = projectCommentDAO.getProjectCommentCount("project_comment.getProjectCommentCount", project_no);
		return x;
	}
	
	// 프로젝트 댓글 리스트 가져오기
	@Override
	public List<ProjectCommentDTO> getProjectCommentList(Map<String, Object> object) {
		List<ProjectCommentDTO> pcommentList = null;
		pcommentList = projectCommentDAO.getProjectCommentList("project_comment.getProjectCommentList", object);
		return pcommentList;
	}
	
	// 프로젝트 댓글 수정하기
	public int updateProjectComment(ProjectCommentDTO projectCommentDTO) {
		int x = projectCommentDAO.updateProjectComment("project_comment.updateProjectComment", projectCommentDTO);
		return x;
	}
	
	// 프로젝트 댓글 삭제하기
	public int deleteProjectComment(int pcomment_no) {
		int x = projectCommentDAO.deleteProjectComment("project_comment.deleteProjectComment", pcomment_no);
		return x;
	}
	
}
