package com.khacademy.khoffice.work_history.services;

import java.util.List;
import java.util.Map;

import com.khacademy.khoffice.work_history.models.AdminWorkHistoryDTO;
import com.khacademy.khoffice.work_history.models.WorkHistoryDTO;

public interface WorkHistoryService {

	int insertStartTimeByMemberNo(WorkHistoryDTO historyDTO);
	
	int insertEndTimeByMemberNo(WorkHistoryDTO historyDTO);
	
	List<WorkHistoryDTO> selectWorkHistoryByMemberNo(int member_no);
	
	int updateMemoByNo(WorkHistoryDTO historyDTO);
	
	WorkHistoryDTO todayHasStartWork(int member_no);
	
	List<AdminWorkHistoryDTO> allSelectWorkHistoryList(Map<String,Object> map);
	
	WorkHistoryDTO selectWorkHistoryByHistoryNo(String work_history_no);
	
	int updateStartTimeByAdmin(WorkHistoryDTO historyDTO);
	
	int updateEndTimeByAdmin(WorkHistoryDTO historyDTO);
	
	int insertAdminStartTimeByMemberNo(WorkHistoryDTO historyDTO);
	
	int insertAdminEndTimeByMemberNo(WorkHistoryDTO historyDTO);
	
	int deleteAdminByWorkHistoryNo(String work_history_no);
}
