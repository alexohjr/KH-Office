package com.khacademy.khoffice.approval.daos;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.khacademy.khoffice.approval.models.VacationApprovalBoardListDTO;
import com.khacademy.khoffice.approval.models.VacationApprovalUpdateStatusDTO;

public class VacationApprovalBoardListDAO extends SqlSessionDaoSupport{

	public List<Integer> getMyVacreport_noList(int member_no){
		return getSqlSession().selectList("vacation_approval_board.getMyVacreport_noList",member_no);
	}
	
	public List<VacationApprovalBoardListDTO> getMyApprovalRowsByNo(int vacreport_no){
		return getSqlSession().selectList("vacation_approval_board.getMyApprovalRowsByNo", vacreport_no);
	}
	
	public int updateVacApprovalStatus(VacationApprovalUpdateStatusDTO status) {
		return getSqlSession().update("vacation_approval_board.updateVacApprovalStatus", status);
	}
	
	public int updateLastApprovalStatus(VacationApprovalUpdateStatusDTO status) {
		return getSqlSession().update("vacation_approval_board.updateLastApprovalStatus", status);
	}
}
