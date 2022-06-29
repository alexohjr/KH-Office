package com.khacademy.khoffice.depart_board.services;

import java.util.List;
import java.util.Map;

import com.khacademy.khoffice.depart_board.models.DepartBoardDTO;


public interface DepartBoardService {
	
	//게시글 목록
	public List<DepartBoardDTO> boardList(Map<String, Object> objects);
	
	//게시글 검색 목록
	public List<DepartBoardDTO> listSearchBoard(Map<String, Object> object);
	
	//부서명출력
	public String getDepartmentName(int member_no);
	
	//게시글 등록
	public int boardInsert(DepartBoardDTO departBoardDTO);
	
	//게시글 등록자
	public DepartBoardDTO membertb(int member_no);

	//게시글 상세
	public DepartBoardDTO boardDetail(int dboard_no);
	
	//게시글 조회수
	public int countUpdate(int dboard_no);
	
	//게시글 삭제
	public int boardDelete(int dboard_no);
	
	//게시글 수정
	public int boardUpdate(DepartBoardDTO departBoardDTO);
	
	//게시글 갯수
	public int articleCount();
	
	//게시글 검색 갯수
	public int articleSearchCount(Map<String, Object> object);

}