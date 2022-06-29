package com.khacademy.khoffice.anony_board.services;

import java.util.List;
import java.util.Map;

import com.khacademy.khoffice.anony_board.models.AnonyBoardDTO;


public interface AnonyBoardService {
	
	//게시글 목록
	public List<AnonyBoardDTO> boardList(Map<String, Object> objects);
	
	//게시글 검색 목록
	public List<AnonyBoardDTO> listSearchBoard(Map<String, Object> object);
	
	//게시글 등록
	public int boardInsert(AnonyBoardDTO anonyBoardDTO);

	//게시글 상세
	public AnonyBoardDTO boardDetail(int aboard_no);
	
	//게시글 조회수
	public int countUpdate(int aboard_no);

	//게시글 갯수
	public int articleCount();
	
	//게시글 검색 갯수
	public int articleSearchCount(Map<String, Object> object);

}