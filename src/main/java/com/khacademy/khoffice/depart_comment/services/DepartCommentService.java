package com.khacademy.khoffice.depart_comment.services;


import java.util.List;
import java.util.Map;

import com.khacademy.khoffice.depart_comment.models.DepartCommentDTO;


public interface DepartCommentService {
	
	// 댓글 등록
	public int dcommentInsert(DepartCommentDTO departCommentDTO);
	
	//댓글 삭제
	public int dcommentDelete(int dcomment_No);

	//댓글 목록
	public List<DepartCommentDTO> dcommentList(Map<String, Object> object);

	//댓글 수정
	public int dcommentUpdate(DepartCommentDTO departCommentDTO);
	
	
	//댓글 갯수
	public int commentCount(int dcomment_No);
}

