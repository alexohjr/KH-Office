package com.khacademy.khoffice.depart_board.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.khacademy.khoffice.depart_board.daos.DepartBoardDAO;
import com.khacademy.khoffice.depart_board.models.DepartBoardDTO;

@Service
public class DepartBoardServiceImpl implements DepartBoardService {
	private DepartBoardDAO dao;

	@Autowired
	@Required
	public void setDao(DepartBoardDAO dao) {
		this.dao = dao;
	}

	// 게시글 목록
	public List<DepartBoardDTO> boardList(Map<String, Object> object) {
		List<DepartBoardDTO> list = dao.boardList("dpartboard.selectDpartBoardList", object);
		return list;
	}

	// 게시글 검색목록
	public List<DepartBoardDTO> listSearchBoard(Map<String, Object> object) {
		List<DepartBoardDTO> list = dao.getSearchListBoard("dpartboard.searchlist", object);
		return list;
	}

	// 부서명출력
	public String getDepartmentName(int member_no) {
		return dao.getDepartmentName("dpartboard.departmentname", member_no);
	}

	// 게시글 등록
	@Override
	public int boardInsert(DepartBoardDTO departBoardDTO) {
		return dao.boardInsert("dpartboard.insertDpartBoard", departBoardDTO);
	}

	// 게시글 등록자
	@Override
	public DepartBoardDTO membertb(int member_no) {
		DepartBoardDTO member = dao.membertb("dpartboard.selectmember", member_no);
		return member;
	}

	// 게시글 상세
	public DepartBoardDTO boardDetail(int dboard_no) {
		return dao.boardDetail("dpartboard.DpartBoardDetail", dboard_no);
	}

	// 조회수업데이트
	public int countUpdate(int dboard_no) {
		return dao.countupdate("dpartboard.countupdateDpartBoardDetail", dboard_no);
	}

	// 게시글 삭제
	@Override
	public int boardDelete(int dboard_no) {
		return dao.boardDelete("dpartboard.deleteDpartBoard", dboard_no);
	}

	// 게시글 수정
	public int boardUpdate(DepartBoardDTO departBoardDTO) {
		return dao.boardUpdate("dpartboard.updateDpartBoard", departBoardDTO);
	}

	// 게시글갯수
	public int articleCount() {
		return dao.articleCount("dpartboard.articleCount");
	}

	// 게시글 검색 갯수
	public int articleSearchCount(Map<String, Object> object) {
		return dao.articleSearchCount("dpartboard.articleSearchCount", object);
	}

}
