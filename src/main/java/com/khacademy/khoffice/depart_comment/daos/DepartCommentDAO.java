package com.khacademy.khoffice.depart_comment.daos;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.khacademy.khoffice.depart_board.models.DepartBoardDTO;
import com.khacademy.khoffice.depart_comment.models.DepartCommentDTO;



public class DepartCommentDAO extends SqlSessionDaoSupport {

	// 댓글등록
	public int dcommentInsert(String string, DepartCommentDTO departCommentDTO) {
		return getSqlSession().insert(string, departCommentDTO);
	}
	
	// 댓글삭제
	public int dcommentDelete(String string, int dcomment_No) {
		return getSqlSession().delete(string, dcomment_No);
	}
	
	// 댓글목록
	public List<DepartCommentDTO> dcommentList(String string, Map<String, Object> object) {
		return getSqlSession().selectList(string, object);
	}
	
	// 댓글수정
	public int dcommentUpdate(String string, DepartCommentDTO departCommentDTO) {
		System.out.println("dao실행");
		return  getSqlSession().update(string, departCommentDTO);
	}
	
	// 댓글갯수
	public int commentCount(String string, int dcomment_No) {
		return getSqlSession().selectOne(string, dcomment_No);
	}

}

