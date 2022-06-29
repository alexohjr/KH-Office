package com.khacademy.khoffice.holding_assets.daos;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.khacademy.khoffice.holding_assets.models.HoldingAssetsDTO;
import com.khacademy.khoffice.holding_assets.models.HoldingAssetsMemberDTO;

public class HoldingAssetsDAO extends SqlSessionDaoSupport {
	public int addHoldingAssets(String string, HoldingAssetsDTO holdingAssetsDTO) {
		return getSqlSession().insert(string, holdingAssetsDTO);
	}
	
	public List<HoldingAssetsMemberDTO> selectHoldingAssetsAll(String string, Map<Object, Object> map){
		return getSqlSession().selectList(string, map);
	}
	public List<HoldingAssetsMemberDTO> selectHoldingAssetsSearch(String string, Map<Object, Object> map){
		return getSqlSession().selectList(string, map);
	}
	
	public int holdingAssetsAllCount(String string) {
		return getSqlSession().selectOne(string);
	}
	public int holdingAssetsSearchCount(String string, String keyword) {
		return getSqlSession().selectOne(string,keyword);
	}
	
	public int holdingAssetsDelete(String string, String hAssets_no) {
		return getSqlSession().delete(string,hAssets_no);
	}
	
	public HoldingAssetsMemberDTO selectHoldingAssetsOne(String string, String hAssets_no) {
		return getSqlSession().selectOne(string,hAssets_no);
	}
	
	public int holdingAssetsEdit(String string, HoldingAssetsDTO holdingAssetsDTO) {
		return getSqlSession().update(string,holdingAssetsDTO);
	}
}
