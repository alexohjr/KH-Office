package com.khacademy.khoffice.depart_board.daos;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.khacademy.khoffice.depart_board.models.DepartBoardDTO;



public class DepartBoardDAO extends SqlSessionDaoSupport {


	//게시글 목록
	public List<DepartBoardDTO> boardList(String string, Map<String, Object> object) {
		List<DepartBoardDTO> departBoardList = getSqlSession().selectList(string, object);
		return departBoardList;
	}
	
	//게시글 검색목록
	public List<DepartBoardDTO> getSearchListBoard(String string, Map<String, Object> object) {
		List<DepartBoardDTO> departBoardDTO = getSqlSession().selectList(string, object);
		return departBoardDTO;
	}
	
	//부서명출력
	public String getDepartmentName(String string, int member_no) {
		return getSqlSession().selectOne(string,member_no);
	}
	
	//게시글 등록
	public int boardInsert(String string, DepartBoardDTO departBoardDTO) {
		return getSqlSession().insert(string, departBoardDTO);
	}
	
	//게시글 등록자
	public DepartBoardDTO membertb(String string,int member_no) {
		return getSqlSession().selectOne(string, member_no);
	}
	
	//게시글 상세
	public DepartBoardDTO boardDetail(String string, int dboard_no) {
		return  getSqlSession().selectOne(string, dboard_no);
	}
	
	//조회수업데이트
	public int countupdate(String string, int boardNo) {
		return getSqlSession().update(string, boardNo);
	}

	//게시글 삭제
	public int boardDelete(String string, int dboard_no) {
		return getSqlSession().delete(string, dboard_no);
	}
	
	//게시글 수정
	public int boardUpdate(String string, DepartBoardDTO departBoardDTO) {
		return getSqlSession().update(string, departBoardDTO);
	}
	
	//게시글갯수
	public int articleCount(String string) {
		return getSqlSession().selectOne(string);
	}
	
	//게시글 검색 갯수
	public int articleSearchCount(String string, Map<String, Object> object) {
		return getSqlSession().selectOne(string, object); 
	}
   
}
