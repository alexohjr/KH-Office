package com.khacademy.khoffice.consult_report.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import com.khacademy.khoffice.approval.daos.ConsultReportBoardListDAO;
import com.khacademy.khoffice.approval.models.ConsultReportAddFormDTO;
import com.khacademy.khoffice.approval.models.ConsultReportBoardListDTO;

@Component
public class ConsultReportServiceImpl implements ConsultReportService {

	public ConsultReportBoardListDAO conBoardDAO;

	@Autowired
	@Required
	public void setConBoardDAO(ConsultReportBoardListDAO conBoardDAO) {
		this.conBoardDAO = conBoardDAO;
	}

	@Override
	public int oneStepConsultInsertReport(ConsultReportAddFormDTO getInfo) {
		int insertSucess = conBoardDAO.oneStepConsultInsertReport(getInfo);
		return insertSucess;
	}

	@Override
	public int consultBoardCount(int member_no) {

		int count = conBoardDAO.consultBoardCount(member_no);
		return count;
	}

	@Override
	public List<ConsultReportBoardListDTO> list(Map<String, Object> map) {
		List<ConsultReportBoardListDTO> list = conBoardDAO.consultBoardList(map);
		return list;
	}

	@Override
	public ConsultReportAddFormDTO consultGetInfo(int member_no) {
		ConsultReportAddFormDTO getInfo = conBoardDAO.consultAddGetForm(member_no);
		return getInfo;
	}

	@Override
	public int selectLastConreportNo() {
		int selectLastConreportNo = conBoardDAO.selectLastConreportNo();
		return selectLastConreportNo;
	}

	@Override
	public List<ConsultReportAddFormDTO> selectConsultBoardDetail(Map<String, Object> map) {
		List<ConsultReportAddFormDTO> getDetail = conBoardDAO.selectConsultBoardDetail(map);
		return getDetail;
	}

	@Override
	public String getNameByMemberNo(int numberMember_no) {
		String member_no = conBoardDAO.getNameByMemberNo(numberMember_no);
		return member_no;
	}
	
	

}
