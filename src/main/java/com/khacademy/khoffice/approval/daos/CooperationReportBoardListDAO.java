package com.khacademy.khoffice.approval.daos;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.khacademy.khoffice.approval.models.CooperationReportAddFormDTO;
import com.khacademy.khoffice.approval.models.CooperationReportBoardListDTO;

public class CooperationReportBoardListDAO extends SqlSessionDaoSupport{

	// 처음 결재작성 폼에 들어가기전에 로그인한 사람의 정보를 가져옴
	public CooperationReportAddFormDTO cooperationAddGetForm(int member_no) {
		return getSqlSession().selectOne("cooperation_report_board.cooperation_report_getForm", member_no);
	}
	
	// 마지막에 등록했던 CoosultReport의 cooreport_no를 가져옴.
	public int selectLastCooreportNo() {
		return getSqlSession().selectOne("cooperation_report_board.select_CooreportNo");
	}
	
	// member_no를가지고 cooperation_report 리스트 뽑아오기
	public List<CooperationReportBoardListDTO> cooperationBoardList(Map<String,Object> map) {
		return getSqlSession().selectList("cooperation_report_board.cooperation_report_list", map);
	}

	// member_no를 가지고 CONSULT_REPORT 의 count(*) 가져오기.
	public int cooperationBoardCount(int member_no) {
		return getSqlSession().selectOne("cooperation_report_board.cooperation_report_count", member_no);
	}
	
	// 결재요청 버튼을 누른후 CONSULT_REPORT 테이블에 1행 삽입
	public int oneStepCooperationInsertReport(CooperationReportAddFormDTO getInfo) {
		return getSqlSession().insert("insert_report.cooperation_write", getInfo);
	}
	
	// Cooperation_REPORT 테이블에 1행 삽입 후 바로 Cooperation_APPROVAL테이블에 1행 삽입.
	public int twoStepCooperationInsertApproval(CooperationReportAddFormDTO getInfo) {
		return getSqlSession().insert("insert_approval.cooperation_write", getInfo);
	}
	
	// member_no와 게시글 pk cooreport_no를 가지고 그 페이지의 상세정보 가져오는 로직.
	public List<CooperationReportAddFormDTO> selectCooperationBoardDetail(Map<String,Object> map){
		return getSqlSession().selectList("cooperation_board_detail.selectCooperationDetailByNum", map);
	}
	
	// DB에서 조회한 member_no값으로 승인해야할 사원의 name값을 가져옴
	public String getNameByMemberNo(int numberMember_no) {
		return getSqlSession().selectOne("cooperation_board_detail.getNameByMemberNo", numberMember_no);
	}
	
	public String getNameByDepartmentNo(int numberDepartment_no) {
		return getSqlSession().selectOne("cooperation_board_detail.getNameByDepartmentNo", numberDepartment_no);
	}
}
