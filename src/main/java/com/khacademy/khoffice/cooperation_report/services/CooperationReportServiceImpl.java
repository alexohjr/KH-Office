package com.khacademy.khoffice.cooperation_report.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import com.khacademy.khoffice.approval.daos.CooperationReportBoardListDAO;
import com.khacademy.khoffice.approval.models.CooperationReportAddFormDTO;
import com.khacademy.khoffice.approval.models.CooperationReportBoardListDTO;

@Component
public class CooperationReportServiceImpl implements CooperationReportService {

	public CooperationReportBoardListDAO cooBoardDAO;
	
	@Autowired
	@Required
	public void setCooBoardDAO(CooperationReportBoardListDAO cooBoardDAO) {
		this.cooBoardDAO = cooBoardDAO;
	}
	
	@Override
	public int oneStepCooperationInsertReport(CooperationReportAddFormDTO getInfo) {
		int insertSuccess = cooBoardDAO.oneStepCooperationInsertReport(getInfo);
		return insertSuccess;
	}



	@Override
	public int cooperationBoardCount(int member_no) {
		int count = cooBoardDAO.cooperationBoardCount(member_no);
		return count;
	}
	
	@Override
	public List<CooperationReportBoardListDTO> list(Map<String,Object> map) {
		List<CooperationReportBoardListDTO> list = cooBoardDAO.cooperationBoardList(map);
		return list;
	}


	@Override
	public int selectLastCooreportNo() {
		int selectLastCooreportNo = cooBoardDAO.selectLastCooreportNo();
		return selectLastCooreportNo;
	}

	@Override
	public CooperationReportAddFormDTO cooperationGetInfo(int member_no) {
		CooperationReportAddFormDTO getInfo = cooBoardDAO.cooperationAddGetForm(member_no);
		return getInfo;
	}

	@Override
	public List<CooperationReportAddFormDTO> selectCooperationBoardDetail(Map<String, Object> map) {
		List<CooperationReportAddFormDTO> getDetail = cooBoardDAO.selectCooperationBoardDetail(map);
		return getDetail;
	}

	@Override
	public String getNameByMemberNo(int numberMember_no) {
		String member_no = cooBoardDAO.getNameByMemberNo(numberMember_no);
		return member_no;
	}

	@Override
	public String getNameByDepartmentNo(int numberDepartment_no) {
		String department_no = cooBoardDAO.getNameByDepartmentNo(numberDepartment_no);
		return department_no;
	}
	
	
	
}
