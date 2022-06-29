package com.khacademy.khoffice.anony_comment.services;


import java.util.List;
import java.util.Map;

import com.khacademy.khoffice.anony_comment.models.AnonyCommentDTO;


public interface AnonyCommentService {
	
	// 댓글 등록
	public int acommentInsert(AnonyCommentDTO anonyCommentDTO);
	
	//댓글 목록
	public List<AnonyCommentDTO> acommentList(Map<String, Object> object);

	//댓글 갯수
	public int commentCount(int acomment_No);
}

