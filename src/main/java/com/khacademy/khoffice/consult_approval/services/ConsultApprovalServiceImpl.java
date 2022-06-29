package com.khacademy.khoffice.consult_approval.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import com.khacademy.khoffice.approval.daos.ConsultApprovalBoardListDAO;
import com.khacademy.khoffice.approval.daos.ConsultReportBoardListDAO;
import com.khacademy.khoffice.approval.models.ConsultApprovalBoardListDTO;
import com.khacademy.khoffice.approval.models.ConsultApprovalUpdateStatusDTO;
import com.khacademy.khoffice.approval.models.ConsultReportAddFormDTO;

@Component
public class ConsultApprovalServiceImpl implements ConsultApprovalService{

	ConsultReportBoardListDAO conBoardDAO;
	
	@Autowired
	@Required
	public void setConBoardDAO(ConsultReportBoardListDAO conBoardDAO) {
		this.conBoardDAO = conBoardDAO;
	}
	
	ConsultApprovalBoardListDAO conApprovalBoardDAO;
	
	
	@Autowired
	@Required
	public void setConApprovalBoardDAO(ConsultApprovalBoardListDAO conApprovalBoardDAO) {
		this.conApprovalBoardDAO = conApprovalBoardDAO;
	}

	@Override
	public int twoStepConsultInsertApproval(ConsultReportAddFormDTO getInfo) {

		int insertApprovalSuccess = conBoardDAO.twoStepConsultInsertApproval(getInfo);
		return insertApprovalSuccess;
	}

	@Override
	public List<Integer> getMyConreport_no(int member_no) {
		List<Integer> getMyConreport_noList = conApprovalBoardDAO.getMyConreport_noList(member_no);
		return getMyConreport_noList;
	}

	@Override
	public List<ConsultApprovalBoardListDTO> getMyApprovalRowsByNo(int conreport_no) {
		List<ConsultApprovalBoardListDTO> getMyApprovalRowsByNo = conApprovalBoardDAO.getMyApprovalRowsByNo(conreport_no);
		return getMyApprovalRowsByNo;
	}

	//status값이 1로 넘어왔을때 승인하기위한 update
	@Override
	public int updateConApprovalStatus(ConsultApprovalUpdateStatusDTO updateStatus) {
		int updateConApprovalStatus = conApprovalBoardDAO.updateConApprovalStatus(updateStatus);
		return updateConApprovalStatus;
	}

	//status값이 2로 넘어왔을때 전체 2로 update
	@Override
	public int updateConApprovalStatusOpposition(ConsultApprovalUpdateStatusDTO updateStatus) {
		int updateConApprovalStatus = conApprovalBoardDAO.updateConApprovalStatusOpposition(updateStatus);
		return updateConApprovalStatus;
	}

	@Override
	public int updateLastApprovalStatus(ConsultApprovalUpdateStatusDTO updateStatus) {
		int updateLastApprovalStatus = conApprovalBoardDAO.updateLastApprovalStatus(updateStatus);
		System.out.println("ServiceImpl last쪽");
		return updateLastApprovalStatus;
	}
	

	
	
}
