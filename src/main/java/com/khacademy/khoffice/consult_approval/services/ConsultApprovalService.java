package com.khacademy.khoffice.consult_approval.services;

import java.util.List;

import com.khacademy.khoffice.approval.models.ConsultApprovalBoardListDTO;
import com.khacademy.khoffice.approval.models.ConsultApprovalUpdateStatusDTO;
import com.khacademy.khoffice.approval.models.ConsultReportAddFormDTO;

public interface ConsultApprovalService {

	int twoStepConsultInsertApproval(ConsultReportAddFormDTO getInfo);
	
	List<Integer> getMyConreport_no(int member_no);
	
	List<ConsultApprovalBoardListDTO> getMyApprovalRowsByNo(int conreport_no);
	
	//status값이 1로 넘어왔을때 승인하기위한 update
	int updateConApprovalStatus(ConsultApprovalUpdateStatusDTO updateStatus);

	//status값이 2로 넘어왔을때 전체 2로 update
	int updateConApprovalStatusOpposition(ConsultApprovalUpdateStatusDTO updateStatus);

	int updateLastApprovalStatus(ConsultApprovalUpdateStatusDTO updateStatus);
}
