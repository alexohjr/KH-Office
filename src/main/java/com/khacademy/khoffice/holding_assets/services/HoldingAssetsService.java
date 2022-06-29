package com.khacademy.khoffice.holding_assets.services;

import java.util.List;
import java.util.Map;

import com.khacademy.khoffice.holding_assets.models.HoldingAssetsDTO;
import com.khacademy.khoffice.holding_assets.models.HoldingAssetsMemberDTO;

public interface HoldingAssetsService {
	public List<HoldingAssetsMemberDTO> getHoldingAssetsListAll(Map<Object, Object> map);
	public List<HoldingAssetsMemberDTO> getSelectHoldingAssetsSearch(Map<Object, Object> Map);
	public HoldingAssetsMemberDTO getSelectHoldingAssetsOne(String hAssets_no);
	public int editHoldingAssets(HoldingAssetsDTO holdingAssetsDTO);
	public int addHoldingAssets(HoldingAssetsDTO holdingAssetsDTO);
	public int deleteHoldingAssets(String hassets_no);
	public int holdingAssetsAllCount();
	public int holdingAssetsSearchCount(String keyword);
	

}
