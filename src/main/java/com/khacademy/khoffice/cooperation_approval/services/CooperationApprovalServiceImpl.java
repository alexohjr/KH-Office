package com.khacademy.khoffice.cooperation_approval.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import com.khacademy.khoffice.approval.daos.CooperationApprovalBoardListDAO;
import com.khacademy.khoffice.approval.daos.CooperationReportBoardListDAO;
import com.khacademy.khoffice.approval.models.CooperationApprovalBoardListDTO;
import com.khacademy.khoffice.approval.models.CooperationApprovalUpdateStatusDTO;
import com.khacademy.khoffice.approval.models.CooperationReportAddFormDTO;

@Component
public class CooperationApprovalServiceImpl implements CooperationApprovalService{

	CooperationReportBoardListDAO cooBoardDAO;

	@Autowired
	@Required
	public void setCooBoardDAO(CooperationReportBoardListDAO cooBoardDAO) {
		this.cooBoardDAO = cooBoardDAO;
	}
	
	CooperationApprovalBoardListDAO cooApprovalBoardDAO;

	@Autowired
	@Required
	public void setCooApprovalBoardDAO(CooperationApprovalBoardListDAO cooApprovalBoardDAO) {
		this.cooApprovalBoardDAO = cooApprovalBoardDAO;
	}

	@Override
	public int twoStepCooperationInsertApproval(CooperationReportAddFormDTO getInfo) {
		int insertApprovalSuccess = cooBoardDAO.twoStepCooperationInsertApproval(getInfo);
		return insertApprovalSuccess;
	}

	@Override
	public List<Integer> getMyCooreport_noList(int member_no) {
		List<Integer> getMyCooreport_noList = cooApprovalBoardDAO.getMyCooreport_noList(member_no);
		return getMyCooreport_noList;
	}

	@Override
	public List<CooperationApprovalBoardListDTO> getMyApprovalRowsByNo(int cooreport_no) {
		List<CooperationApprovalBoardListDTO> getMyApprovalRowsByNo = cooApprovalBoardDAO.getMyApprovalRowsByNo(cooreport_no);
		return getMyApprovalRowsByNo;
	}

	@Override
	public int updateCooApprovalStatus(CooperationApprovalUpdateStatusDTO updateStatus) {
		int updateCooApprovalStatus = cooApprovalBoardDAO.updateCooApprovalStatus(updateStatus);
		return updateCooApprovalStatus;
	}

	@Override
	public int updateLastApprovalStatus(CooperationApprovalUpdateStatusDTO updateStatus) {
		int updateLastApprovalStatus = cooApprovalBoardDAO.updateLastApprovalStatus(updateStatus);
		return updateLastApprovalStatus;
	}	
	
	
	
	
}
