package com.khacademy.khoffice.anony_comment.daos;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.khacademy.khoffice.anony_comment.models.AnonyCommentDTO;


public class AnonyCommentDAO extends SqlSessionDaoSupport {

	// 댓글등록
	public int acommentInsert(String string, AnonyCommentDTO anonyCommentDTO) {
		return getSqlSession().insert(string, anonyCommentDTO);
	}
	
	// 댓글목록
	public List<AnonyCommentDTO> acommentList(String string, Map<String, Object> object) {
		return getSqlSession().selectList(string, object);
	}
	
	// 댓글갯수
	public int commentCount(String string, int acomment_No) {
		return getSqlSession().selectOne(string, acomment_No);
	}


}

