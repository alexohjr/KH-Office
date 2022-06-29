package com.khacademy.khoffice.notice_board.daos;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.khacademy.khoffice.notice_board.models.NoticeBoardDTO;



public class NoticeBoardDAO extends SqlSessionDaoSupport {


	//게시글 목록
	public List<NoticeBoardDTO> boardList(String string, Map<String, Object> object) {
		
		System.out.println("object.get(\"start\") : " + object.get("start"));
		System.out.println("object.get(\"end\") : " + object.get("end"));
		
		
		
		List<NoticeBoardDTO> noticeBoardDTO = getSqlSession().selectList(string, object);
		return noticeBoardDTO;
	}
	
	//게시글 검색목록
	public List<NoticeBoardDTO> getSearchListBoard(String string, Map<String, Object> object) {
		List<NoticeBoardDTO> noticeBoardDTO = getSqlSession().selectList(string, object);
		return noticeBoardDTO;
	}
	
	//게시글 상단
	public List<NoticeBoardDTO> getListIsTop(String string) {
		List<NoticeBoardDTO> noticeBoardDTO = getSqlSession().selectList(string);
		return noticeBoardDTO;
	}
	
	//게시글 등록
	public int boardInsert(String string, NoticeBoardDTO noticeBoardDTO) {
		return getSqlSession().insert(string, noticeBoardDTO);
	}
	
	//게시글 등록자
	public NoticeBoardDTO membertb(String string,int member_no) {
		return getSqlSession().selectOne(string, member_no);
	}
	
	//게시글 상세
	public NoticeBoardDTO boardDetail(String string, int nboard_no) {
		return  getSqlSession().selectOne(string, nboard_no);
	}
	
	//조회수업데이트
	public int countupdate(String string, int boardNo) {
		return getSqlSession().update(string, boardNo);
	}

	//게시글 삭제
	public int boardDelete(String string, int nboard_no) {
		return getSqlSession().delete(string, nboard_no);
	}
	
	//게시글 수정
	public int boardUpdate(String string, NoticeBoardDTO noticeBoardDTO) {
		return getSqlSession().update(string, noticeBoardDTO);
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
