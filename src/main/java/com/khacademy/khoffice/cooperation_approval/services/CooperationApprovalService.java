package com.khacademy.khoffice.cooperation_approval.services;

import java.util.List;

import com.khacademy.khoffice.approval.models.CooperationApprovalBoardListDTO;
import com.khacademy.khoffice.approval.models.CooperationApprovalUpdateStatusDTO;
import com.khacademy.khoffice.approval.models.CooperationReportAddFormDTO;

public interface CooperationApprovalService {
	
	int twoStepCooperationInsertApproval(CooperationReportAddFormDTO getInfo);
	
	List<Integer> getMyCooreport_noList(int member_no);

	List<CooperationApprovalBoardListDTO> getMyApprovalRowsByNo(int cooreport_no);
	
	
	//status값이 1로 넘어왔을때 승인하기위한 update
	int updateCooApprovalStatus(CooperationApprovalUpdateStatusDTO updateStatus);
	
	int updateLastApprovalStatus(CooperationApprovalUpdateStatusDTO updateStatus);
}
