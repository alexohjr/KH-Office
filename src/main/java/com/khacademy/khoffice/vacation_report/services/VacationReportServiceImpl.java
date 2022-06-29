package com.khacademy.khoffice.vacation_report.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import com.khacademy.khoffice.approval.daos.VacationReportBoardListDAO;
import com.khacademy.khoffice.approval.models.VacationReportAddFormDTO;
import com.khacademy.khoffice.approval.models.VacationReportBoardListDTO;
import com.khacademy.khoffice.department.daos.DepartmentDAO;

@Component
public class VacationReportServiceImpl implements VacationReportService{

	public VacationReportBoardListDAO vacBoardDAO;

	@Autowired
	@Required
	public void setVacBoardDAO(VacationReportBoardListDAO vacBoardDAO) {
		this.vacBoardDAO = vacBoardDAO;
	}
	
	public DepartmentDAO departmentDAO;
	
	@Autowired
	@Required
	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}


	@Override
	public int oneStepVacationInsertReport(VacationReportAddFormDTO getInfo) {
		int insertSuccess = vacBoardDAO.oneStepVacationInsertReport(getInfo);
		return insertSuccess;
	}


	@Override
	public int vacationBoardCount(int member_no) {
		int count = vacBoardDAO.vacationBoardCount(member_no);
		return count;
	}

	@Override
	public VacationReportAddFormDTO vacationGetInfo(int member_no) {
		VacationReportAddFormDTO getInfo = vacBoardDAO.vacationAddGetForm(member_no);
		return getInfo;
	}

	@Override
	public List<VacationReportBoardListDTO> list(Map<String, Object> map) {
		List<VacationReportBoardListDTO> list = vacBoardDAO.vacationBoardList(map);
		return list;
	}
	
	
	@Override
	public int selectLastVacreportNo() {
		int selectLastVacreportNo = vacBoardDAO.selectLastVacreportNo();
		return selectLastVacreportNo;
	}


	@Override
	public List<VacationReportAddFormDTO> selectVacationBoardDetail(Map<String, Object> map) {
		List<VacationReportAddFormDTO> getDetail = vacBoardDAO.selectVacationBoardDetail(map);
		return getDetail;
	}


	@Override
	public String getNameByMemberNo(int numberMember_no) {
		String member_no = vacBoardDAO.getNameByMemberNo(numberMember_no);
		return member_no;
	}


}
