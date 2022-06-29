package com.khacademy.khoffice.holding_assets.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.khacademy.khoffice.holding_assets.daos.HoldingAssetsDAO;
import com.khacademy.khoffice.holding_assets.models.HoldingAssetsDTO;
import com.khacademy.khoffice.holding_assets.models.HoldingAssetsMemberDTO;

@Service
public class HoldingAssetsServiceImpl implements HoldingAssetsService {
	private HoldingAssetsDAO holdingAssetsDAO;

	@Autowired
	@Required
	public void setHoldingAssetsDAO(HoldingAssetsDAO holdingAssetsDAO) {
		this.holdingAssetsDAO = holdingAssetsDAO;
	}

	@Override
	public List<HoldingAssetsMemberDTO> getHoldingAssetsListAll(Map<Object, Object> map) {
		return holdingAssetsDAO.selectHoldingAssetsAll("holdingAssets.selectHoldingAssetsAll", map);
	}

	@Override
	public List<HoldingAssetsMemberDTO> getSelectHoldingAssetsSearch(Map<Object, Object> map) {
		return holdingAssetsDAO.selectHoldingAssetsSearch("holdingAssets.selectHoldingAssetsSearch", map);
	}

	@Override
	public int editHoldingAssets(HoldingAssetsDTO holdingAssetsDTO) {

		return holdingAssetsDAO.holdingAssetsEdit("holdingAssets.holdingAssetsEdit", holdingAssetsDTO);
	}

	@Override
	public int addHoldingAssets(HoldingAssetsDTO holdingAssetsDTO) {
		return holdingAssetsDAO.addHoldingAssets("holdingAssets.addHoldingAssets", holdingAssetsDTO);

	}

	@Override
	public int deleteHoldingAssets(String hAssets_no) {

		return holdingAssetsDAO.holdingAssetsDelete("holdingAssets.holdingAssetsDelete", hAssets_no);
	}

	@Override
	public int holdingAssetsAllCount() {
		return holdingAssetsDAO.holdingAssetsAllCount("holdingAssets.holdingAssetsAllCount");
	}

	@Override
	public int holdingAssetsSearchCount(String keyword) {
		return holdingAssetsDAO.holdingAssetsSearchCount("holdingAssets.holdingAssetsSearchCount", keyword);
	}

	@Override
	public HoldingAssetsMemberDTO getSelectHoldingAssetsOne(String hAssets_no) {

		return holdingAssetsDAO.selectHoldingAssetsOne("holdingAssets.selectHoldingAssetsOne", hAssets_no);
	}

}
