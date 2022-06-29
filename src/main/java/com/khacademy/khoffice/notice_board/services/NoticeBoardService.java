package com.khacademy.khoffice.notice_board.services;

import java.util.List;
import java.util.Map;

import com.khacademy.khoffice.notice_board.models.NoticeBoardDTO;


public interface NoticeBoardService {
	
	//게시글 목록
	public List<NoticeBoardDTO> boardList(Map<String, Object> objects);
	
	//게시글 검색 목록
	public List<NoticeBoardDTO> listSearchBoard(Map<String, Object> object);
	
	//게시글 상단
	public List<NoticeBoardDTO> ListIsTop();
	
	//게시글 등록
	public int boardInsert(NoticeBoardDTO noticeBoardDTO);

	//게시글 상세
	public NoticeBoardDTO boardDetail(int nboard_no);
	
	//게시글 조회수
	public int countUpdate(int nboard_no);
	
	//게시글 삭제
	public int boardDelete(int nboard_no);
	
	//게시글 수정
	public int boardUpdate(NoticeBoardDTO noticeBoardDTO);
	
	//게시글 갯수
	public int articleCount();
	
	//게시글 검색 갯수
	public int articleSearchCount(Map<String, Object> object);

}