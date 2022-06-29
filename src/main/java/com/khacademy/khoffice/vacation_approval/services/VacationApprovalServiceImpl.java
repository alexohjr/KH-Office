package com.khacademy.khoffice.vacation_approval.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import com.khacademy.khoffice.approval.daos.VacationApprovalBoardListDAO;
import com.khacademy.khoffice.approval.daos.VacationReportBoardListDAO;
import com.khacademy.khoffice.approval.models.VacationApprovalBoardListDTO;
import com.khacademy.khoffice.approval.models.VacationApprovalUpdateStatusDTO;
import com.khacademy.khoffice.approval.models.VacationReportAddFormDTO;

@Component
public class VacationApprovalServiceImpl implements VacationApprovalService{

	VacationReportBoardListDAO vacBoardDAO;
	
	@Autowired
	@Required
	public void setVacBoardDAO(VacationReportBoardListDAO vacBoardDAO) {
		this.vacBoardDAO = vacBoardDAO;
	}
	
	VacationApprovalBoardListDAO vacApprovalBoardDAO;
	
	@Autowired
	@Required
	public void setVacApprovalBoardDAO(VacationApprovalBoardListDAO vacApprovalBoardDAO) {
		this.vacApprovalBoardDAO = vacApprovalBoardDAO;
	}

	@Override
	public int twoStepVacationInsertApproval(VacationReportAddFormDTO getInfo) {
		int insertApprovalSuccess = vacBoardDAO.twoStepVacationInsertApproval(getInfo);
		return insertApprovalSuccess;
	}

	@Override
	public List<Integer> getMyVacreport_no(int member_no) {
		List<Integer> getMyVacreport_noList = vacApprovalBoardDAO.getMyVacreport_noList(member_no);
		return getMyVacreport_noList;
	}

	@Override
	public List<VacationApprovalBoardListDTO> getMyApprovalRowsByNo(int vacreport_no) {
		List<VacationApprovalBoardListDTO> getMyApprovalRowsByNo = vacApprovalBoardDAO.getMyApprovalRowsByNo(vacreport_no);
		return getMyApprovalRowsByNo;
	}

	@Override
	public int updateVacApprovalStatus(VacationApprovalUpdateStatusDTO updateStatus) {
		int updateConApprovalStatus = vacApprovalBoardDAO.updateVacApprovalStatus(updateStatus);
		return updateConApprovalStatus;
	}

	@Override
	public int updateLastApprovalStatus(VacationApprovalUpdateStatusDTO updateStatus) {
		int updateLastApprovalStatus = vacApprovalBoardDAO.updateLastApprovalStatus(updateStatus);
		return updateLastApprovalStatus;
	}
	
	

	
}
