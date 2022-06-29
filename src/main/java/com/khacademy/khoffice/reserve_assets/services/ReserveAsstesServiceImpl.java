package com.khacademy.khoffice.reserve_assets.services;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.khacademy.khoffice.holding_assets.models.HoldingAssetsMemberDTO;
import com.khacademy.khoffice.reserve_assets.daos.ReserveAssetsDAO;
import com.khacademy.khoffice.reserve_assets.models.ReserveAsstesDTO;
import com.khacademy.khoffice.reserve_assets.models.ReserveMyMemberDTO;

@Service
public class ReserveAsstesServiceImpl implements ReserveAsstesService {
	private ReserveAssetsDAO reserveAssetsDAO;
	
	@Autowired
	@Required
	public void setReserveAssetsDAO(ReserveAssetsDAO reserveAssetsDAO) {
		this.reserveAssetsDAO = reserveAssetsDAO;
	}

	@Override
	public List<HoldingAssetsMemberDTO> getHoldingAssetsListAll() {
		return reserveAssetsDAO.selectHoldingAssetsAll("holdingAssets.selectHoldingAssetsAll");
	}

	@Override
	public int addReserveAssets(ReserveAsstesDTO reserveAsstesDTO) {
		return reserveAssetsDAO.addReserveAssets("reserveAssets.addReserveAssets", reserveAsstesDTO);
	}

	@Override
	public List<ReserveAsstesDTO> getday(Map<Object, Object> map) {  
		return reserveAssetsDAO.getDay("reserveAssets.getDay", map);
	}

	@Override
	public List<String> getEnd(Map<Object, Object> map) {
		return reserveAssetsDAO.getEnd("reserveAssets.getEnd", map);
	}

	@Override
	public List<ReserveMyMemberDTO> getMyReserveList(Map<Object, Object> map) {
		return reserveAssetsDAO.getReserveMyMember("reserveAssets.reserveMyMemberList", map);
	}
  
	@Override
	public int myResuerCount(String member_no) {
		return reserveAssetsDAO.getReserveCount("reserveAssets.reserveCount", member_no);
	}

	@Override
	public void myReserveDelete(String rAssets_no) {
		reserveAssetsDAO.myReserveDelete("reserveAssets.myReserveDelete", rAssets_no);
	}

	@Override
	public int holdingAssetsAllCount() {
		return reserveAssetsDAO.holdingAssetsAllCount("holdingAssets.holdingAssetsAllCount");
	}

	@Override
	public int reserveAssetsCount(String hAssets_no) {
		return reserveAssetsDAO.reserveAssetsCount("reserveAssets.reserveAssetsCount", hAssets_no);
	}

	@Override
	public List<ReserveAsstesDTO> getdayMember(Map<Object, Object> map) {
		return reserveAssetsDAO.getDayMember("reserveAssets.getDayMember", map);
	}
	
	
	
	
	
	
	

}
