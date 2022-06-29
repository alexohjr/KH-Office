package com.khacademy.khoffice.project_comment.service;

import java.util.List;
import java.util.Map;

import com.khacademy.khoffice.project_comment.models.ProjectCommentDTO;

public interface ProjectCommentService {

	/*// pmember_no 값 구하기
	public int getPmemberNo(Map<String, Object> object);*/
	
	// 프로젝트 댓글 추가
	public int addProjectComment(ProjectCommentDTO projectCommentDTO);
	
	// 프로젝트 댓글 카운트
	public int getProjectCommentCount(int project_no);
	
	// 프로젝트 리스트 가져오기
	public List<ProjectCommentDTO> getProjectCommentList(Map<String, Object> object);
	
	// 프로젝트 댓글 수정하기
	public int updateProjectComment(ProjectCommentDTO projectCommentDTO);
	
	// 프로젝트 댓글 삭제하기
	public int deleteProjectComment(int pcomment_no);
}
 