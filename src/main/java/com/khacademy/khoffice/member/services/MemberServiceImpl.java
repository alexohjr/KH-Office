package com.khacademy.khoffice.member.services;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.khacademy.khoffice.approval.models.VacationApprovalUpdateStatusDTO;
import com.khacademy.khoffice.member.daos.MemberDAO;
import com.khacademy.khoffice.member.models.BookmarkDTO;
import com.khacademy.khoffice.member.models.MemberDTO;
import com.khacademy.khoffice.member.models.MemberDepartmentDTO;
import com.khacademy.khoffice.member.models.MemberDetailDTO;
import com.khacademy.khoffice.member.models.PasswordDTO;
import com.khacademy.khoffice.work_history.models.AdminWorkHistoryDTO;

@Service
public class MemberServiceImpl implements MemberService {
	
	private MemberDAO memberDAO;
	
	@Autowired
	@Required
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	//로그인
	@Override
	public boolean authenticate(MemberDTO memberDTO,HttpServletRequest request) {
		boolean result = false;
		
		try {
			Integer member_no = null;
			member_no = memberDAO.doLogin("member.selectByEmailPassword", memberDTO); //로그인 여부 확인용 & 세션에 member_no 저장용
			
			if(member_no != null) { //0이 아니면 member_no값이 있으므로
				request.getSession().invalidate();
				request.getSession().setAttribute("session_memberNo", member_no); //세션에 member_no 저장
				System.out.println("[MemberServiceImpl] member계정 로그인 완료. 세션 : " + request.getSession().getAttribute("session_memberNo"));
				result = true; //result에 true 저장 
			}else if(member_no == null){ //member_no가 0이면 
				result = false;
			}
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}

	//멤버리스트
	@Override
	public List<MemberDepartmentDTO> getMemberList(Object object) {
		List<MemberDepartmentDTO> memberDeptDTO = null;
		try {
			memberDeptDTO = memberDAO.getList("member.selectMemberList", object);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return memberDeptDTO;
	}
	
	//즐겨찾기 리스트 가져오기 
	@Override 
	public List<BookmarkDTO> getBookmarkList(int memberNo){
		List<BookmarkDTO> bookmark = memberDAO.getBookmarkByMemberNo("member.selectBookmarkList",memberNo);
		return bookmark;
		
	}
	
	//페이징용 멤버 카운트
	@Override
	public int memberCount(Object object) {
		int x = 0;
		try {
			x = memberDAO.getMemberCount("member.selectMemberCount", object);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		return x;
	}

	//즐겨찾기 추가
	@Override
	public boolean insertBookmark(Object object) {
		boolean result = false;
		int insertedRow = 0;
		insertedRow = memberDAO.insertBookmark("member.insertBookmark",object);
		if(insertedRow != 0) {
			result = true;
		}
		return result;
	}
	
	//즐겨찾기 삭제 
	public boolean deleteBookmark(Object object) {
		boolean result = false;
		int deletedRow = 0;
		deletedRow = memberDAO.deleteBookmark("member.deleteBookmark",object);
		if(deletedRow != 0) {
			result = true;
		}
		return result;
	}
	
	//이메일 중복체크
	@Override
	public int emailCheck(String email) {
		int x = 0;
		try {
			x = memberDAO.getEmailCount("member.selectMemberEmail", email);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		return x;
	}
	
	//사원등록
	@Override 
	public int memberRegister(MemberDTO memberDTO) {
		int x = 0;
		try {
			x = memberDAO.insertMember("member.insertMember",memberDTO);
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		return x;
	}
	
	//사원 상세페이지
	@Override
	public List<MemberDetailDTO> getMemberDetail(int memberNo){
		List<MemberDetailDTO> memberDetail = memberDAO.getMemberDetailByMemberNo("member.selectMemberByMemberNo",memberNo);
		return memberDetail;
	}

	//비밀번호 변경
	@Override
	public int changePassword(PasswordDTO passwordDTO) {
		int x = 0;
		try {
			x = memberDAO.updatePassword("member.updateMemberPassword",passwordDTO);
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		return x;
	}
	//사원정보 수정을 위한 thumb_path 조회
	@Override
	public String getMemberThumbPath(int memberNo) {
		String thumb_path = null;
		try {
			thumb_path = memberDAO.getMemberThumbPath("member.selectMemberThumb",memberNo);
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		return thumb_path;
	}
	
	//사원정보수정하기
	@Override
	public int updateMember(MemberDTO memberDTO) {
		int x = 0;
		try {
			x = memberDAO.updateMember("member.updateMemberInfo",memberDTO);
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		return x;
	}
	
	//사원 삭제하기
	@Override
	public int deleteMember(int memberNo) {
		int x = 0;
		try {
			x = memberDAO.deleteMember("member.deleteMember",memberNo);
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		return x;
		
	}
	//부서 카테고리
	@Override
	public List<MemberDTO> getMemberListByDepartmentNo(int departmentNo) {
		// 부서번호를 가지고 멤버 조회
		List<MemberDTO> memberDTOList =  memberDAO.getMemberListByDepartmentNo("member.selectMemberByDepartmentNo", departmentNo);
		return memberDTOList;
	}
	
	
	

	@Override
	public MemberDTO getPositionNameByMemberNo(int memberNo) {
		// 사번을 가지고 직급, 이름 조회
		return memberDAO.getPositionNameByMemberNo("member.selectPositionNameByMemberNo", memberNo);
	}

	
	@Override
	public int decreaseRemainderLeave(VacationApprovalUpdateStatusDTO status) {
		int decreaseRemainderLeave = memberDAO.decreaseRemainderLeave("member.decreaseRemainderLeave", status);
		return decreaseRemainderLeave;
	}
	
	@Override
	public String getNameByNo(String substitute) {
		String getNameByNo = memberDAO.getNameByNo("member.getNameByNo",substitute);
		return getNameByNo;
	}
	
	@Override
	public List<MemberDTO> getAllMemberList() {
		List<MemberDTO> getAllMemberList = memberDAO.getAllMemberList("member.getAllMemberList");
		return getAllMemberList;
	}
	
	@Override
	public int getRemainderLeaveCount(int member_no) {
		int getRemainderLeaveCount = memberDAO.getRemainderLeaveCount("member.getRemainderLeaveCount", member_no);
		return getRemainderLeaveCount;
	}

	@Override
	public int selectTotalCount(String department_no) {
		int selectTotalCount = memberDAO.selectTotalCount("member.selectTotalCount", department_no);
		return selectTotalCount;
	}

	@Override
	public List<AdminWorkHistoryDTO> getHistoryMemberListByDepartmentNo(int department_no) {
		List<AdminWorkHistoryDTO> getHistoryMemberListByDepartmentNo 
		= memberDAO.getHistoryMemberListByDepartmentNo("member.getHistoryMemberListByDepartmentNo", department_no);
		return getHistoryMemberListByDepartmentNo;
	}
	
}
