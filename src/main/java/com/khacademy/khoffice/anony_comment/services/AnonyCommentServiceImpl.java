package com.khacademy.khoffice.anony_comment.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.khacademy.khoffice.anony_comment.daos.AnonyCommentDAO;
import com.khacademy.khoffice.anony_comment.models.AnonyCommentDTO;


@Service
public class AnonyCommentServiceImpl implements AnonyCommentService {
	private AnonyCommentDAO dao;

	@Autowired
	@Required
	public void setDao(AnonyCommentDAO dao) {
		this.dao = dao;
	
	}
	
	//댓글 목록
	@Override
	public List<AnonyCommentDTO> acommentList(Map<String, Object> object) {
		return dao.acommentList("anonycomment.AnonyCommentList",object);
	}    
	
	//댓글 등록
	@Override
	public int acommentInsert(AnonyCommentDTO anonyCommentDTO) {
		return dao.acommentInsert("anonycomment.insertAnonyComment", anonyCommentDTO);
	}
	
	//댓글 갯수
	public int commentCount(int acomment_No) {
		return dao.commentCount("anonycomment.articleCountAnonyComment", acomment_No);
	}
}
	
