package com.khacademy.khoffice.work_history.daos;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.khacademy.khoffice.work_history.models.AdminWorkHistoryDTO;
import com.khacademy.khoffice.work_history.models.WorkHistoryDTO;

public class WorkHistoryDAO extends SqlSessionDaoSupport{

	public int insertStartTimeByMemberNo(WorkHistoryDTO historyDTO) {
		return getSqlSession().insert("work_history.insertStartTimeByMemberNo", historyDTO);
	}
	
	public int insertEndTimeByMemberNo(WorkHistoryDTO historyDTO) {
		return getSqlSession().update("work_history.insertEndTimeByMemberNo", historyDTO);
	}
	
	public List<WorkHistoryDTO> selectWorkHistoryByMemberNo(int member_no) {
		return getSqlSession().selectList("work_history.selectWorkHistoryByMemberNo",member_no);
	}
	
	public int updateMemoByNo(WorkHistoryDTO historyDTO) {
		return getSqlSession().update("work_history.updateMemoByNo", historyDTO);
	}
	
	public WorkHistoryDTO todayHasStartWork(int member_no) {
		return getSqlSession().selectOne("work_history.todayHasStartWork", member_no);
	}
	
	public List<AdminWorkHistoryDTO> allSelectWorkHistoryList(Map<String,Object> map) {
		return getSqlSession().selectList("work_history.allSelectWorkHistoryList", map);
	}
	
	public WorkHistoryDTO selectWorkHistoryByHistoryNo(String work_history_no) {
		return getSqlSession().selectOne("work_history.selectWorkHistoryByHistoryNo", work_history_no);
	}
	
	public int updateStartTimeByAdmin(WorkHistoryDTO historyDTO) {
		return getSqlSession().update("work_history.updateStartTimeByAdmin" , historyDTO);
	}
	
	public int updateEndTimeByAdmin(WorkHistoryDTO historyDTO) {
		return getSqlSession().update("work_history.updateEndTimeByAdmin" , historyDTO);
	}
	
	public int insertAdminStartTimeByMemberNo(WorkHistoryDTO historyDTO) {
		return getSqlSession().insert("work_history.insertAdminStartTimeByMemberNo", historyDTO);
	}
	
	public int insertAdminEndTimeByMemberNo(WorkHistoryDTO historyDTO) {
		return getSqlSession().insert("work_history.insertAdminEndTimeByMemberNo", historyDTO);
	}
	
	public int deleteAdminByWorkHistoryNo(String work_history_no) {
		return getSqlSession().delete("work_history.deleteAdminByWorkHistoryNo", work_history_no);
	}
}
