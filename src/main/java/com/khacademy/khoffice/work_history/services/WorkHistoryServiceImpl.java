package com.khacademy.khoffice.work_history.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import com.khacademy.khoffice.work_history.daos.WorkHistoryDAO;
import com.khacademy.khoffice.work_history.models.AdminWorkHistoryDTO;
import com.khacademy.khoffice.work_history.models.WorkHistoryDTO;

@Component
public class WorkHistoryServiceImpl implements WorkHistoryService {

	public WorkHistoryDAO workHistoryDAO;

	@Autowired
	@Required
	public void setWorkHistoryDAO(WorkHistoryDAO workHistoryDAO) {
		this.workHistoryDAO = workHistoryDAO;
	}

	@Override
	public int insertStartTimeByMemberNo(WorkHistoryDTO historyDTO) {
		int insertStartTimeByMemberNo = workHistoryDAO.insertStartTimeByMemberNo(historyDTO);
		return insertStartTimeByMemberNo;
	}

	@Override
	public int insertEndTimeByMemberNo(WorkHistoryDTO historyDTO) {
		int insertEndTimeByMemberNo = workHistoryDAO.insertEndTimeByMemberNo(historyDTO);
		return insertEndTimeByMemberNo;
	}

	@Override
	public List<WorkHistoryDTO> selectWorkHistoryByMemberNo(int member_no) {
		List<WorkHistoryDTO> historyDTO = workHistoryDAO.selectWorkHistoryByMemberNo(member_no);
		return historyDTO;
	}

	@Override
	public int updateMemoByNo(WorkHistoryDTO historyDTO) {
		int updateMemoByNo = workHistoryDAO.updateMemoByNo(historyDTO);
		return updateMemoByNo;
	}

	@Override
	public WorkHistoryDTO todayHasStartWork(int member_no) {
		WorkHistoryDTO todayHasStartWork = workHistoryDAO.todayHasStartWork(member_no);
		return todayHasStartWork;
	}

	@Override
	public List<AdminWorkHistoryDTO> allSelectWorkHistoryList(Map<String, Object> map) {
		List<AdminWorkHistoryDTO> allSelectWorkHistoryList = workHistoryDAO.allSelectWorkHistoryList(map);
		return allSelectWorkHistoryList;
	}

	@Override
	public WorkHistoryDTO selectWorkHistoryByHistoryNo(String work_history_no) {
		WorkHistoryDTO selectWorkHistoryByHistoryNo = workHistoryDAO.selectWorkHistoryByHistoryNo(work_history_no);
		return selectWorkHistoryByHistoryNo;
	}

	@Override
	public int updateStartTimeByAdmin(WorkHistoryDTO historyDTO) {
		int updateStartTimeByAdmin = workHistoryDAO.updateStartTimeByAdmin(historyDTO);
		return updateStartTimeByAdmin;
	}

	@Override
	public int updateEndTimeByAdmin(WorkHistoryDTO historyDTO) {
		int updateEndTimeByAdmin = workHistoryDAO.updateEndTimeByAdmin(historyDTO);
		return updateEndTimeByAdmin;
	}

	@Override
	public int insertAdminStartTimeByMemberNo(WorkHistoryDTO historyDTO) {
		int insertAdminStartTimeByMemberNo = workHistoryDAO.insertAdminStartTimeByMemberNo(historyDTO);
		return insertAdminStartTimeByMemberNo;
	}

	@Override
	public int insertAdminEndTimeByMemberNo(WorkHistoryDTO historyDTO) {
		int insertAdminEndTimeByMemberNo = workHistoryDAO.insertAdminEndTimeByMemberNo(historyDTO);
		return insertAdminEndTimeByMemberNo;
	}

	@Override
	public int deleteAdminByWorkHistoryNo(String work_history_no) {
		int deleteAdminByWorkHistoryNo = workHistoryDAO.deleteAdminByWorkHistoryNo(work_history_no);
		return deleteAdminByWorkHistoryNo;
	}

	
	
}
