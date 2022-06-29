package com.khacademy.khoffice.member.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.khacademy.khoffice.approval.models.VacationApprovalUpdateStatusDTO;
import com.khacademy.khoffice.member.models.BookmarkDTO;
import com.khacademy.khoffice.member.models.MemberDTO;
import com.khacademy.khoffice.member.models.MemberDepartmentDTO;
import com.khacademy.khoffice.member.models.MemberDetailDTO;
import com.khacademy.khoffice.member.models.PasswordDTO;
import com.khacademy.khoffice.work_history.models.AdminWorkHistoryDTO;

public interface MemberService {
	boolean authenticate(MemberDTO memberDTO,HttpServletRequest request);
	public List<MemberDepartmentDTO> getMemberList(Object object);
	public List<BookmarkDTO> getBookmarkList(int memberNo);
	public int memberCount(Object object);
	public boolean insertBookmark(Object object);
	public boolean deleteBookmark(Object object);
	public int emailCheck(String email);
	public int memberRegister(MemberDTO memberDTO);
	public List<MemberDetailDTO> getMemberDetail(int memberNo);
	public int changePassword(PasswordDTO passwordDTO);
	public String getMemberThumbPath(int memberNo);
	public int updateMember(MemberDTO memberDTO);
	public int deleteMember(int memberNo);
	public List<MemberDTO> getMemberListByDepartmentNo(int departmentNo);
	
	public MemberDTO getPositionNameByMemberNo(int memberNo);
	
	public int selectTotalCount(String department_no);
	public List<AdminWorkHistoryDTO> getHistoryMemberListByDepartmentNo(int department_no);
	
	public int decreaseRemainderLeave(VacationApprovalUpdateStatusDTO status);
	public String getNameByNo(String substitute);
	public List<MemberDTO> getAllMemberList();
	public int getRemainderLeaveCount(int member_no);
	
}
