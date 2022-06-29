package com.khacademy.khoffice.approval.daos;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.khacademy.khoffice.approval.models.VacationReportAddFormDTO;
import com.khacademy.khoffice.approval.models.VacationReportBoardListDTO;

public class VacationReportBoardListDAO extends SqlSessionDaoSupport {
	
	// 결재요청 버튼을 누른후 CONSULT_REPORT 테이블에 1행 삽입
	public int oneStepVacationInsertReport(VacationReportAddFormDTO getInfo) {
		return getSqlSession().insert("insert_report.vacation_write", getInfo);
	}
	
	// CONSULT_REPORT 테이블에 1행 삽입 후 바로 CONSULT_APPROVAL테이블에 1행 삽입.
	public int twoStepVacationInsertApproval(VacationReportAddFormDTO getInfo) {
		return getSqlSession().insert("insert_approval.vacation_write", getInfo);
	}
	
	// 마지막에 등록했던 ConsultReport의 conreport_no를 가져옴.
	public int selectLastVacreportNo() {
		return getSqlSession().selectOne("vacation_report_board.select_VacreportNo");
	}
	
	// 처음 결재작성 폼에 들어가기전에 로그인한 사람의 정보를 가져옴
	public VacationReportAddFormDTO vacationAddGetForm(int member_no) {
		return getSqlSession().selectOne("vacation_report_board.vacation_report_getForm", member_no);
	}
	
	// member_no를가지고 vacation_report 리스트 뽑아오기
	public List<VacationReportBoardListDTO> vacationBoardList(Map<String,Object> map) {
		return getSqlSession().selectList("vacation_report_board.vacation_report_list", map);
	}

	// member_no를 가지고 VACATION_REPORT의 count(*) 가져오기
	public int vacationBoardCount(int member_no) {
		return getSqlSession().selectOne("vacation_report_board.vacation_report_count", member_no);
	}
	
	//member_no와 게시글 pk vacreport_no를 가지고 그 페이지의 상세정보 가져오는 로직.
	public List<VacationReportAddFormDTO> selectVacationBoardDetail(Map<String,Object> map){
		return getSqlSession().selectList("vacation_board_detail.selectVacationDetailByNum", map);
	}
	
	public String getNameByMemberNo(int numberMember_no) {
		return getSqlSession().selectOne("vacation_board_detail.getNameByMemberNo",numberMember_no);
	}
}
