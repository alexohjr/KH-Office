package com.khacademy.khoffice.reserve_assets.models;

import java.util.List;
import java.util.Map;

public class HoldingReserveDTO {
	private String hAssets_no;
	private String h_name;
	private List reserve;

	public String gethAssets_no() {
		return hAssets_no;
	}

	public void sethAssets_no(String hAssets_no) {
		this.hAssets_no = hAssets_no;
	}

	public String getH_name() {
		return h_name;
	}

	public void setH_name(String h_name) {
		this.h_name = h_name;
	}

	public List getReserve() {
		return reserve;
	}

	public void setReserve(List reserve) {
		this.reserve = reserve;
	}

	@Override
	public String toString() {
		return "HoldingReserveDTO [hAssets_no=" + hAssets_no + ", h_name=" + h_name + ", reserve=" + reserve + "]";
	}

}
