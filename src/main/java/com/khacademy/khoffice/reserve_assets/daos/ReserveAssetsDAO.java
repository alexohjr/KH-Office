package com.khacademy.khoffice.reserve_assets.daos;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.khacademy.khoffice.holding_assets.models.HoldingAssetsDTO;
import com.khacademy.khoffice.holding_assets.models.HoldingAssetsMemberDTO;
import com.khacademy.khoffice.reserve_assets.models.ReserveAsstesDTO;
import com.khacademy.khoffice.reserve_assets.models.ReserveMyMemberDTO;

public class ReserveAssetsDAO extends SqlSessionDaoSupport{
	
	public List<HoldingAssetsMemberDTO> selectHoldingAssetsAll(String string){
		return getSqlSession().selectList(string);
	} 
	
	public int addReserveAssets(String string, ReserveAsstesDTO reserveAsstesDTO) {
		return getSqlSession().insert(string, reserveAsstesDTO);
	}
	
	public List<ReserveAsstesDTO> getDay(String string, Map<Object, Object> map){
		return getSqlSession().selectList(string,map);
	}
	
	public List<String> getEnd(String string, Map<Object, Object> map){
		return getSqlSession().selectList(string, map);
	}
	
	public List<ReserveMyMemberDTO> getReserveMyMember(String string, Map<Object, Object> map){
		return getSqlSession().selectList(string, map);
	}
	
	public int getReserveCount(String string, String member_no) {
		return getSqlSession().selectOne(string,member_no);
	}
	
	public void myReserveDelete(String string, String rAssets_no) {
		getSqlSession().delete(string,rAssets_no);
	}
	
	public int holdingAssetsAllCount(String string) {
		return getSqlSession().selectOne(string);
	}
	
	public int  reserveAssetsCount(String string,String hAssets_no) {
		return getSqlSession().selectOne(string,hAssets_no);
	}
	
	public List<ReserveAsstesDTO> getDayMember(String string, Map<Object, Object> map){
		return getSqlSession().selectList(string,map);
	}
	
}
