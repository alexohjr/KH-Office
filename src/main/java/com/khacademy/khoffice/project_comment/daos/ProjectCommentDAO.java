package com.khacademy.khoffice.project_comment.daos;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.khacademy.khoffice.project_comment.models.ProjectCommentDTO;

public class ProjectCommentDAO extends SqlSessionDaoSupport{
	
	/*// pmember_no 값 구하기
	public int getPmemberNo(String string, Map<String, Object> object) {
		int pmember_no = 0;
		pmember_no = getSqlSession().selectOne(string, object);
		return pmember_no;
	}*/
	
	// 프로젝트 댓글 추가
	public int addProjectComment(String string, ProjectCommentDTO pcommentDTO) {
		return getSqlSession().insert(string, pcommentDTO);
	}
	
	// 댓글 개수 가져오기
	public int getProjectCommentCount(String string, int project_no) {
		int x = 0;
		x = getSqlSession().selectOne(string, project_no);
		return x; 
	}
	
	// 프로젝트 리스트 가져오기
	public List<ProjectCommentDTO> getProjectCommentList(String string, Map<String, Object> object) {
		List<ProjectCommentDTO> pcommentList = getSqlSession().selectList(string, object);
		return pcommentList;
	}
	
	// 프로젝트 댓글 수정하기
	public int updateProjectComment(String string, ProjectCommentDTO pcommentDTO) {
		return getSqlSession().update(string, pcommentDTO);
	}
	
	// 프로젝트 댓글 삭제하기
	public int deleteProjectComment(String string, int pcomment_no) {
		return getSqlSession().delete(string, pcomment_no);
	}
	
} 
