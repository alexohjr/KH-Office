package com.khacademy.khoffice.approval.daos;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.khacademy.khoffice.approval.models.ConsultApprovalBoardListDTO;
import com.khacademy.khoffice.approval.models.ConsultApprovalUpdateStatusDTO;

public class ConsultApprovalBoardListDAO extends SqlSessionDaoSupport{

	public List<Integer> getMyConreport_noList(int member_no) {
		return getSqlSession().selectList("consult_approval_board.getMyConreport_noList", member_no);
	}
	
	public List<ConsultApprovalBoardListDTO> getMyApprovalRowsByNo(int conreport_no){
		return getSqlSession().selectList("consult_approval_board.getMyApprovalRowsByNo", conreport_no);
	}
	
	//status값이 1로 넘어왔을때 승인하기위한 update
	public int updateConApprovalStatus(ConsultApprovalUpdateStatusDTO status) {
		return getSqlSession().update("consult_approval_board.updateConApprovalStatus", status);
	}
	
	//status값이 2로 넘어왔을때 전체 2로 update
	public int updateConApprovalStatusOpposition(ConsultApprovalUpdateStatusDTO status) {
		return getSqlSession().update("consult_approval_board.updateConApprovalStatusOpposition", status);
	}
	
	public int updateLastApprovalStatus(ConsultApprovalUpdateStatusDTO status) {
		System.out.println("ApprovalBoard쪽");
		return getSqlSession().update("consult_approval_board.updateLastApprovalStatus", status);
	}
}
