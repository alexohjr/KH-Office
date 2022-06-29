package com.khacademy.khoffice.vacation_report.services;

import java.util.List;
import java.util.Map;

import com.khacademy.khoffice.approval.models.VacationReportAddFormDTO;
import com.khacademy.khoffice.approval.models.VacationReportBoardListDTO;

public interface VacationReportService {
	
	// 결재작성된 VacationReport 갯수 count해서 가져옴.
	int selectLastVacreportNo();
	
	// 결재작성된 VacationReport 갯수 count해서 가져옴.
	int vacationBoardCount(int member_no);
	
	// 결재작성된 VacationReport 목록을 가져와 게시판 리스트 뿌리기
	List<VacationReportBoardListDTO> list(Map<String,Object> map);
	
	// addForm에서 필요한 정보들을 가져옴
	VacationReportAddFormDTO vacationGetInfo(int member_no);
	
	// addForm에서 결재작성(VacationReport) 추가.
	int oneStepVacationInsertReport(VacationReportAddFormDTO getInfo);
	// member_no값을 가지고 vacation_report Board 상세페이지 가져오기	
	List<VacationReportAddFormDTO> selectVacationBoardDetail(Map<String,Object> map);
	
	// DB에서 조회한 member_no값으로 승인해야할 사원의 name값을 가져옴 
	String getNameByMemberNo(int numberMember_no);
	
}
