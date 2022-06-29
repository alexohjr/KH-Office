package com.khacademy.khoffice.depart_comment.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.khacademy.khoffice.depart_board.models.DepartBoardDTO;
import com.khacademy.khoffice.depart_comment.daos.DepartCommentDAO;
import com.khacademy.khoffice.depart_comment.models.DepartCommentDTO;



@Service
public class DepartCommentServiceImpl implements DepartCommentService {
	private DepartCommentDAO dao;

	@Autowired
	@Required
	public void setDao(DepartCommentDAO dao) {
		this.dao = dao;
	
	}
	
	//댓글 목록
	@Override
	public List<DepartCommentDTO> dcommentList(Map<String, Object> object) {
		return dao.dcommentList("dpartcomment.DpartCommentList",object);
	}    
	
	//댓글 등록
	@Override
	public int dcommentInsert(DepartCommentDTO departCommentDTO) {
		return dao.dcommentInsert("dpartcomment.insertDpartComment", departCommentDTO);
	}
	
	//댓글 삭제
	public int dcommentDelete(int dcomment_No) {
		return dao.dcommentDelete("dpartcomment.deleteDpartComment", dcomment_No);
	}
 
	//댓글 수정
	public int dcommentUpdate(DepartCommentDTO departCommentDTO) {
		System.out.println("Impl실행");
		return dao.dcommentUpdate("dpartcomment.updateDpartComment", departCommentDTO);
	}
	
	//댓글 갯수
	public int commentCount(int dcomment_No) {
		return dao.commentCount("dpartcomment.articleCountDpartComment", dcomment_No);
	}
}
	
