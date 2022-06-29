package com.khacademy.khoffice.reserve_assets.services;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.khacademy.khoffice.holding_assets.models.HoldingAssetsMemberDTO;
import com.khacademy.khoffice.reserve_assets.models.ReserveAsstesDTO;
import com.khacademy.khoffice.reserve_assets.models.ReserveMyMemberDTO;

public interface ReserveAsstesService {
	public List<HoldingAssetsMemberDTO> getHoldingAssetsListAll();
	public List<ReserveAsstesDTO> getday(Map<Object, Object> map);
	public List<ReserveAsstesDTO> getdayMember(Map<Object, Object> map);
	public int addReserveAssets(ReserveAsstesDTO reserveAsstesDTO);
	public List<String> getEnd(Map<Object, Object> map);
	public int myResuerCount(String member_no);
	public void myReserveDelete(String rAssets_no);
	public int holdingAssetsAllCount();
	
	public int reserveAssetsCount(String hAssets_no);
	
	
	public List<ReserveMyMemberDTO> getMyReserveList(Map<Object, Object> map);
	
}
