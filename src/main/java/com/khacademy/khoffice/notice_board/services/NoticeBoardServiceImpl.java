package com.khacademy.khoffice.notice_board.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.khacademy.khoffice.notice_board.daos.NoticeBoardDAO;
import com.khacademy.khoffice.notice_board.models.NoticeBoardDTO;


@Service
public class NoticeBoardServiceImpl implements NoticeBoardService {
	private NoticeBoardDAO dao;

	@Autowired
	@Required
	public void setDao(NoticeBoardDAO dao) {
		this.dao = dao;
	}

	//게시글 목록
	public List<NoticeBoardDTO> boardList(Map<String, Object> object) {
		List<NoticeBoardDTO> list = dao.boardList("noticeboard.selectNoticeBoardList", object);
		return list;
	} 
	
	//게시글 검색목록
	public List<NoticeBoardDTO> listSearchBoard(Map<String, Object> object) {
		List<NoticeBoardDTO> list = dao.getSearchListBoard("noticeboard.searchlist", object);
		return list;
	}
	
	//게시글 상단고정
	public List<NoticeBoardDTO> ListIsTop() {
		List<NoticeBoardDTO> list = dao.getListIsTop("noticeboard.selectNoticeBoardIsTop");
		return list;
	}
	
	//조회수업데이트
	public int ListIsTop(int dboard_no) {
		return dao.countupdate("noticeboard.selectNoticeBoardList", dboard_no);
	}
	
	//게시글 등록
	@Override
	public int boardInsert(NoticeBoardDTO noticeBoardDTO) {
		return dao.boardInsert("noticeboard.insertNoticeBoard", noticeBoardDTO);
	}
	
	//게시글 상세
	public NoticeBoardDTO boardDetail(int dboard_no) {
		return dao.boardDetail("noticeboard.NoticeBoardDetail", dboard_no);
	}
	
	//조회수업데이트
	public int countUpdate(int dboard_no) {
		return dao.countupdate("noticeboard.countupdateNoticeBoardDetail", dboard_no);
	}
	
	//게시글 삭제
	@Override
	public int boardDelete(int dboard_no) {
		return dao.boardDelete("noticeboard.deleteNoticeBoard", dboard_no);
	}
	
	//게시글 수정
	public int boardUpdate(NoticeBoardDTO noticeBoardDTO) {
		return dao.boardUpdate("noticeboard.updateNoticeBoard", noticeBoardDTO);
	}
	
	//게시글갯수
	public int articleCount() {
		return dao.articleCount("noticeboard.articleCount");
	}
	
	//게시글 검색 갯수
	public int articleSearchCount(Map<String, Object> object) {
		return dao.articleSearchCount("noticeboard.articleSearchCount", object);
	}

	
}
	
