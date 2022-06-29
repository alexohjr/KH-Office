package com.khacademy.khoffice.vacation_approval.services;

import java.util.List;

import com.khacademy.khoffice.approval.models.VacationApprovalBoardListDTO;
import com.khacademy.khoffice.approval.models.VacationApprovalUpdateStatusDTO;
import com.khacademy.khoffice.approval.models.VacationReportAddFormDTO;

public interface VacationApprovalService {
	
	// vacation_report insert후 바로 vacation_approval 테이블에 insert
	int twoStepVacationInsertApproval(VacationReportAddFormDTO getInfo);

	// 내가 승인해야할 vacreport_no list 가져오기
	List<Integer> getMyVacreport_no(int member_no);
	
	// vacreport_no를통해 내가 승인해야할 row들을 가져옴.
	List<VacationApprovalBoardListDTO> getMyApprovalRowsByNo(int vacreport_no);
	
	// 승인/반려를 클릭한 status값을 담은 DTO객체를들고 update
	int updateVacApprovalStatus(VacationApprovalUpdateStatusDTO updateStatus);
	
	// 최종결재자이면서, 승인을 누를때 status=3으로 update
	int updateLastApprovalStatus(VacationApprovalUpdateStatusDTO updateStatus);
}
