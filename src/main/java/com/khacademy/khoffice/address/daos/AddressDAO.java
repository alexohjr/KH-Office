package com.khacademy.khoffice.address.daos;


import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.khacademy.khoffice.address.models.AddressDTO;
import com.khacademy.khoffice.address.models.BookmarkDTO;
import com.khacademy.khoffice.address.models.EditAddressDTO;

public class AddressDAO extends SqlSessionDaoSupport{
	
	//개인주소록 가져오기
	public List<AddressDTO> getList(String string, Object object){
		return getSqlSession().selectList(string, object);
	}
	//리스트 페이징용 개인주소록 수 카운트
	public int getAddressCount(String string, Object object) {
		return getSqlSession().selectOne(string, object);
	}
	
	//즐겨찾기 가져오기
	public List<BookmarkDTO> getBookmarkByMemberNo(String string, int memberNo) {
		return getSqlSession().selectList(string, memberNo);
	}
	
	//즐겨찾기 추가하기
	public int insertBookmark(String string,Object object) {
		return getSqlSession().insert(string, object);
	}
	
	//즐겨찾기 삭제
	public int deleteBookmark(String string, Object object) {
		return getSqlSession().delete(string, object);
	}
	
	//연락처 추가
	public int insertAddress(String string,AddressDTO addressDTO) {
		return getSqlSession().insert(string,addressDTO);
	}
	
	//연락처 수정
	public int updateAddress(String string, EditAddressDTO editAddressDTO) {
		return getSqlSession().update(string,editAddressDTO);
	}
	
	//연락처 삭제 
	public int deleteAddress(String string,int addressNo) {
		return getSqlSession().delete(string,addressNo);
	}
	
}
