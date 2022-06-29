package com.khacademy.khoffice.anony_board.daos;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.khacademy.khoffice.anony_board.models.AnonyBoardDTO;



public class AnonyBoardDAO extends SqlSessionDaoSupport {


	//게시글 목록
	public List<AnonyBoardDTO> boardList(String string, Map<String, Object> object) {
		List<AnonyBoardDTO> anonyBoardDTO = getSqlSession().selectList(string, object);
		return anonyBoardDTO;
	}
	
	//게시글 검색목록
	public List<AnonyBoardDTO> getSearchListBoard(String string, Map<String, Object> object) {
		List<AnonyBoardDTO> anonyBoardDTO = getSqlSession().selectList(string, object);
		return anonyBoardDTO;
	}
	
	//게시글 등록
	public int boardInsert(String string, AnonyBoardDTO anonyBoardDTO) {
		return getSqlSession().insert(string, anonyBoardDTO);
	}
	
	//게시글 상세
	public AnonyBoardDTO boardDetail(String string, int aboard_no) {
		return  getSqlSession().selectOne(string, aboard_no);
	}
	
	//조회수업데이트
	public int countupdate(String string, int aboardNo) {
		return getSqlSession().update(string, aboardNo);
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
