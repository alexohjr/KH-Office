package com.khacademy.khoffice.address.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.khacademy.khoffice.address.daos.AddressDAO;
import com.khacademy.khoffice.address.models.AddressDTO;
import com.khacademy.khoffice.address.models.BookmarkDTO;
import com.khacademy.khoffice.address.models.EditAddressDTO;
@Service
public class AddressServiceImpl implements AddressService{
private AddressDAO addressDAO;
	
	@Autowired
	@Required
	public void setMemberDAO(AddressDAO addressDAO) {
		this.addressDAO = addressDAO;
	}
	
	//개인주소록 불러오기
	@Override
	public List<AddressDTO> getAddressList(Object object){
		List<AddressDTO> addressDTO = null;
		try {
			addressDTO = addressDAO.getList("address.selectAddressList", object);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return addressDTO;
	}
	
	//페이징용 개인주소록 수 카운트
	@Override
	public int addressCount(Object object) {
		int x = 0;
		try {
			x = addressDAO.getAddressCount("address.selectAddressCount", object);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		return x;
	}
	
	//즐겨찾기 보기용 즐겨찾기 리스트 불러오기
	public List<BookmarkDTO> getBookmarkList(int memberNo) {
		List<BookmarkDTO> bookmarkDTO = null;
		bookmarkDTO = addressDAO.getBookmarkByMemberNo("address.selectBookmarkList",memberNo);
		return bookmarkDTO;
	}
	
	//즐겨찾기 추가
	public boolean insertbookmark(Object object) {
		boolean result = false;
		int insertedRow = 0;
		insertedRow = addressDAO.insertBookmark("address.insertBookmark",object);
		if(insertedRow != 0) {
			result = true;
		}
		return result;
	}
	
	//즐겨찾기 삭제
	public boolean deleteBookmark(Object object) {
		boolean result = false;
		int deletedRow = 0;
		deletedRow = addressDAO.deleteBookmark("address.deleteBookmark",object);
		if(deletedRow != 0) {
			result = true;
		}
		return result;
	}
	
	//개인연락처 등록하기
	public int insertAddress(AddressDTO addressDTO) {
		int insertedRow = 0;
		insertedRow = addressDAO.insertAddress("address.insertAddress",addressDTO);
		return insertedRow;
	}
	
	//개인 연락처 수정하기
	public int updateAddress(EditAddressDTO editAddressDTO) {
		int updatedRow = 0;
		updatedRow = addressDAO.updateAddress("address.updateAddress",editAddressDTO);
		return updatedRow;
	}
	//개인 연락처 삭제하기
	public int deleteAddress(int addressNo) {
		int deletedRow = 0;
		deletedRow = addressDAO.deleteAddress("address.deleteAddress",addressNo);
		return deletedRow;
	}
}
