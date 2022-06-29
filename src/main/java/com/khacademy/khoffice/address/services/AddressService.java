package com.khacademy.khoffice.address.services;

import java.util.List;

import com.khacademy.khoffice.address.models.AddressDTO;
import com.khacademy.khoffice.address.models.BookmarkDTO;
import com.khacademy.khoffice.address.models.EditAddressDTO;

public interface AddressService {
	public List<AddressDTO> getAddressList(Object object);
	public int addressCount(Object object);
	public List<BookmarkDTO> getBookmarkList(int memberNo);
	public boolean insertbookmark(Object object);
	public boolean deleteBookmark(Object object);
	public int insertAddress(AddressDTO addressDTO);
	public int updateAddress(EditAddressDTO editAddressDTO);
	public int deleteAddress(int addressNo);
}
