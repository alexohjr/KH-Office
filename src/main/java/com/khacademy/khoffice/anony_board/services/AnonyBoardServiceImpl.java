package com.khacademy.khoffice.anony_board.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.khacademy.khoffice.anony_board.daos.AnonyBoardDAO;
import com.khacademy.khoffice.anony_board.models.AnonyBoardDTO;



@Service
public class AnonyBoardServiceImpl implements AnonyBoardService {
	private AnonyBoardDAO dao;

	@Autowired
	@Required
	public void setDao(AnonyBoardDAO dao) {
		this.dao = dao;
	}

	//게시글 목록
	public List<AnonyBoardDTO> boardList(Map<String, Object> object) {
		List<AnonyBoardDTO> list = dao.boardList("anonyboard.selectAnonyBoardList", object);
		return list;
	} 
	
	//게시글 검색목록
	public List<AnonyBoardDTO> listSearchBoard(Map<String, Object> object) {
		List<AnonyBoardDTO> list = dao.getSearchListBoard("anonyboard.searchlist", object);
		return list;
	}
	
	//게시글 등록
	@Override
	public int boardInsert(AnonyBoardDTO anonyBoardDTO) {
		return dao.boardInsert("anonyboard.insertAnonyBoard", anonyBoardDTO);
	}
	
	//게시글 상세
	public AnonyBoardDTO boardDetail(int aboard_no) {
		return dao.boardDetail("anonyboard.AnonyBoardDetail", aboard_no);
	}
	
	//조회수업데이트
	public int countUpdate(int aboard_no) {
		return dao.countupdate("anonyboard.countupdateAnonyBoardDetail", aboard_no);
	}

	//게시글갯수
	public int articleCount() {
		return dao.articleCount("anonyboard.articleCount");
	}
	
	//게시글 검색 갯수
	public int articleSearchCount(Map<String, Object> object) {
		return dao.articleSearchCount("anonyboard.articleSearchCount", object);
	}

}
	
