package com.khacademy.khoffice.member.daos;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.khacademy.khoffice.approval.models.VacationApprovalUpdateStatusDTO;
import com.khacademy.khoffice.member.models.BookmarkDTO;
import com.khacademy.khoffice.member.models.MemberDTO;
import com.khacademy.khoffice.member.models.MemberDepartmentDTO;
import com.khacademy.khoffice.member.models.MemberDetailDTO;
import com.khacademy.khoffice.member.models.PasswordDTO;
import com.khacademy.khoffice.work_history.models.AdminWorkHistoryDTO;

public class MemberDAO extends SqlSessionDaoSupport {
	// 로그인
	public Integer doLogin(String string, MemberDTO memberDTO) {
		// mapper 사용 로직 작성
		Integer member_no = getSqlSession().selectOne(string, memberDTO); // member_no 반환
		return member_no;
	}

	// 사원주소록 리스트
	public List<MemberDepartmentDTO> getList(String string, Object object) {
		List<MemberDepartmentDTO> memberDeptDTO = getSqlSession().selectList(string, object);
		return memberDeptDTO;
	}

	// 리스트에 즐겨찾기 여부 가져오기
	public List<BookmarkDTO> getBookmarkByMemberNo(String string, int memberNo) {
		List<BookmarkDTO> bookmarkDTO = getSqlSession().selectList(string, memberNo);
		return bookmarkDTO;
	}

	// 리스트 페이징용 사원 수 카운트
	public int getMemberCount(String string, Object object) {
		return getSqlSession().selectOne(string, object);

	}

	// 즐겨찾기 추가
	public int insertBookmark(String string, Object object) {
		return getSqlSession().insert(string, object);
	}

	// 즐겨찾기 삭제
	public int deleteBookmark(String string, Object object) {
		return getSqlSession().delete(string, object);
	}

	// 이메일 중복검사
	public int getEmailCount(String string, String email) {
		return getSqlSession().selectOne(string, email);
	}

	// 사원등록하기
	public int insertMember(String string, MemberDTO memberDTO) {
		return getSqlSession().insert(string, memberDTO);
	}

	// 사원 상세페이지 겸 내정보페이지
	public List<MemberDetailDTO> getMemberDetailByMemberNo(String string, int memberNo) {
		List<MemberDetailDTO> memberDetailDTO = getSqlSession().selectList(string, memberNo);
		return memberDetailDTO;
	}

	// 사원 - 내정보의 비밀번호 수정하기
	public int updatePassword(String string, PasswordDTO passwordDTO) {
		return getSqlSession().update(string, passwordDTO);
	}

	// 사원정보 수정을 위해 thumb_path 조회
	public String getMemberThumbPath(String string, int memberNo) {
		return getSqlSession().selectOne(string, memberNo);
	}

	// 어드민 - 사원정보 수정하기
	public int updateMember(String string, MemberDTO memberDTO) {
		return getSqlSession().update(string, memberDTO);
	}

	// 어드민 - 사원 삭제하기
	public int deleteMember(String string, int memberNo) {
		return getSqlSession().delete(string, memberNo);
	}

	/**
	 * @param string 호출할 매퍼명, departmentNo 부서번호
	 * @return departmentNo 에 해당하는 사원 리스트
	 * @author CHAE SEJONG
	 */
	public List<MemberDTO> getMemberListByDepartmentNo(String string, int departmentNo) {
		return getSqlSession().selectList(string, departmentNo);
	}

	public MemberDTO getPositionNameByMemberNo(String string, int memberNo) {
		return getSqlSession().selectOne(string, memberNo);
	}

	public int decreaseRemainderLeave(String string, VacationApprovalUpdateStatusDTO status) {
		return getSqlSession().update(string, status);
	}

	public String getNameByNo(String string, String substitute) {
		return getSqlSession().selectOne(string, substitute);
	}

	// 모든 사원들의 name과 member_no를 가져옴
	public List<MemberDTO> getAllMemberList(String string) {
		return getSqlSession().selectList(string);
	}

	public int getRemainderLeaveCount(String string, int member_no) {
		return getSqlSession().selectOne(string, member_no);
	}
	
	public int selectTotalCount(String string, String department_no) {
		return getSqlSession().selectOne(string, department_no);
	}
	
	public List<AdminWorkHistoryDTO> getHistoryMemberListByDepartmentNo(String string, int department_no){
		return getSqlSession().selectList(string, department_no);
	}

}