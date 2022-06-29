package com.khacademy.khoffice.cooperation_report.services;

import java.util.List;
import java.util.Map;

import com.khacademy.khoffice.approval.models.CooperationReportAddFormDTO;
import com.khacademy.khoffice.approval.models.CooperationReportBoardListDTO;

public interface CooperationReportService {
	
	// 결재 작성된 CooperationReport 갯수 count해서 가져옴.
	int cooperationBoardCount(int member_no);
	
	// 결재작성된 CooperationReport 목록을 가져와 게시판 리스트 뿌리기.
	List<CooperationReportBoardListDTO> list(Map<String,Object> map);
	
	// addForm에서 필요한 정보들을 가져옴.
	CooperationReportAddFormDTO cooperationGetInfo(int member_no);
	
	// 가장 최근에 등록된 CooperationtReport의 cooreport_no를 가져옴.
	int selectLastCooreportNo();
	
	// addForm에서 결재작성(CooperationReport) 추가
	int oneStepCooperationInsertReport(CooperationReportAddFormDTO getInfo);
	
	// member_no값을 가지고 cooperation_reportBoard 상세페이지 가져오기
	List<CooperationReportAddFormDTO> selectCooperationBoardDetail(Map<String,Object> map);
	
	// DB에서 조회한 member_no값으로 승인해야할 사원의 name값을 가져옴
	String getNameByMemberNo(int numberMember_no);
	
	String getNameByDepartmentNo(int numberDepartment_no);
}
