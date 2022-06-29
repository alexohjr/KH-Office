package com.khacademy.khoffice.approval.daos;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.khacademy.khoffice.approval.models.ConsultReportAddFormDTO;
import com.khacademy.khoffice.approval.models.ConsultReportBoardListDTO;

public class ConsultReportBoardListDAO extends SqlSessionDaoSupport {
	
	// 마지막에 등록했던 ConsultReport의 conreport_no를 가져옴.
	public int selectLastConreportNo() {
		return getSqlSession().selectOne("consult_report_board.select_ConreportNo");
	}

	// 처음 결재작성 폼에 들어가기전에 로그인한 사람의 정보를 가져옴
	public ConsultReportAddFormDTO consultAddGetForm(int member_no) {
		return getSqlSession().selectOne("consult_report_board.consult_report_getForm", member_no);
	}
	
	// 결재요청 버튼을 누른후 CONSULT_REPORT 테이블에 1행 삽입
	public int oneStepConsultInsertReport(ConsultReportAddFormDTO getInfo) {
		return getSqlSession().insert("insert_report.consult_write", getInfo);
	}

	// CONSULT_REPORT 테이블에 1행 삽입 후 바로 CONSULT_APPROVAL테이블에 1행 삽입.
	public int twoStepConsultInsertApproval(ConsultReportAddFormDTO getInfo) {
		return getSqlSession().insert("insert_approval.consult_write", getInfo);
	}
	
	
	// member_no를가지고 CONSULT_REPORT 리스트 뽑아와서 게시판목록으로 사용.
	public List<ConsultReportBoardListDTO> consultBoardList(Map<String,Object> map) {
		return getSqlSession().selectList("consult_report_board.consult_report_list", map);
	}
	
	// member_no를 가지고 CONSULT_REPORT 의 count(*) 가져오기.
	public int consultBoardCount(int member_no) {
		return getSqlSession().selectOne("consult_report_board.consult_report_count", member_no);
	}
	
	// member_no와 게시글 pk conreport_no를 가지고 그 페이지의 상세정보 가져오는 로직.
	public List<ConsultReportAddFormDTO> selectConsultBoardDetail(Map<String,Object> map) {
		return getSqlSession().selectList("consult_board_detail.selectConsultDetailByNum",map);
	}
	
	// DB에서 조회한 member_no값으로 승인해야할 사원의 name값을 가져옴
	public String getNameByMemberNo(int numberMember_no) {
		return getSqlSession().selectOne("consult_board_detail.getNameByMemberNo", numberMember_no);
	}
	
	
	
	
	
	
	
	
	

	

}
