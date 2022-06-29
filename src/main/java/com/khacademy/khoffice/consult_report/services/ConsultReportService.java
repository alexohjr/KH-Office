package com.khacademy.khoffice.consult_report.services;

import java.util.List;
import java.util.Map;

import com.khacademy.khoffice.approval.models.ConsultReportAddFormDTO;
import com.khacademy.khoffice.approval.models.ConsultReportBoardListDTO;

public interface ConsultReportService {
	
	// 결재작성된 ConsultReport 갯수 count해서 가져옴.
	int consultBoardCount(int member_no);

	// 결재작성된 ConsultReport 목록을 가져와 게시판 리스트 뿌리기
	List<ConsultReportBoardListDTO> list(Map<String,Object> map);
	
	// addForm에서 필요한 정보들을 가져옴
	ConsultReportAddFormDTO consultGetInfo(int member_no);

	// addForm에서 결재작성(ConsultReport) 추가 
	int oneStepConsultInsertReport(ConsultReportAddFormDTO getInfo);
	
	// 가장 최근에 등록된 ConsultReport의 conreport_no를 가져옴.
	int selectLastConreportNo();
	
	// member_no값을 가지고 consult_reportBoard 상세페이지 가져오기
	List<ConsultReportAddFormDTO> selectConsultBoardDetail(Map<String,Object> member_no);

	// DB에서 조회한 member_no값으로 승인해야할 사원의 name값을 가져옴	
	String getNameByMemberNo(int numberMember_no);

}
