package com.khacademy.khoffice.approval.daos;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.khacademy.khoffice.approval.models.CooperationApprovalBoardListDTO;
import com.khacademy.khoffice.approval.models.CooperationApprovalUpdateStatusDTO;

public class CooperationApprovalBoardListDAO extends SqlSessionDaoSupport{
	public List<Integer> getMyCooreport_noList(int member_no){
		return getSqlSession().selectList("cooperation_approval_board.getMyCooreport_noList", member_no);
	}
	
	public List<CooperationApprovalBoardListDTO> getMyApprovalRowsByNo(int cooreport_no){
		return getSqlSession().selectList("cooperation_approval_board.getMyApprovalRowsByNo", cooreport_no);
	}
	
	public int updateCooApprovalStatus(CooperationApprovalUpdateStatusDTO status) {
		return getSqlSession().update("cooperation_approval_board.updateCooApprovalStatus",status);
	}
	
	public int updateLastApprovalStatus(CooperationApprovalUpdateStatusDTO status) {
		return getSqlSession().update("cooperation_approval_board.updateLastApprovalStatus", status);
	}
}
